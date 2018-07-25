package com.example.springboot.study.math.service;

import com.example.springboot.study.math.bean.ScStudentBean;
import com.example.springboot.study.math.dao.MathScStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MathService {

    @Autowired
    private MathScStudentDao mathScStudentDao;

    public List<ScStudentBean> studentAll() {
        List<ScStudentBean> scStudentBeans = mathScStudentDao.findAll();
        return scStudentBeans;
    }

    @Transactional("secondPlatformTransactionManager")
    public ScStudentBean saveStudentTest() {
        ScStudentBean scStudentBean = new ScStudentBean();
        scStudentBean.setGid((long)1);
        scStudentBean.setStuName("caiyuanyuan");
        ScStudentBean newScStudentBean = mathScStudentDao.save(scStudentBean);
        return newScStudentBean;
    }
}
