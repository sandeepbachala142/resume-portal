package io.sandeep.resumeportal.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String company;
    private String designation;
    private LocalDate startDate;
    private  LocalDate endDate;
    private String city;
    private String state;
    private boolean currentJob;

    @ElementCollection(targetClass=String.class)
    private List<String> responsibilities = new ArrayList<>();

    public Job() {
    }

    public Job(int id, String company, String designation, LocalDate startDate, LocalDate endDate, String city, String state) {
        this.id = id;
        this.company = company;
        this.designation = designation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.city = city;
        this.state = state;
    }



    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", designation='" + designation + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getFormattedStartDate(){
        return startDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    }
    public String getFormattedEndDate(){
        return endDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    }
}
