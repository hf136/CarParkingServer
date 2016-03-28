package pojo;

import org.joda.time.DateTime;

/**
 * Created by wyq on 2016/3/28.
 */
public class AppointmentEntry {
    private int id;
    private int parkingid;
    private DateTime create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(DateTime create_time) {
        this.create_time = create_time;
    }

    public int getParkingid() {
        return parkingid;
    }

    public void setParkingid(int parkingid) {
        this.parkingid = parkingid;
    }
}
