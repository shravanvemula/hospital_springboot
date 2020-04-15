package com.project.hospital.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="appointment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @Column(name="app_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "is required")
    private int id;

    @Column(name="app_date")
    @NotNull(message = "is required")
    @Size(min=1,message = "is required")
    private String appointmentDate;

    @Column(name="app_time")
    @NotNull(message = "is required")
    @Size(min=1,message ="is required")
    private String appointmentTime;

    @Column(name="app_reason")
    @NotNull(message = "is required")
    @Size(min=1,message ="is required")
    private String appointmentReason;


    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="doc_id")
    private Doctor doctor;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="pat_id")
    private Patient patient;

    @Override
    public String toString() {
        return "Appointment Date='" + appointmentDate + '\'' +
                ", Appointment Time='" + appointmentTime + '\'' +
                ",Appointment Reason='" + appointmentReason + '\'' +
                ", " + patient + '\'' +", " + doctor;
    }
}
