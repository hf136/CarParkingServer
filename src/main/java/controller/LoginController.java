package controller;

import dao.IUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
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
    public Map<String, String> login(String username, String password, HttpSession httpSession){
        SqlSession sqlSession = DBUtil.openSession();
        IUser iuser = sqlSession.getMapper(IUser.class);
        User user = iuser.getUserWithOauth(username, "local");

        Map<String, String> map = new HashMap<String, String>();
        if(user == null || !user.getPassword().equals(password)){
            map.put("login", "failed");
        }
        else {
            httpSession.setAttribute("id", user.getId());
            map.put("login", "success");
        }
        sqlSession.close();
        return map;
    }

    @RequestMapping("/register")
    @ResponseBody
    public Map<String, String> register(String username, String password){
        System.out.println(username);
        System.out.println(password);
        SqlSession sqlSession = DBUtil.openSession();
        IUser iUser = sqlSession.getMapper(IUser.class);
        User user = iUser.getUserWithOauth(username, "local");

        Map<String, String> map = new HashMap<String, String>();
        if(user != null){
            map.put("register", "failed");
            map.put("message", "username already have.");
            return map;
        }
        user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPlatform("local");

        if(iUser.addUser(user) == 1){
            map.put("register", "success");
        }
        else {
            map.put("register", "failed");
        }
        sqlSession.commit();
        sqlSession.close();
        return map;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout(HttpSession httpSession){
        httpSession.setAttribute("id", null);
        return "logout success";
    }

}
