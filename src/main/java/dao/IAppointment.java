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

    public AppointResult getLatestAppoints(@Param("userid") int userid);

    public List<AppointResult> getAppoints(@Param("userid") int userid);

    public int cancelAppoint(@Param("orderid") int orderid);

    public Appointment getAppointment(@Param("orderid") int orderid);

    public AppointResult getAppointResult(@Param("orderid") int orderid);

    public int updateStartTime(@Param("start_time") String start_time, @Param("orderid") int orderid, @Param("state") String state);

    public int updateEndTime(@Param("end_time") String end_time, @Param("orderid") int orderid, @Param("state") String state);

    public int updateState(@Param("orderid") int orderid, @Param("state") String state);

    public Appointment confirmCertificate(@Param("orderid") int orderid, @Param("certificate") String certificate);
}
