package com.example.springboot.study.language.dao;

import com.example.springboot.study.language.bean.ScStudentBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScStudentDao extends JpaRepository<ScStudentBean, Long> {
}
