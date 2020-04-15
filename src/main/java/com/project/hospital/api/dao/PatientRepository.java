package com.project.hospital.api.dao;

import com.project.hospital.api.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {


}
