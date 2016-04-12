package controller;

import dao.IAdminOperater;
import dao.IAppointment;
import dao.IParking;
import org.apache.ibatis.session.SqlSession;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pojo.Parking;
import pojo.User;
import util.BaiduMapUtil;
import util.DBUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public List<Parking> getParkingList() {
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

    @ResponseBody
    @RequestMapping(value = "/parkinglot/nearby/{radius}", method = RequestMethod.GET)
    public List<Parking> getParkingNearbyList(String location, @PathVariable double radius) {
        String[] str = location.split(",");
        double longitude = Double.parseDouble(str[0]);
        double latitude = Double.parseDouble(str[1]);
        SqlSession sqlSession = DBUtil.openSession();
        List<Parking> parkings;
        List<Parking> parkingResultList = new ArrayList<Parking>();
        try {
            IParking iParking = sqlSession.getMapper(IParking.class);
            parkings = iParking.getParkings();

            for (Parking parking : parkings) {
                double dist = BaiduMapUtil.GetShortDistance(longitude, latitude, parking.getLongitude(), parking.getLatitude());
                System.out.println("dist: " + dist);
                if (dist <= radius) {
                    parkingResultList.add(parking);
                }
            }
        }
        finally {
            sqlSession.close();
        }
        return parkingResultList;
    }
}
