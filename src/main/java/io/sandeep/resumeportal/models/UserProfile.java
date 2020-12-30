package io.sandeep.resumeportal.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    private int theme;
    private String userName;
    private String summary;
}
