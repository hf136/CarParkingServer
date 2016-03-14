package dao;

import pojo.Parking;

import java.util.List;

/**
 * Created by wuyueqiu on 16-3-14.
 */
public interface IParking {

    public List<Parking> getParkings();

    public int addParking(Parking parking);
}
