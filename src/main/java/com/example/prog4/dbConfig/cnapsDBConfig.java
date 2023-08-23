package com.example.prog4.dbConfig;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.example.prog4.cnaps.repository",
        entityManagerFactoryRef = "cnapsEntityManagerFactory",
        transactionManagerRef = "cnapsTransactionManager"
)
public class cnapsDBConfig {

    @Bean(name = "cnapsDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.cnaps")
    public DataSource cnapsDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/cnaps").build();
    }

    @Bean(name = "cnapsEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cnapsEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("cnapsDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.prog4.cnaps.entity")
                .persistenceUnit("cnaps")
                .build();
    }

    @Bean(name = "cnapsTransactionManager")
    public PlatformTransactionManager cnapsTransactionManager(
            @Qualifier("cnapsEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
