package com.example.demo.Repository;

import com.example.demo.Model.AbsentApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsentApplicationRepository extends JpaRepository<AbsentApplication,Long> {
}
