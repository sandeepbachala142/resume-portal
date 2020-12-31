package io.sandeep.resumeportal.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

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


    public Job(int id, String company, String designation, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.company = company;
        this.designation = designation;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Job() {
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", designation='" + designation + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
