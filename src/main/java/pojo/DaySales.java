package pojo;

/**
 * Created by wuyueqiu on 16-4-11.
 */
public class DaySales {
    private int puserid;
    private long income;
    private int orders;
    private String date;

    public int getPuserid() {
        return puserid;
    }

    public void setPuserid(int puserid) {
        this.puserid = puserid;
    }

    public long getIncome() {
        return income;
    }

    public void setIncome(long income) {
        this.income = income;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
