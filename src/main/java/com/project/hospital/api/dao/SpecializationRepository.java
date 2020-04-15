package com.project.hospital.api.dao;

import com.project.hospital.api.entity.Patient;
import com.project.hospital.api.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization,Integer>{


}
