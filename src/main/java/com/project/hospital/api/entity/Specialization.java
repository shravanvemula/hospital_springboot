package com.project.hospital.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="specialization")
@Getter
@Setter
@NoArgsConstructor
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="spec_id")
    private int id;

    @Column(name="spec_name")
    @NotNull(message="is required")
    @Size(min=1,message = "is required")
    private String name;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="doctor_specialization",
    joinColumns = @JoinColumn(name="spec_id"),
    inverseJoinColumns = @JoinColumn(name="doc_id"))
    private List<Doctor> doctors;


    public Specialization(String name){
        this.name=name;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "specializationName='" + name + '\'' +
                '}';
    }
}
