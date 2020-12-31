package io.sandeep.resumeportal;

import io.sandeep.resumeportal.models.Job;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller   // Controller will change the application to SPRING MVC controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/")
    public String home(){
        UserProfile up1 = new UserProfile();
        up1.setId(1);
        up1.setUserName("sandy");
        up1.setDesignation("Java Developer");
        up1.setEmail("sandeep.bachala142@gmail.com");
        up1.setFirstName("Sandeep Kumar");
        up1.setLastName("Bachala");
        up1.setPhone("9848022338");
        up1.setTheme(1);

        Job job1 = new Job(1,"TCS","Java Developer",
                LocalDate.of(2011,11,12),
                LocalDate.of(2014,11,12));
        Job job2 = new Job(2,"Anthem","Senior Java Developer",
                LocalDate.of(2015,1,11),
                LocalDate.of(2020,9,11));
        List<Job> jobs = new ArrayList<>();
        jobs.add(job1);
        jobs.add(job2);
        up1.setJobs(jobs);
        userProfileRepository.save(up1);
        return "profile";
    }

    @GetMapping("/edit")
    public String edit(){
        return "profile";
    }

    @GetMapping("/view/{userId}")
    public String view(@PathVariable String userId, Model model){ // Model is the pojo which returns to view component when we return from this service.
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(()-> new RuntimeException("Not found : " + userId));
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userId",userId.toUpperCase());
        model.addAttribute("userProfile",userProfile);
        System.out.println(userProfile.getJobs().toString());
        return "profile-templates/"+userProfile.getTheme()+"/index";  // This will return the profile template as view because its MVC controller. It wont be a json.  So it will retrun profile.html
    }


}
