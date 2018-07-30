package com.example.springboot.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.StandardServletEnvironment;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDataSourceConfiguration {

    Logger logger = LoggerFactory.getLogger(DynamicDataSourceConfiguration.class);

    private static final String PRIMARYPREFIX = "spring.datasource";
    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DRIVERCLASSNAME = "driver-class-name";
    private static final String SEPARATE = ".";

    private static final String SLAVEPREFIXNAMES = "datasource.slave.prefix";

    @Autowired
    StandardServletEnvironment environment;


    public DataSource initPrimaryDataSource() {
        logger.info("开始初始化 primary datasource.....");
        String url = environment.getProperty(PRIMARYPREFIX + SEPARATE + URL);
        String username = environment.getProperty(PRIMARYPREFIX + SEPARATE + USERNAME);
        String password = environment.getProperty(PRIMARYPREFIX + SEPARATE + PASSWORD);
        String driverClassName = environment.getProperty(PRIMARYPREFIX + SEPARATE + DRIVERCLASSNAME);
        DynamicDataSourceContextHolder.dataSourcePrefixs.add(PRIMARYPREFIX);
        return DataSourceBuilder.create().type(HikariDataSource.class).url(url)
                .username(username).password(password).driverClassName(driverClassName).build();
    }

    public Map<Object, Object> initSlaveDataSource() {
        String slaveNames = environment.getProperty(SLAVEPREFIXNAMES);
        logger.info("开始初始化 slave datasource, datasource.slave.prefix is {}", slaveNames);
        if (!StringUtils.isEmpty(slaveNames)) {
            String[] slavePrefixs = StringUtils.split(slaveNames, ",");
            Map<Object, Object> mapDataSource = new HashMap<>();
            for(String slavePrefix: slavePrefixs) {
                String url = environment.getProperty(slavePrefix + SEPARATE + URL);
                String username = environment.getProperty(slavePrefix + SEPARATE + USERNAME);
                String password = environment.getProperty(slavePrefix + SEPARATE + PASSWORD);
                String driverClassName = environment.getProperty(slavePrefix + SEPARATE + DRIVERCLASSNAME);
                DynamicDataSourceContextHolder.dataSourcePrefixs.add(slavePrefix);
                logger.info("添加datasource {}.", slavePrefix);
                DataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).url(url)
                        .username(username).password(password).driverClassName(driverClassName).build();
                mapDataSource.put(slavePrefix, dataSource);
            }
            return mapDataSource;
        }
        logger.error("slave datasource 初始化失败，{}为空", slaveNames);
        return null;
    }

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        logger.info("开始动态datasource注册.");
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        DataSource primaryDataSource = initPrimaryDataSource();
        logger.info("primary datasource 初始化完毕.");
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSource);
        Map<Object, Object> slaveDataSources = initSlaveDataSource();
        logger.info("slave datasource 初始化完毕.");
        dynamicDataSource.setTargetDataSources(slaveDataSources);
        logger.info("动态datasource注册完毕.");
        return dynamicDataSource;
    }
}
