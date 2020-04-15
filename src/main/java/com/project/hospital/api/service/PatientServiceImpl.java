package com.project.hospital.api.service;

import com.project.hospital.api.dao.PatientRepository;

import com.project.hospital.api.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

        private PatientRepository patientRepository;

        @Autowired
        public PatientServiceImpl(PatientRepository patientRepository){
            this.patientRepository=patientRepository;
        }

    @Override
    public List<Patient> findAll() {

            return patientRepository.findAll();
    }

    @Override
    public Patient findById(int id) {
        Optional<Patient> result=patientRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }
        else{
            throw new RuntimeException("Did not found Patient with id "+id);
        }

    }

    @Override
    public void save(Patient patient) {
            patientRepository.save(patient);

    }

    @Override
    public void deleteById(int id) {

            patientRepository.deleteById(id);
    }
}
