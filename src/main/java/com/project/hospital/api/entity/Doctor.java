package com.project.hospital.api.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="doctor")
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "is required")
    @Column(name="doc_id")
    private int id;

    @Column(name="doc_firstname")
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String firstName;

    @Column(name="doc_lastname")
    @Size(min = 1, message = "is required")
    @NotNull(message = "is required")
    private String lastName;

    @Column(name="doc_phoneno")
    @Size(min = 1, message = "is required")
    @NotNull(message = "is required")
    private String phoneNo;

    @OneToMany(mappedBy = "doctor",cascade={CascadeType.ALL})
    private List<Appointment> appointments;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="doctor_specialization",
    joinColumns = @JoinColumn(name="doc_id"),
    inverseJoinColumns = @JoinColumn(name="spec_id"))
    private List<Specialization> specializations;


    public Doctor(int id,String firstName,String lastName,String phoneNo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
    }

    public Doctor() {
    }

    @Override
    public String toString() {
        return '\'' +"Doctor Name='" + firstName + '\'' + lastName + '\''
                        + ", Patient PhoneNo='" + phoneNo ;
    }

}
