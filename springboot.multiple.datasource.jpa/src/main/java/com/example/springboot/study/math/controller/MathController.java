package com.example.springboot.study.math.controller;

import com.example.springboot.study.math.bean.ScStudentBean;
import com.example.springboot.study.math.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("math")
public class MathController {

    @Autowired
    private MathService mathService;

    @RequestMapping()
    public String math() {
        return "This is math!";
    }

    @RequestMapping("/studentAll")
    public List<ScStudentBean> studentAll() {
        List<ScStudentBean> scStudentBeans = mathService.studentAll();
        return scStudentBeans;
    }

    @RequestMapping("/saveStudentTest")
    public ScStudentBean saveStudentTest() {
        ScStudentBean scStudentBean = mathService.saveStudentTest();
        return scStudentBean;
    }

}
