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
import pojo.AppointResult;
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
     * @param param
     * @param userid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/appointment", method = RequestMethod.POST)
    public Map<String, Integer> makeAppointment(@RequestBody Map<String, String> param, @ModelAttribute("id") Integer userid){
        Appointment appointment = new Appointment();
        System.out.println(param.get("parkingid"));
        // DateTime date = DateTime.parse(appointment.getTime(), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        // 尝试转化传过来的时间字符串
        DateTimeFormatter timeFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime date = timeFormat.parseDateTime(param.get("time"));
        System.out.println(date.toString());

        appointment.setParkingid(Integer.parseInt(param.get("parkingid")));
        appointment.setTime(param.get("time"));

        Map<String, Integer> res = new HashMap<String, Integer>();
        SqlSession sqlSession = DBUtil.openSession();

        try {
            IAppointment iappointment = sqlSession.getMapper(IAppointment.class);
            // 车位库存减一
            if (iappointment.reduceParkingNum(appointment.getParkingid()) != 1) {
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
            if (iappointment.addAppointment(appointment) == 0) {
                sqlSession.rollback();
                sqlSession.close();
                res.put("code", 2);
                return res;
            }
            sqlSession.commit();
        }
        finally {
            sqlSession.close();
        }
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
    public List<AppointResult> getAppointment(@ModelAttribute("id") Integer userid){
        SqlSession sqlSession = DBUtil.openSession();
        try {
            IAppointment iAppointment = sqlSession.getMapper(IAppointment.class);
            List<AppointResult> appointments = iAppointment.getAppoints(userid);
            for (int i = 0; i < appointments.size(); i++) {
                AppointResult appointment = appointments.get(i);
                // 15分钟未支付的订单自动取消
                if(appointment.getState().equals("未支付")){
                    DateTime createTime = DateTime.parse(appointment.getCreate_time().substring(0, appointment.getCreate_time().lastIndexOf('.')), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
                    if(createTime.plusMinutes(15).compareTo(DateTime.now()) < 0){
                        appointment.setState("超时自动取消");
                        // 停车场剩余停车位加 1
                        iAppointment.increaseParkingNum(appointment.getParkingid());
                        sqlSession.commit();
                    }
                }
                // 构造二维码
                else{
                    String req_url = "http://10.4.21.211:8080/verification";
                    String qrCode = req_url + "?certificate=" + appointment.getCertificate() + "&orderid=" + appointment.getId() + "&parkingid=" + appointment.getParkingid();
                    System.out.println("qrCode: " + qrCode);
                    appointment.setCertificate(qrCode);
                }
            }
            return appointments;
        }
        finally {
            sqlSession.close();
        }
    }

    /**
     * 取消预约
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/appointment", method = RequestMethod.DELETE)
    public Map<String, Integer> cancelAppointment(@RequestBody Map<String, String> param, @ModelAttribute("id") Integer userid){
        Map<String, Integer> res = new HashMap<String, Integer>();
        Integer orderid = Integer.parseInt(param.get("orderid"));
        System.out.println("orderid: " + orderid);
        if(orderid == null){
            res.put("code ", 2);
            return res;
        }
        SqlSession sqlSession = DBUtil.openSession();
        try {
            Appointment appointment = sqlSession.selectOne("getAppointByOrderid", orderid);
            // 判断是否是此用户的订单
            if (appointment.getUserid() != userid) {
                res.put("code ", 2);
                return res;
            }
            IAppointment iAppointment = sqlSession.getMapper(IAppointment.class);
            if (iAppointment.cancelAppoint(orderid) == 0) {
                res.put("code ", 2);
                return res;
            }
            System.out.printf("parkingid: " + appointment.getParkingid());
            // 停车场剩余停车位加 1
            iAppointment.increaseParkingNum(appointment.getParkingid());
            sqlSession.commit();
        }
        finally {
            sqlSession.close();
        }
        res.put("code", 1);
        return res;
    }

    /**
     * 二维码验证
     * @param certificate
     * @param parkingid
     * @param orderid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/verification", method = RequestMethod.GET)
    public Map<String, Integer> verification(String certificate, Integer parkingid, Integer orderid){
        Map<String, Integer> res = new HashMap<String, Integer>();
        SqlSession sqlSession = DBUtil.openSession();
        try{
            IAppointment iAppointment = sqlSession.getMapper(IAppointment.class);
            Appointment appointment = iAppointment.getAppointment(orderid);
            if(appointment != null && appointment.getParkingid() == parkingid && appointment.getCertificate().equals(certificate)){
                // 设置进入停车场时间
                if(appointment.getState().equals("未消费")){
                    DateTime dataTime = DateTime.now();
                    iAppointment.updateStartTime(dataTime.toString("yyyy-MM-dd HH:mm:ss"), orderid, "停车中");
                    res.put("code", 1);
                    sqlSession.commit();
                    return res;
                }
                // 设置离开停车场时间
                else if(appointment.getState().equals("停车中")){
                    DateTime startTime = DateTime.parse(appointment.getStart_time().substring(0, appointment.getStart_time().lastIndexOf('.')), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
                    // 停车时间不能小于一分钟
                    if(startTime.plusMinutes(1).compareTo(DateTime.now()) > 0){
                        res.put("code", 2);
                        return res;
                    }
                    else {
                        DateTime dateTime = DateTime.now();
                        iAppointment.updateEndTime(dateTime.toString("yyyy-MM-dd HH:mm:ss"), orderid, "已消费");
                        res.put("code", 1);
                        sqlSession.commit();
                        return res;
                    }
                }
                else{
                    res.put("code", 2);
                }
            }
            else {
                res.put("code", 2);
            }
        }
        finally {
            sqlSession.close();
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public Map<String, Integer> pay(Integer orderid) {
        Map<String, Integer> res = new HashMap<String, Integer>();
        SqlSession sqlSession = DBUtil.openSession();
        try {
            IAppointment iAppointment = sqlSession.getMapper(IAppointment.class);
            Appointment appointment = iAppointment.getAppointment(orderid);
            if(appointment.getState().equals("未支付")){
                DateTime createTime = DateTime.parse(appointment.getCreate_time().substring(0, appointment.getCreate_time().lastIndexOf('.')), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
                // 超时之后不能支付
                if(createTime.plusMinutes(15).compareTo(DateTime.now()) < 0) {
                    res.put("code", 2);
                    res.put("reason", 1);
                    return res;
                }
                iAppointment.updateEndTime(null, orderid, "已支付");
                res.put("code", 1);
                sqlSession.commit();
                return res;
            }
            else{
                res.put("code", 2);
                return res;
            }
        }
        finally {
            sqlSession.close();
        }
    }

}
