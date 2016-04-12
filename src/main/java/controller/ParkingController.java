package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by wyq on 2016/3/30.
 */

@Controller
@RequestMapping("/parking")
@SessionAttributes("puserid")
public class ParkingController {

    @RequestMapping("/appointments")
    public void getAppointments(){

    }

}
