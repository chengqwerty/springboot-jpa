package com.example.springboot.study.language.dao;

import com.example.springboot.study.language.bean.ScStudentBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScStudentDao extends JpaRepository<ScStudentBean, Long> {

    long countByStuName(String stuName);

    List<ScStudentBean> findByGidAndAge(Integer gid, Integer age);
}
