package controller;

import dao.IAdminOperater;
import org.apache.ibatis.session.SqlSession;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import pojo.DaySales;
import util.DBUtil;

import java.util.List;
import java.util.Map;

/**
 * Created by wuyueqiu on 16-4-11.
 */
@Controller()
@RequestMapping("/admin")
@SessionAttributes("puserid")
public class DashboardController {

    @RequestMapping("/dashboard")
    public ModelAndView dashboard(@ModelAttribute("puserid") Integer puserid){
        System.out.println("enter dash");
        ModelAndView mv = new ModelAndView("dashboard");

        SqlSession sqlSession = DBUtil.openSession();
        try{
            IAdminOperater iAdminOperater = sqlSession.getMapper(IAdminOperater.class);
            Integer parkingid = iAdminOperater.getParkingid(puserid);
            if(parkingid == null){
                return mv;
            }
            System.out.println("parkingid: " + parkingid);
            mv.addObject("available", iAdminOperater.getAvailableParking(puserid));
            mv.addObject("total", iAdminOperater.getTotalIncome(parkingid) + "元");

            DateTime time = DateTime.now();
            String today = time.toString("yyyy-MM-dd") + "%";
            Map<String, Integer> map = iAdminOperater.getTodayOrderAndIncome(parkingid, today);
            mv.addObject("income", map.get("income") == null ? "0元" : map.get("income") + "元");
            mv.addObject("order", map.get("order"));
        }
        finally {
            sqlSession.close();
        }
        return mv;
    }

    @ResponseBody
    @RequestMapping("/sales")
    public List<DaySales> getSalesHistory(@ModelAttribute("puserid") Integer puserid){
        SqlSession sqlSession = DBUtil.openSession();
        try{
            IAdminOperater iAdminOperater = sqlSession.getMapper(IAdminOperater.class);
            return iAdminOperater.getDashboardInfo(puserid);
        }
        finally {
            sqlSession.close();
        }
    }
}
