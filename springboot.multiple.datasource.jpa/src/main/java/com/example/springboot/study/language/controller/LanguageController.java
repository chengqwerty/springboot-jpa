package com.example.springboot.study.language.controller;

import com.example.springboot.study.language.bean.ScStudentBean;
import com.example.springboot.study.language.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/language")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @RequestMapping()
    public String language() {
        return "This is language!";
    }

    @RequestMapping("/studentAll")
    public List<ScStudentBean> studentAll() {
        List<ScStudentBean> scStudentBeans = languageService.studentAll();
        return scStudentBeans;
    }

    @RequestMapping("/saveStudentTest")
    public ScStudentBean saveStudentTest() {
        ScStudentBean scStudentBean =languageService.saveStudentTest();
        return scStudentBean;
    }
}
