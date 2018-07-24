package com.example.springboot.study.language.service;

import com.example.springboot.study.language.bean.ScStudentBean;
import com.example.springboot.study.language.dao.ScStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    ScStudentDao scStudentDao;

    public List<ScStudentBean> studentAll() {
        List<ScStudentBean> scStudentBeans = scStudentDao.findAll();
        return scStudentBeans;
    }
}
