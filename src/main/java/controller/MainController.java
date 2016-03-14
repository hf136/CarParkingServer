package controller;

import dao.IParking;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.Parking;
import util.DBUtil;

import java.util.List;

/**
 * Created by wuyueqiu on 16-3-9.
 */
@Controller
@SessionAttributes("id")
public class MainController {

    @RequestMapping("/u")
    @ResponseBody
    public String test(@ModelAttribute("id") Integer id){
        return id.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/parkinglot", method = RequestMethod.POST)
    public String addParking(@RequestBody Parking parking){
        SqlSession sqlSession = DBUtil.openSession();
        IParking iParking = sqlSession.getMapper(IParking.class);
        System.out.println(parking.toString());

        if(iParking.addParking(parking) == 1) {
            sqlSession.commit();
            sqlSession.close();
            return "add parkinglot success";
        }
        else {
            sqlSession.close();
            return "add parkinglot failed";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/parkinglot", method = RequestMethod.GET)
    public List<Parking> getParkingList(){
        SqlSession sqlSession = DBUtil.openSession();
        IParking iParking = sqlSession.getMapper(IParking.class);
        List<Parking> parkings = iParking.getParkings();
        sqlSession.close();
        return parkings;
    }
}
