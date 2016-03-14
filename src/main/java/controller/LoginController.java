package controller;

import dao.IUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import pojo.User;
import util.DBUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuyueqiu on 16-3-12.
 */

@Controller
@SessionAttributes
public class LoginController {

    @RequestMapping("/oauth")
    @ResponseBody
    public Map<String, String> loginWithOauth(String uid, String platform, HttpServletRequest req, HttpSession httpSession){
        System.out.println("uid: " + uid);
        System.out.println("platform: " + platform);
        System.out.println("profile_image_url: " + req.getParameter("profile_image_url"));

        SqlSession session = DBUtil.openSession();
        IUser iuser = session.getMapper(IUser.class);
        User user = iuser.getUserWithOauth(uid, platform);
        if(user == null){
            user = new User();
            user.setUsername(uid);
            user.setPlatform(platform);
            int line = iuser.addUserWithOauth(user);
            httpSession.setAttribute("id", user.getId());
            System.out.printf("add new oauth user: " + user.getId());
        }
        else{
            httpSession.setAttribute("id", user.getId());
        }
        session.commit();
        session.close();
        Map<String, String> map = new HashMap<String, String>();
        map.put("login", "success");
        return map;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, String> login(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("login", "success");
        return map;
    }

    @RequestMapping("/logout")
    public void logout(HttpSession httpSession){
        httpSession.setAttribute("id", null);
    }

}
