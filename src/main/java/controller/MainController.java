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
    public String addParking(@RequestBody List<Parking> parkings){
        SqlSession sqlSession = DBUtil.openSession();
        IParking iParking = sqlSession.getMapper(IParking.class);
        System.out.println(parkings.toString());

        for(int i=0; i<parkings.size(); i++){
            if(iParking.addParking(parkings.get(i)) == 0) {
                sqlSession.close();
                return "add parking lot failed";
            }
        }
        sqlSession.commit();
        sqlSession.close();
        return "add parking lot success";
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
