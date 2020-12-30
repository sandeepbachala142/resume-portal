package io.sandeep.resumeportal;

import io.sandeep.resumeportal.models.User;
import io.sandeep.resumeportal.models.UserProfile;
import io.sandeep.resumeportal.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Controller   // Controller will change the application to SPRING MVC controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home(){
        return "Welcome to HomePage";
    }

    @GetMapping("/edit")
    public String edit(){
        return "Edit Page";
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable String userId, Model model){ // Model is the pojo which returns to view component when we return from this service.
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(()-> new RuntimeException("Not found : " + userId));
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userId",userId.toUpperCase());
        model.addAttribute("userProfile",userProfile);
        return "profile-templates/"+userProfile.getTheme()+"/index";  // This will return the profile template as view because its MVC controller. It wont be a json.  So it will retrun profile.html
    }


}
