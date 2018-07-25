package com.example.springboot.config;

import com.example.springboot.study.language.LanguageConfig;
import com.example.springboot.study.math.MathConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackageClasses = LanguageConfig.class,
        entityManagerFactoryRef = "firstEntityManagerFactory",
        transactionManagerRef = "firstPlatformTransactionManager")
public class FirstEntityManagerConfig {

    @Autowired
    @Qualifier("firstDataSource")
    private DataSource firstDataSource;

    @Bean(name = "firstEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return entityManagerFactoryBuilder.dataSource(firstDataSource).packages(LanguageConfig.class)
                .persistenceUnit("firstPersistenceUnit").build();
    }

    @Primary
    @Bean(name = "firstEntityManager")
    public EntityManager firstEntityManager(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return firstEntityManagerFactory(entityManagerFactoryBuilder)
                .getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "firstPlatformTransactionManager")
    public PlatformTransactionManager firstPlatformTransactionManager(EntityManagerFactoryBuilder entityManagerFactoryBuilder) {
        return new JpaTransactionManager(firstEntityManagerFactory(entityManagerFactoryBuilder).getObject());
    }
}
