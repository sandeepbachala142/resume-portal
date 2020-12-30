package io.sandeep.resumeportal;

import io.sandeep.resumeportal.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){

        User u = new User();

        return "Welcome to HomePage";
    }

    @GetMapping("/edit")
    public String edit(){
        User u = new User();
        return "Edit Page";
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable String userId, Model model){
        model.addAttribute("userId",userId);
        return "profile";
    }


}
