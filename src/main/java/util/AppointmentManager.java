package util;

import dao.IAppointment;
import org.apache.ibatis.session.SqlSession;
import org.joda.time.DateTime;
import pojo.AppointmentEntry;

import java.util.Vector;

/**
 * Created by wyq on 2016/3/28.
 */
public class AppointmentManager {
    // Vector是线程安全的
    private static Vector<AppointmentEntry> appointmentEntries = new Vector<AppointmentEntry>();

    static {
        // 开启超时自动取消预约线程
        run();
    }

    public static void addAppointmentEntry(AppointmentEntry appointmentEntry){
        appointmentEntries.add(appointmentEntry);
    }

    public static void removeAppointmnetEntry(int id){
        for (int i = 0; i < appointmentEntries.size(); i++) {
            if(appointmentEntries.get(i).getId() == id){
                appointmentEntries.remove(i);
                return ;
            }
        }
    }

    private static void run(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("启动超时自动预约线程...");
                while (true){
                    if(appointmentEntries.size() > 0){
                        SqlSession sqlSession = DBUtil.openSession();
                        try {
                            IAppointment iAppointment = sqlSession.getMapper(IAppointment.class);
                            for (int i = 0; i < appointmentEntries.size(); i++) {
                                AppointmentEntry appointmentEntry = appointmentEntries.get(i);
                                if (appointmentEntry.getCreate_time().plusMinutes(15).compareTo(DateTime.now()) < 0) {
                                    iAppointment.updateState(appointmentEntry.getId(), "超时自动取消");
                                    // 停车场剩余停车位加 1
                                    iAppointment.increaseParkingNum(appointmentEntry.getParkingid());
                                    sqlSession.commit();
                                    System.out.println(DateTime.now().toString() + " 自动取消预约订单: " + appointmentEntry.getId());
                                    appointmentEntries.removeElementAt(i);
                                    i--;
                                }
                            }
                        }
                        finally {
                            sqlSession.close();
                        }

                        // 线程休眠一分钟
                        try{
                            Thread.sleep(1000*60);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        // 预约队列为空，线程可以休眠更长的时间
                        try {
                            Thread.sleep(1000*60*5); // 5分钟
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        thread.start();
    }
}
