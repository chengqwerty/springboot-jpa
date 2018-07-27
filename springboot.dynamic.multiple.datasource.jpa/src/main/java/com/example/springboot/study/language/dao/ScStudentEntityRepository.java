package com.example.springboot.study.language.dao;

import com.example.springboot.study.language.bean.ScStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScStudentEntityRepository extends JpaRepository<ScStudentEntity, Integer> {
}
