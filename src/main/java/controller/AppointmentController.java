package controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import pojo.Appointment;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wyq on 2016/3/16.
 */

@Controller
@SessionAttributes("id")
public class AppointmentController {

    @ResponseBody
    @RequestMapping(value = "/appointment", method = RequestMethod.POST)
    public Appointment makeAppointment(@RequestBody Appointment appointment){
        System.out.println(appointment.getParkingid());
//        DateTime date = DateTime.parse(appointment.getTime(), DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        DateTimeFormatter timeFormat = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime date = timeFormat.parseDateTime(appointment.getTime());
        System.out.println(date.toString());
        Map<String, Integer> res = new HashMap<String, Integer>();
        res.put("code", 1);
        return appointment;
    }

    @ResponseBody
    @RequestMapping(value = "/appointment", method = RequestMethod.GET)
    public Map<String, Integer> getAppointment(){
        Map<String, Integer> res = new HashMap<String, Integer>();
        res.put("code", 1);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/appointment", method = RequestMethod.DELETE)
    public Map<String, Integer> cancelAppointment(String parkingId){
        Map<String, Integer> res = new HashMap<String, Integer>();
        res.put("code", 1);
        return res;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
