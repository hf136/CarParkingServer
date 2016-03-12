package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by wuyueqiu on 16-3-9.
 */
@Controller
@SessionAttributes("id")
public class MainController {

    @RequestMapping("/u")
    @ResponseBody
    public String test(@ModelAttribute("id") Integer id){
        return id.toString();
    }
}
