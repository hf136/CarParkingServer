package controller;

import dao.IParking;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.Parking;
import pojo.User;
import util.DBUtil;

import java.io.UnsupportedEncodingException;
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

    @RequestMapping("/test2")
    @ResponseBody
    public User test2(@RequestBody User user){
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/parkinglots", method = RequestMethod.POST)
    public String addParkings(@RequestBody List<Parking> parkings){
        SqlSession sqlSession = DBUtil.openSession();
        try {
            IParking iParking = sqlSession.getMapper(IParking.class);

            for (int i = 0; i < parkings.size(); i++) {
                if (iParking.addParking(parkings.get(i)) == 0) {
                    sqlSession.close();
                    return "add parking lot failed";
                }
            }
            sqlSession.commit();
        }
        finally {
            sqlSession.close();
        }
        return "add parking lot success";
    }

    @ResponseBody
    @RequestMapping(value = "/parkinglot", method = RequestMethod.POST)
    public String addParking(@RequestBody Parking parking){
        SqlSession sqlSession = DBUtil.openSession();
        try {
            IParking iParking = sqlSession.getMapper(IParking.class);
            System.out.println(parking.toString());

            if (iParking.addParking(parking) == 0) {
                sqlSession.close();
                return "add parking lot failed";
            }
            sqlSession.commit();
        }
        finally {
            sqlSession.close();
        }
        return "add parking lot success";
    }

    @ResponseBody
    @RequestMapping(value = "/parkinglot", method = RequestMethod.GET)
    public List<Parking> getParkingList() throws UnsupportedEncodingException {
        SqlSession sqlSession = DBUtil.openSession();
        List<Parking> parkings;
        try {
            IParking iParking = sqlSession.getMapper(IParking.class);
            parkings = iParking.getParkings();
        }
        finally {
            sqlSession.close();
        }
        return parkings;
    }
}
