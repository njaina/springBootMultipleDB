package com.example.prog4.dbConfig;

import com.example.prog4.employee.repository.SessionRepository;
import com.example.prog4.employee.repository.UserRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.prog4.employee.repository",
        entityManagerFactoryRef = "employeeEntityManagerFactory",
        transactionManagerRef = "employeeTransactionManager"
)
public class employeeDBConfig {

    @Primary
    @Bean(name = "employeeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.employee")
    public DataSource employeeDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/employee").
        username("postgres").password("tataro.").
        build();
    }

    @Primary
    @Bean(name = "employeeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("employeeDataSource") DataSource dataSource) {
        return builder
               .dataSource(dataSource)
               .packages("com.example.prog4.employee.entity")
               .persistenceUnit("employee")
               .build();
    }

    @Primary
    @Bean(name = "employeeTransactionManager")
    public PlatformTransactionManager employeeTransactionManager(
            @Qualifier("employeeEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
