package dao;

import org.apache.ibatis.annotations.Param;
import pojo.Appointment;
import pojo.DaySales;

import java.util.List;
import java.util.Map;

/**
 * Created by wuyueqiu on 16-4-3.
 */
public interface IAdminOperater {

    public Integer getParkingid(@Param("puserid") int puserid);

    public Long getTotalIncome(@Param("parkingid") int parkingid);

    public Map<String, Integer> getTodayOrderAndIncome(@Param("parkingid") int parkingid, @Param("date") String date);

    public Integer getAvailableParking(@Param("puserid") int puserid);

    public List<DaySales> getDashboardInfo(@Param("puserid") int puserid);

    public List<Appointment> getAppointments(@Param("parkingid") int parkingid);

}
