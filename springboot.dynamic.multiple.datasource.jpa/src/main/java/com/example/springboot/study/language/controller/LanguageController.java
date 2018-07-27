package com.example.springboot.study.language.controller;

import com.example.springboot.config.DataSourceSlavePrefix;
import com.example.springboot.study.language.bean.ScStudentBean;
import com.example.springboot.study.language.bean.ScStudentEntity;
import com.example.springboot.study.language.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("language")
public class LanguageController {

    @Autowired
    private DataSourceSlavePrefix dataSourceSlavePrefix;

    @Autowired
    private LanguageService languageService;

    @RequestMapping()
    public String language() {
        return dataSourceSlavePrefix.getPrefix();
    }

    @RequestMapping("/postGre/studentById")
    public ScStudentEntity postGreStudentById(int sid) {
        ScStudentEntity scStudentEntity = languageService.postGreStudentById(sid);
        return scStudentEntity;
    }

    @RequestMapping("/postGre/studentAll")
    public List<ScStudentEntity> postGreStudentAll() {
        List<ScStudentEntity> scStudentEntityList = languageService.postGreStudentAll();
        return scStudentEntityList;
    }

    @RequestMapping("/oracle/studentAll")
    public List<ScStudentBean> oracleStudentAll() {
        List<ScStudentBean> scStudentBeanList = languageService.oracleStudentAll();
        return scStudentBeanList;
    }

    @RequestMapping("/postGre/saveStudent")
    public ScStudentEntity postGreSaveStudent(String studentName) {
        ScStudentEntity scStudentEntity = languageService.postGreSaveStudent(studentName);
        return scStudentEntity;
    }

    @RequestMapping("/oracle/saveStudent")
    public ScStudentBean oracleSaveStudent(String studentName) {
        ScStudentBean scStudentBean = languageService.oracleSaveStudent(studentName);
        return scStudentBean;
    }

}
