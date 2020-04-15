package com.project.hospital.api.service;

import com.project.hospital.api.dao.DoctorRepository;
import com.project.hospital.api.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService{

    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository){
        this.doctorRepository=doctorRepository;
    }

    @Override
    @Transactional
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    @Transactional
    public Doctor findById(int id) {

        Optional<Doctor> result=doctorRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new RuntimeException("Did not found Doctor with id "+id);
        }

    }

    @Override
    @Transactional
    public void save(Doctor doctor) {

        doctorRepository.save(doctor);
    }

    @Override
    @Transactional
    public void deleteById(int id) {

        doctorRepository.deleteById(id);

    }
}
