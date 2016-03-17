package controller;

import dao.IAppointment;
import org.apache.ibatis.session.SqlSession;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import pojo.Appointment;
import sun.misc.BASE64Encoder;
import util.DBUtil;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wyq on 2016/3/16.
 */

@Controller
@SessionAttributes("id")
public class AppointmentController {

    /**
     * 预约
     * @param appointment
     * @param userid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/appointment", method = RequestMethod.POST)
    public Map<String, Integer> makeAppointment(@RequestBody Appointment appointment, @ModelAttribute("id") Integer userid){
        System.out.println(appointment.getParkingid());
//        DateTime date = DateTime.parse(appointment.getTime(), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        DateTimeFormatter timeFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime date = timeFormat.parseDateTime(appointment.getTime());
        System.out.println(date.toString());

        Map<String, Integer> res = new HashMap<String, Integer>();
        SqlSession sqlSession = DBUtil.openSession();
        IAppointment iappointment = sqlSession.getMapper(IAppointment.class);
        // 车位库存减一
        if(iappointment.reduceParkingNum(appointment.getParkingid()) != 1){
            sqlSession.rollback();
            sqlSession.close();
            System.out.println("库存不足");
            res.put("code", 2);
            return res;
        }
        // 设置预约订单信息
        appointment.setUserid(userid);
        DateTime now = DateTime.now();
        String createTime = now.toString(timeFormat);
        System.out.println("now time: " + createTime);
        // 预约订单创建时间
        appointment.setCreate_time(createTime);
        // 预约状态
        appointment.setState("未支付");
        // 预约凭证
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            String str = String.valueOf(System.currentTimeMillis()) + userid;
            md5.update(str.getBytes());
            BASE64Encoder base64Encoder = new BASE64Encoder();
            String certificate = base64Encoder.encode(md5.digest());
            System.out.println("md5: " + certificate);
            appointment.setCertificate(certificate);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if(iappointment.addAppointment(appointment) == 0){
            sqlSession.rollback();
            sqlSession.close();
            res.put("code", 2);
            return res;
        }
        sqlSession.commit();
        sqlSession.close();
        res.put("code", 1);
        res.put("orderid", appointment.getId());
        return res;
    }

    /**
     * 获取预约列表
     * @param userid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/appointment", method = RequestMethod.GET)
    public List<Appointment> getAppointment(@ModelAttribute("id") Integer userid){
        SqlSession sqlSession = DBUtil.openSession();
        IAppointment iAppointment = sqlSession.getMapper(IAppointment.class);
        List<Appointment> appointments = iAppointment.getAppoints(userid);
        sqlSession.close();
        return appointments;
    }

    /**
     * 取消预约
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/appointment", method = RequestMethod.DELETE)
    public Map<String, Integer> cancelAppointment(@RequestBody Map<String, Integer> param, @ModelAttribute("id") Integer userid){
        Map<String, Integer> res = new HashMap<String, Integer>();
        Integer orderid = param.get("orderid");
        System.out.println("orderid: " + orderid);
        if(orderid == null){
            res.put("code ", 2);
            return res;
        }
        SqlSession sqlSession = DBUtil.openSession();
        Appointment appointment = sqlSession.selectOne("getAppointByOrderid", orderid);
        // 判断是否是此用户的订单
        if(appointment.getUserid() != userid){
            res.put("code ", 2);
            return res;
        }
        IAppointment iAppointment = sqlSession.getMapper(IAppointment.class);
        if(iAppointment.cancelAppoint(orderid) == 0){
            res.put("code ", 2);
            return res;
        }
        System.out.printf("parkingid: " + appointment.getParkingid());
        iAppointment.increaseParkingNum(appointment.getParkingid());
        sqlSession.commit();
        sqlSession.close();
        res.put("code", 1);
        return res;
    }

}