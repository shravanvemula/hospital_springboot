package com.project.hospital.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pat_id")
    private int id;

    @NotNull(message = "is required")
    @Size(min=1,message = "is required")
    @Column(name="pat_firstname")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min=1,message = "is required")
    @Column(name="pat_lastname")
    private String lastName;

    @Column(name="pat_phoneno")
    @NotNull(message = "is required")
    @Size(min=1,message="is required")
    private String phoneNo;


    @OneToMany(mappedBy = "patient",cascade={CascadeType.ALL})
    private List<Appointment> appointments;


    @Override
    public String toString() {
        return '\'' +"Patient Name='" + firstName + '\'' + lastName + '\''
                + ", Patient PhoneNo='" + phoneNo ;
    }
}
