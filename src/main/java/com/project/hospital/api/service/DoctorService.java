package com.project.hospital.api.service;

import com.project.hospital.api.entity.Doctor;


import java.util.List;

public interface DoctorService {

    List<Doctor> findAll();
    Doctor findById(int id);
    void save(Doctor doctor);
    void deleteById(int id);
}
