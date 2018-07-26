package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringbootDynamicMultipleDatasourceJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDynamicMultipleDatasourceJpaApplication.class, args);
    }
}
