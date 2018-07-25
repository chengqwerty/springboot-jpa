package com.example.springboot.study.math.dao;

import com.example.springboot.study.math.bean.ScStudentBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MathScStudentDao extends JpaRepository<ScStudentBean, Long> {
}
