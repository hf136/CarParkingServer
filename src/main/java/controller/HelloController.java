package controller;

import dao.IUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.User;
import util.DBUtil;

import java.util.List;

/**
 * Created by wuyueqiu on 16-3-8.
 */

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        System.out.println("hello");
        return "haha";
    }

    @RequestMapping("/test")
    @ResponseBody
    public List<User> test(){
        SqlSession session = DBUtil.openSession();
        IUser iuser = session.getMapper(IUser.class);
        List<User> users = iuser.selectUser();
        session.close();
        return users;
    }
}
