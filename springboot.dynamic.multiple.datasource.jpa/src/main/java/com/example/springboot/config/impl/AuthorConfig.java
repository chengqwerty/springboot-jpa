package com.example.springboot.config.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "cc.author")
public class AuthorConfig {

    private String name;
    private String birthday;


}
