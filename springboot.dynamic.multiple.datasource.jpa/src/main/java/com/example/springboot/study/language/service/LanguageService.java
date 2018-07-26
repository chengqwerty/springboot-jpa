package com.example.springboot.study.language.service;

import com.example.springboot.config.TargetDataSource;
import com.example.springboot.study.language.bean.ScStudentBean;
import com.example.springboot.study.language.bean.ScStudentEntity;
import com.example.springboot.study.language.dao.ScStudentBeanRepository;
import com.example.springboot.study.language.dao.ScStudentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    ScStudentEntityRepository scStudentEntityRepository;

    @Autowired
    ScStudentBeanRepository scStudentBeanRepository;

    public List<ScStudentEntity> postGreStudentAll() {
        List<ScStudentEntity> scStudentEntitieList = scStudentEntityRepository.findAll();
        return scStudentEntitieList;
    }

    @TargetDataSource(name = "app.datasource.second")
    public List<ScStudentBean> oracleStudentAll() {
        List<ScStudentBean> scStudentBeanList = scStudentBeanRepository.findAll();
        return scStudentBeanList;
    }
}
