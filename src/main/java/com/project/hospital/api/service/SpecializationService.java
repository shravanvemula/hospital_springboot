package com.project.hospital.api.service;

import com.project.hospital.api.entity.Patient;
import com.project.hospital.api.entity.Specialization;

import java.util.List;

public interface SpecializationService {


    List<Specialization> findAll();
    Specialization findById(int id);
    void save(Specialization specialization);
    void deleteById(int id);

}