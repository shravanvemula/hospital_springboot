package com.project.hospital.api.service;

import com.project.hospital.api.entity.Doctor;
import com.project.hospital.api.entity.Patient;

import java.util.List;

public interface PatientService {


    List<Patient> findAll();
    Patient findById(int id);
    void save(Patient patient);
    void deleteById(int id);


}
