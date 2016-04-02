package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Puser;

/**
 * Created by wuyueqiu on 16-4-2.
 */
public interface IPuser {

    public Puser getPuserByName(@Param("username") String username);

    public int addPuser(Puser puser);

}
