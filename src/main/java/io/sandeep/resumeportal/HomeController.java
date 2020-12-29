package io.sandeep.resumeportal;

import io.sandeep.resumeportal.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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


}
