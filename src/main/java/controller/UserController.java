package controller;

import dao.IUser;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pojo.UserInfo;
import util.DBUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wyq on 2016/3/24.
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("id")
public class UserController {

    /**
     * 获取用户信息
     * @param userid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public UserInfo getInfo(@ModelAttribute("id") Integer userid){
        SqlSession sqlSession = DBUtil.openSession();
        try{
            IUser iUser = sqlSession.getMapper(IUser.class);
            UserInfo userInfo = iUser.getUserInfoById(userid);
            return userInfo;
        }
        finally {
            sqlSession.close();
        }
    }

    /**
     * 修改用户信息
     * @param userid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    public Map<String, Integer> modInfo(@ModelAttribute("id") Integer userid, @RequestBody Map<String, String> param){
        // System.out.println(param.get("nickname"));
        UserInfo userInfo = new UserInfo();
        SqlSession sqlSession = DBUtil.openSession();
        Map<String, Integer> res = new HashMap<String, Integer>();
        try{
            userInfo.setUserid(userid);
            userInfo.setNickname(param.get("nickname"));
            userInfo.setPhone(param.get("phone"));
            userInfo.setAvatar(param.get("avatar"));
            userInfo.setSex(param.get("sex"));

            IUser iUser = sqlSession.getMapper(IUser.class);
            UserInfo tmp = iUser.getUserInfoById(userid);
            if(tmp == null){
                iUser.addUserInfo(userInfo);
            }
            else {
                iUser.updateUserInfo(userInfo);
            }
            sqlSession.commit();
            res.put("code", 1);
            return res;
        }
        finally {
            sqlSession.close();
        }
    }
}
