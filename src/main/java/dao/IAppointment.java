package dao;

import org.apache.ibatis.annotations.Param;
import pojo.AppointResult;
import pojo.Appointment;

import java.util.List;

/**
 * Created by wuyueqiu on 16-3-16.
 */
public interface IAppointment {

    public int addAppointment(Appointment appointment);

    public int reduceParkingNum(@Param("parkingid") int parkingid);

    public int increaseParkingNum(@Param("parkingid") int parkingid);

    public List<AppointResult> getAppoints(@Param("userid") int userid);

    public int cancelAppoint(@Param("orderid") int orderid);
}
