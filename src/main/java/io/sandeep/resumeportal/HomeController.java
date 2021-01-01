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
        Optional<UserProfile> upOptional= userProfileRepository.findByUserName("sandy");
        upOptional.orElseThrow(()-> new RuntimeException("Not found : "));
        UserProfile up1 = upOptional.get();

        Job job1 = new Job(1,"TCS","Java Developer",
                LocalDate.of(2011,11,12),
                LocalDate.of(2014,11,12),"Bangalore","India");
        job1.getResponsibilities().add("Involved in SDLC Requirements gathering, Analysis, Design, Development and Testing of application using AGILE methodology");
        job1.getResponsibilities().add("Involved in development of both middle layer and front end development with IBM Portlets.");
        job1.getResponsibilities().add("Implementing Disaster Recover activity whenever it’s required.");
        job1.getResponsibilities().add("Used Struts framework to implement portlets for UI and middle layer.");
        job1.getResponsibilities().add("Worked extensively on Web Services, XML and JMS. ");


        Job job2 = new Job(2,"Verizon","Senior Java Developer",
                LocalDate.of(2015,1,11),
                LocalDate.of(2020,9,11),"Irving","TX");
        job2.getResponsibilities().add("Requirement gathering, analysis, and designing solutions for enterprise-level web applications and RESTful APIs as per the business requirements.");
        job2.getResponsibilities().add("Develop robust, scalable, REST Web services, and MicroServices using Java 8, Spring Boot, Spring Cloud, Redis, Hibernate, API Gateway, etc.");
        job2.getResponsibilities().add("Worked on APIGEE to create API proxies for the backend web services in multiple microservices.");
        job2.getResponsibilities().add("Worked on Apigee security policies for validating the request, authentication, and authorization.");
        job2.getResponsibilities().add("Deep-dive investigation and root cause analysis of severe code issues and bugs.");
        job2.getResponsibilities().add("Develop POCs for demo to Business and obtain necessary approvals for production releases.");
        job2.setCurrentJob(true);

        up1.getJobs().clear();
        up1.getJobs().add(job1);
        up1.getJobs().add(job2);
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
