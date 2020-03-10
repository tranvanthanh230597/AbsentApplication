package com.example.demo.Repository;

import com.example.demo.Model.ClassJoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassJoinRepository extends JpaRepository<ClassJoin,Long> {
}
