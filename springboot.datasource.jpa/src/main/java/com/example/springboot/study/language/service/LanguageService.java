package com.example.springboot.study.language.service;

import com.example.springboot.study.language.bean.ScStudentBean;
import com.example.springboot.study.language.dao.ScStudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class LanguageService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ScStudentDao scStudentDao;

    public List<ScStudentBean> studentAll() {
        List<ScStudentBean> scStudentBeans = scStudentDao.findAll();
        return scStudentBeans;
    }

    public Long countByStuName(String stuName) {
        Long stuNum = scStudentDao.countByStuName(stuName);
        return stuNum;
    }

    public List<ScStudentBean> findByGidAndAge(Integer gid, Integer age) {
        List<ScStudentBean> scStudentBeanList = scStudentDao.findByGidAndAge(gid, age);
        return scStudentBeanList;
    }

    public List<ScStudentBean> queryStudentAll() {
        Query query = entityManager.createQuery("select scs from ScStudentBean scs");
        List<ScStudentBean> scStudentBeanList = query.getResultList();
        return scStudentBeanList;
    }

    @Transactional
    public void saveManyStudent() {
        for(int i=0;i<100;i++) {
            ScStudentBean scStudentBean = new ScStudentBean();
            scStudentBean.setAge(19);
            scStudentBean.setGid(1);
            scStudentBean.setStuName("chengcheng");
            scStudentDao.save(scStudentBean);
        }
    }

    @Transactional
    public ScStudentBean saveStudent(ScStudentBean scStudentBean) {
        ScStudentBean saveScStudentBean = scStudentDao.save(scStudentBean);
        return saveScStudentBean;
    }

    @Transactional
    public ScStudentBean updateStudent(ScStudentBean scStudentBean) {
        ScStudentBean updateScStudentBean = scStudentDao.save(scStudentBean);
        return updateScStudentBean;
    }

    @Transactional
    public ScStudentBean deleteStudent(ScStudentBean scStudentBean) {
        scStudentDao.delete(scStudentBean);
        return scStudentBean;
    }

    public List<ScStudentBean> criteriaQueryStudentAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ScStudentBean> criteriaQuery = criteriaBuilder.createQuery(ScStudentBean.class);
        Root<ScStudentBean> scStudentBeanRoot = criteriaQuery.from(ScStudentBean.class);
        criteriaQuery.select(scStudentBeanRoot);
        List<ScStudentBean> scStudentBeanList = entityManager.createQuery(criteriaQuery).getResultList();
        return scStudentBeanList;
    }
}
