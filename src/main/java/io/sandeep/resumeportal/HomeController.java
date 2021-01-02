package io.sandeep.resumeportal;

import io.sandeep.resumeportal.models.Education;
import io.sandeep.resumeportal.models.Job;
import io.sandeep.resumeportal.models.User;
import io.sandeep.resumeportal.models.UserProfile;
import io.sandeep.resumeportal.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller   // Controller will change the application to SPRING MVC controller
public class HomeController {

    @Autowired
    UserProfileRepository userProfileRepository;

    @GetMapping("/sampledata")
    public String home(Principal principal,Model model){
        Optional<UserProfile> upOptional= userProfileRepository.findByUserName("sandy");
        upOptional.orElseThrow(()-> new RuntimeException("Not found : "));
        UserProfile up1 = upOptional.get();

        Job job1 = new Job(1,"TCS","Java Developer",
                LocalDate.of(2011,11,12)
                ,"Bangalore","India");
        job1.setEndDate(LocalDate.of(2014,11,12));
        job1.getResponsibilities().add("Involved in SDLC Requirements gathering, Analysis, Design, Development and Testing of application using AGILE methodology");
        job1.getResponsibilities().add("Involved in development of both middle layer and front end development with IBM Portlets.");
        job1.getResponsibilities().add("Implementing Disaster Recover activity whenever it’s required.");
        job1.getResponsibilities().add("Used Struts framework to implement portlets for UI and middle layer.");
        job1.getResponsibilities().add("Worked extensively on Web Services, XML and JMS. ");


        Job job2 = new Job(2,"Verizon","Senior Java Developer",
                LocalDate.of(2015,1,11),
                "Irving","TX");
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

        // int id, String institution, String specialization, String cgpa, LocalDate startDate, LocalDate endDate
        Education ed1 = new Education(1,"Gandhi Institute of Technology and Management, India",
                "Bachelor of Technology in Electronics and Communications Engineering","8",
                LocalDate.of(2007,4,11),
                LocalDate.of(2011,3,11));

        Education ed2 = new Education(2,"Wilmington University, Delaware, USA",
                "Masters in Information Assurance","8",
                LocalDate.of(2016,1,11),
                LocalDate.of(2017,10,11));

        up1.getEducationList().clear();
        up1.getEducationList().add(ed1);
        up1.getEducationList().add(ed2);

        up1.getSkills().clear();
        up1.getSkills().add("Java");
        up1.getSkills().add("Spring framework");
        up1.getSkills().add("Microservices");
        up1.getSkills().add("J2EE");
        up1.getSkills().add("Hibernate");
        up1.getSkills().add("Webservices");
        up1.getSkills().add("REST");

        userProfileRepository.save(up1);
        if(principal!=null)
            model.addAttribute("loggedin",principal.getName());
        return "index";
    }

    @GetMapping("/")
    public String homePage(Principal principal,Model model){
        if(principal!=null)
            model.addAttribute("loggedin",principal.getName());

        return "index";
    }
    @GetMapping("/edit")
    public String edit(Principal principal, Model model,@RequestParam(required = false) String add){
         /*Pricipal is the object which is provided by spring security
        that gives the information of logged in USER.
        */
        String userId=principal.getName();
        Optional<UserProfile> upOptional= userProfileRepository.findByUserName(userId);
        upOptional.orElseThrow(()-> new RuntimeException("Not found : "));
        UserProfile up = upOptional.get();
        if("job".equalsIgnoreCase(add)){
            up.getJobs().add(new Job());
        }else if("education".equalsIgnoreCase(add)){
            up.getEducationList().add(new Education());
        }else if("skill".equalsIgnoreCase(add)){
            up.getSkills().add(new String());
        }

        model.addAttribute("userId",userId);
        model.addAttribute("userProfile",up);
        return "profile-edit";
    }

    @PostMapping("/edit")
    public String postEdit(Principal principal, Model model, @ModelAttribute UserProfile userProfile){
        String userId=principal.getName();
        Optional<UserProfile> upOptional= userProfileRepository.findByUserName(userId);
        upOptional.orElseThrow(()-> new RuntimeException("Not found : "));
        UserProfile up = upOptional.get();
        userProfile.setId(up.getId());
        userProfile.setUserName(up.getUserName());
        userProfileRepository.save(userProfile);
        model.addAttribute("userId",principal.getName());
        return "redirect:/view/"+userId;
    }


    @GetMapping("/delete")
    public String delete(Principal principal, Model model, @RequestParam String type, @RequestParam int index){
        String userId = principal.getName();
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(() -> new RuntimeException("Not found: " + userId));
        UserProfile userProfile = userProfileOptional.get();
        if ("job".equals(type)) {
            userProfile.getJobs().remove(index);
        } else if ("education".equals(type)) {
            userProfile.getEducationList().remove(index);
        } else if ("skill".equals(type)) {
            userProfile.getSkills().remove(index);
        }
        userProfileRepository.save(userProfile);
        return "redirect:/edit";
    }

    @GetMapping("/view/{userId}")
    public String view(Principal principal,@PathVariable String userId, Model model){ // Model is the pojo which returns to view component when we return from this service.
        if(principal!=null && userId.equalsIgnoreCase(principal.getName())){
            model.addAttribute("owner","true");
        }
        Optional<UserProfile> userProfileOptional = userProfileRepository.findByUserName(userId);
        userProfileOptional.orElseThrow(()-> new RuntimeException("Not found : " + userId));
        UserProfile userProfile = userProfileOptional.get();
        model.addAttribute("userId",userId.toUpperCase());
        model.addAttribute("userProfile",userProfile);
        System.out.println(userProfile.getJobs().toString());
        return "profile-templates/"+userProfile.getTheme()+"/index";  // This will return the profile template as view because its MVC controller. It wont be a json.  So it will retrun index.html
    }


}
