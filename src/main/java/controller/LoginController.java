package controller;

import dao.IPuser;
import dao.IUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import pojo.Puser;
import pojo.User;
import pojo.UserInfo;
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
        try {
            IUser iuser = session.getMapper(IUser.class);
            User user = iuser.getUserWithOauth(uid, platform);
            if (user == null) {
                user = new User();
                user.setUsername(uid);
                user.setPlatform(platform);
                int line = iuser.addUserWithOauth(user);
                httpSession.setAttribute("id", user.getId());
                System.out.printf("add new oauth user: " + user.getId());
            } else {
                httpSession.setAttribute("id", user.getId());
            }
            // 添加微博用户信息
            UserInfo userInfo = new UserInfo();
            userInfo.setUserid(user.getId());
            userInfo.setNickname(req.getParameter("screen_name"));
            userInfo.setAvatar(req.getParameter("profile_image_url"));
            UserInfo tmp = iuser.getUserInfoById((user.getId()));
            if(tmp == null){
                iuser.addUserInfo(userInfo);
            }
            else {
                iuser.updateUserInfo(userInfo);
            }
            session.commit();
        }
        finally {
            session.close();
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("login", "success");
        return map;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, String> login(String username, String password, HttpSession httpSession){
        SqlSession sqlSession = DBUtil.openSession();
        Map<String, String> map = new HashMap<String, String>();
        try {
            IUser iuser = sqlSession.getMapper(IUser.class);
            User user = iuser.getUserWithOauth(username, "local");

            if (user == null || !user.getPassword().equals(password)) {
                map.put("login", "failed");
            } else {
                httpSession.setAttribute("id", user.getId());
                map.put("login", "success");
            }
            return map;
        }
        finally {
            sqlSession.close();
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public Map<String, String> register(String username, String password){
        System.out.println(username);
        System.out.println(password);
        SqlSession sqlSession = DBUtil.openSession();
        Map<String, String> map = new HashMap<String, String>();
        try {
            IUser iUser = sqlSession.getMapper(IUser.class);
            User user = iUser.getUserWithOauth(username, "local");

            if (user != null) {
                map.put("register", "failed");
                map.put("message", "username already have.");
                return map;
            }
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setPlatform("local");

            if (iUser.addUser(user) == 1) {
                map.put("register", "success");
            } else {
                map.put("register", "failed");
            }
            sqlSession.commit();
        }
        finally {
            sqlSession.close();
        }
        return map;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout(HttpSession httpSession){
        httpSession.setAttribute("id", null);
        return "logout success";
    }

    @RequestMapping("/admin/login")
    @ResponseBody
    public Map<String, String> adminlogin(String username, String password, HttpSession httpSession){
        System.out.println("/admin/login");
        SqlSession sqlSession = DBUtil.openSession();
        Map<String, String> map = new HashMap<String, String>();
        try {
            IPuser iPuser = sqlSession.getMapper(IPuser.class);
            Puser puser = iPuser.getPuserByName(username);

            if (puser == null || !puser.getPassword().equals(password)) {
                map.put("login", "failed");
            } else {
                httpSession.setAttribute("puserid", puser.getId());
                map.put("login", "success");
            }
            return map;
        }
        finally {
            sqlSession.close();
        }
    }

    @RequestMapping("/admin/logout")
    public String adminlogout(HttpSession httpSession){
        httpSession.setAttribute("puserid", null);
        return "redirect:/admin/signin.html";
    }

    @RequestMapping("/admin/register")
    @ResponseBody
    public Map<String, String> adminRegister(String username, String password){
        System.out.println(username);
        System.out.println(password);
        SqlSession sqlSession = DBUtil.openSession();
        Map<String, String> map = new HashMap<String, String>();
        try {
            IPuser iPuser = sqlSession.getMapper(IPuser.class);
            Puser puser = iPuser.getPuserByName(username);

            if (puser != null) {
                map.put("register", "failed");
                map.put("message", "username already have.");
                return map;
            }
            puser = new Puser();
            puser.setUsername(username);
            puser.setPassword(password);

            if (iPuser.addPuser(puser) == 1) {
                map.put("register", "success");
            } else {
                map.put("register", "failed");
            }
            sqlSession.commit();
        }
        finally {
            sqlSession.close();
        }
        return map;
    }

}
