package com.example.demo.Service.impl;

import com.example.demo.Model.ClassJoin;
import com.example.demo.Repository.ClassJoinRepository;
import com.example.demo.Service.ClassJoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassJoinServiceImpl implements ClassJoinService {
    @Autowired
    private ClassJoinRepository classJoinRepository;
    @Override
    public Iterable<ClassJoin> findAll() {
        return classJoinRepository.findAll();
    }

    @Override
    public ClassJoin findById(Long id) {
        return classJoinRepository.findById(id).get();
    }

    @Override
    public void save(ClassJoin classJoin) {
        classJoinRepository.save(classJoin);
    }

    @Override
    public void remove(Long id) {
        classJoinRepository.deleteById(id);
    }
}
