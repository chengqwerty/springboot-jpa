package com.example.springboot.study.language.service;

import com.example.springboot.config.datasource.TargetDataSource;
import com.example.springboot.study.language.bean.ScStudentBean;
import com.example.springboot.study.language.bean.ScStudentEntity;
import com.example.springboot.study.language.dao.ScStudentBeanRepository;
import com.example.springboot.study.language.dao.ScStudentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LanguageService {

    @Autowired
    ScStudentEntityRepository scStudentEntityRepository;

    @Autowired
    ScStudentBeanRepository scStudentBeanRepository;

    public ScStudentEntity postGreStudentById(int sid) {
        ScStudentEntity scStudentEntity = scStudentEntityRepository.findById(sid).get();
        return scStudentEntity;
    }

    public List<ScStudentEntity> postGreStudentAll() {
        List<ScStudentEntity> scStudentEntitieList = scStudentEntityRepository.findAll();
        return scStudentEntitieList;
    }

    /**
     * aop注解自动切换数据源
     * @return
     */
    @TargetDataSource(name = "app.datasource.second")
    public List<ScStudentBean> oracleStudentAll() {
        List<ScStudentBean> scStudentBeanList = scStudentBeanRepository.findAll();
        return scStudentBeanList;
    }

    @Transactional
    public ScStudentEntity postGreSaveStudent(ScStudentEntity scStudentEntity) {
        ScStudentEntity newScStudentEntity = scStudentEntityRepository.save(scStudentEntity);
        int a = 1/0; // 手动抛出异常，测试spring事务处理
        return newScStudentEntity;
    }

    /**
     * aop注解自动切换数据源，事务测试
     * @param scStudentBean
     * @return
     */
    @TargetDataSource(name = "app.datasource.second")
    @Transactional
    public ScStudentBean oracleSaveStudent(ScStudentBean scStudentBean) {
        ScStudentBean newScStudentBean = scStudentBeanRepository.save(scStudentBean);
        int a = 1/0; // 手动抛出异常，测试spring事务处理
        return newScStudentBean;
    }
}
