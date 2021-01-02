package io.sandeep.resumeportal.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table
@Data
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String institution;
    private String specialization;
    private String cgpa;
    private LocalDate startDate;
    private  LocalDate endDate;


    public Education(int id, String institution, String specialization, String cgpa, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.institution = institution;
        this.specialization = specialization;
        this.cgpa = cgpa;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Education() {
    }

    public String getFormattedStartDate(){
        return startDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    }
    public String getFormattedEndDate(){
        return endDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    }
}
