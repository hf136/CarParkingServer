package dao;

import org.apache.ibatis.annotations.Param;
import pojo.User;
import pojo.UserInfo;

import java.util.List;

/**
 * Created by wuyueqiu on 16-3-8.
 */
public interface IUser {

    public List<User> selectUser();

    public User selectUserByName(String username);

    public int addUserWithOauth(User user);

    public int addUser(User user);

    public User getUserWithOauth(@Param("username") String username, @Param("platform") String platform);

    public User getUserById(int id);

    public UserInfo getUserInfoById(int userid);

    public int addUserInfo(UserInfo userInfo);

    public int updateUserInfo(UserInfo userInfo);
}
