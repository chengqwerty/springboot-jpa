package com.example.springboot.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

    public DynamicDataSourceAspect() {

    }

    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        String dataSourcePrefix = targetDataSource.name();
        if(!DynamicDataSourceContextHolder.isContainsDataSource(dataSourcePrefix)) {

        } else {
            System.out.println(dataSourcePrefix);
            DynamicDataSourceContextHolder.setDataSource(dataSourcePrefix);
        }
    }

    @After("@annotation(targetDataSource)")
    public void clearDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        DynamicDataSourceContextHolder.clearDataSource();
    }
}
