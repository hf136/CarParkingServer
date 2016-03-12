package dao;

import org.apache.ibatis.annotations.Param;
import pojo.User;

import java.util.List;

/**
 * Created by wuyueqiu on 16-3-8.
 */
public interface IUser {

    public List<User> selectUser();

    public User selectUserByName(String username);

    public int addUserWithOauth(@Param("username") String username, @Param("platform") String platform);

    public int addUser(@Param("username") String username, @Param("password") String password);

    public User getUserWithOauth(@Param("username") String username, @Param("platform") String platform);

    public User getUserById(int id);
}
