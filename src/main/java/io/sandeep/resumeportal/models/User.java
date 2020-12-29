package io.sandeep.resumeportal.models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name="User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String password;
    private String roles;
    private boolean active;

}
