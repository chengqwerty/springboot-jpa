package com.example.springboot.config;

import com.example.springboot.study.math.MathConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackageClasses = MathConfig.class,
        entityManagerFactoryRef = "secondEntityManagerFactory",
        transactionManagerRef = "secondPlatformTransactionManager")
public class SecondEntityManagerConfig {

    @Autowired
    @Qualifier("secondDataSource")
    private DataSource secondDataSource;

    @Bean(name = "secondEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return entityManagerFactoryBuilder.dataSource(secondDataSource).packages(MathConfig.class)
                .persistenceUnit("secondPersistenceUnit").build();
    }

    @Bean(name = "secondEntityManager")
    public EntityManager secondEntityManager(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return secondEntityManagerFactory(entityManagerFactoryBuilder)
                .getObject().createEntityManager();
    }

    @Bean(name = "secondPlatformTransactionManager")
    public PlatformTransactionManager secondPlatformTransactionManager(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return new JpaTransactionManager(secondEntityManagerFactory(entityManagerFactoryBuilder).getObject());
    }
}
