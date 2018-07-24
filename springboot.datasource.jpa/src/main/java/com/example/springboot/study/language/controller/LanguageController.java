package com.example.springboot.study.language.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/language")
public class LanguageController {

    @RequestMapping()
    public String language() {

        return "This is language!";
    }

}
