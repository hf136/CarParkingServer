package pojo;


/**
 * Created by wuyueqiu on 16-3-9.
 */
public class Appointment {

    private int id;
    private int userid;
    private String time;
    private int parkingid;
    private String start_time;
    private String end_time;
    private String certificate;
    private float money;
    private String state;
    private String create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getParkingid() {
        return parkingid;
    }

    public void setParkingid(int parkingid) {
        this.parkingid = parkingid;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", userid=" + userid +
                ", time='" + time + '\'' +
                ", parkingid=" + parkingid +
                ", start_time='" + start_time + '\'' +
                ", end_time='" + end_time + '\'' +
                ", certificate='" + certificate + '\'' +
                ", money=" + money +
                ", state='" + state + '\'' +
                ", create_time='" + create_time + '\'' +
                '}';
    }
}
