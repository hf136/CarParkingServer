package dao;

import pojo.User;

import java.util.List;

/**
 * Created by wuyueqiu on 16-3-8.
 */
public interface IUser {

    public List<User> selectUser();

    public User selectUserByName(String username);
}
