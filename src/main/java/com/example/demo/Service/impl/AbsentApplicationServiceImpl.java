package com.example.demo.Service.impl;

import com.example.demo.Model.AbsentApplication;
import com.example.demo.Repository.AbsentApplicationRepository;
import com.example.demo.Service.AbsentApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AbsentApplicationServiceImpl implements AbsentApplicationService {
    @Autowired
    private AbsentApplicationRepository absentApplicationRepository;
    @Override
    public Iterable<AbsentApplication> findAll() {
        return absentApplicationRepository.findAll();
    }

    @Override
    public AbsentApplication findById(Long id) {
        return absentApplicationRepository.findById(id).get();
    }

    @Override
    public void save(AbsentApplication absentApplication) {
        absentApplicationRepository.save(absentApplication);
    }

    @Override
    public void remove(Long id) {
        absentApplicationRepository.deleteById(id);
    }
}
