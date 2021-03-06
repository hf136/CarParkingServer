package pojo;

/**
 * Created by wuyueqiu on 16-3-9.
 */
public class Parking {

    private int id;
    private String name;
    private double latitude;
    private double longitude;
    private String address;
    private float price;
    private int amount;
    private int available;
    private String image;
    private String state;
    private int puserid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPuserid() {
        return puserid;
    }

    public void setPuserid(int puserid) {
        this.puserid = puserid;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ", available=" + available +
                ", image='" + image + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
