package com.example.springboot.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang3.StringUtils;
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
        if (!StringUtils.isEmpty(slaveNames)) {
            String[] slavePrefixs = StringUtils.split(slaveNames, ",");
            Map<Object, Object> mapDataSource = new HashMap<>();
            for(String slavePrefix: slavePrefixs) {
                String url = environment.getProperty(slavePrefix + SEPARATE + URL);
                String username = environment.getProperty(slavePrefix + SEPARATE + USERNAME);
                String password = environment.getProperty(slavePrefix + SEPARATE + PASSWORD);
                String driverClassName = environment.getProperty(slavePrefix + SEPARATE + DRIVERCLASSNAME);
                DynamicDataSourceContextHolder.dataSourcePrefixs.add(slavePrefix);
                System.out.println(slavePrefix);
                DataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).url(url)
                        .username(username).password(password).driverClassName(driverClassName).build();
                mapDataSource.put(slavePrefix, dataSource);
            }
            return mapDataSource;
        }
        return null;
    }

    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        DataSource primaryDataSource = initPrimaryDataSource();
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSource);
        Map<Object, Object> slaveDataSources = initSlaveDataSource();
        dynamicDataSource.setTargetDataSources(slaveDataSources);
        return dynamicDataSource;
    }
}
