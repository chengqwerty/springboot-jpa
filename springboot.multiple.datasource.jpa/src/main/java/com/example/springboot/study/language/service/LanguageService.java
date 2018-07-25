package com.example.springboot.study.language.service;

import com.example.springboot.study.language.bean.ScStudentBean;
import com.example.springboot.study.language.dao.ScStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    private ScStudentDao scStudentDao;

    public List<ScStudentBean> studentAll() {
        List<ScStudentBean> scStudentBeans = scStudentDao.findAll();
        return scStudentBeans;
    }

    @Transactional
    public ScStudentBean saveStudentTest() {
        ScStudentBean scStudentBean = new ScStudentBean();
        scStudentBean.setAge(19);
        scStudentBean.setGid(1);
        scStudentBean.setStuName("lizimeng");
        ScStudentBean newScStudentBean = scStudentDao.save(scStudentBean);
        return newScStudentBean;
    }

}
