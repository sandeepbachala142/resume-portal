package io.sandeep.resumeportal.models;

import lombok.Data;
import org.hibernate.criterion.LikeExpression;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    private int theme;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String designation;

    @ElementCollection(targetClass=String.class)
    private List<String> skills= new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="education_id")
    List<Education> educationList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "job_id")
    List<Job> jobs = new ArrayList<>();

    private String summary;

    public String getSkillsList(){
        String skillsList="";
        for(String skill : skills){
            if(!"".equalsIgnoreCase(skillsList))
                skillsList = skillsList+", "+skill;
            else
                skillsList=skill;
        }
        return  skillsList;
    }
}
