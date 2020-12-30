package io.sandeep.resumeportal;

import io.sandeep.resumeportal.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller   // Controller will change the application to SPRING MVC controller
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
    public String view(@PathVariable String userId, Model model){ // Model is the pojo which returns to view component when we return from this service.
        model.addAttribute("userId",userId);
        return "profile-templates/2/index";  // This will return the profile template as view because its MVC controller. It wont be a json.  So it will retrun profile.html
    }


}
