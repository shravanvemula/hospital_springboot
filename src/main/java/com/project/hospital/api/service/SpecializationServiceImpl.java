package com.project.hospital.api.service;

import com.project.hospital.api.dao.SpecializationRepository;
import com.project.hospital.api.entity.Specialization;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;

public class SpecializationServiceImpl implements SpecializationService {

    private SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationServiceImpl(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }

    @Override
    public Specialization findById(int id) {
        Optional<Specialization> result=specializationRepository.findById(id);

        if(result!=null){
            return result.get();
        }
        else{
            throw new RuntimeException("Did not find specialization with id "+id);
        }

    }

    @Override
    public void save(Specialization specialization) {
        specializationRepository.save(specialization);
    }

    @Override
    public void deleteById(int id) {
        specializationRepository.deleteById(id);

    }
}
