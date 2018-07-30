package com.example.springboot.config.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

    Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    public DynamicDataSourceAspect() {

    }

    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        String dataSourcePrefix = targetDataSource.name();
        if(!DynamicDataSourceContextHolder.isContainsDataSource(dataSourcePrefix)) {
            logger.error("没有找到datasource {}, 或者 {} 没有注册.", dataSourcePrefix, dataSourcePrefix);
        } else {
            logger.info("切换 datasource 到 {}", dataSourcePrefix);
            DynamicDataSourceContextHolder.setDataSource(dataSourcePrefix);
        }
    }

    @After("@annotation(targetDataSource)")
    public void clearDataSource(JoinPoint joinPoint, TargetDataSource targetDataSource) {
        logger.info("返回 primary datasource.");
        DynamicDataSourceContextHolder.clearDataSource();
    }
}
