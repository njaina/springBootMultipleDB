package com.example.prog4.config;

import java.util.HashMap;
import java.util.Objects;
import javax.sql.DataSource;
import lombok.AllArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource({"classpath:application.properties"})
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.example.prog4.cnaps.repository"},
        entityManagerFactoryRef = "cnapsEntityManagerFactory",
        transactionManagerRef = "cnapsTransactionManager")
@AllArgsConstructor
public class CnapsConf {
    private final Environment env;

    @Bean(name = "cnapsManagementDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("cnaps.datasource.url"));
        dataSource.setUsername(env.getProperty("cnaps.datasource.username"));
        dataSource.setPassword(env.getProperty("cnaps.datasource.password"));
        dataSource.setDriverClassName(
                Objects.requireNonNull(env.getProperty("cnaps.datasource.driver-class-name")));
        return dataSource;
    }


    @Bean("cnapsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        var entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(dataSource());
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManager.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("cnaps.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

        entityManager.setJpaPropertyMap(properties);
        entityManager.setPackagesToScan("com.example.prog4.cnaps.entity");
        return entityManager;
    }

    @Bean("cnapsTransactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
        return transactionManager;
    }

    @Bean
    @Primary
    public Flyway cnapsFlyway() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource())
                .locations("classpath:/cnaps")
                .load();
        flyway.migrate();
        return flyway;
    }
}