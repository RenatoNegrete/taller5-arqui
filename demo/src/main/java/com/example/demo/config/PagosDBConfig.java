package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.atomikos.jdbc.AtomikosDataSourceBean;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.example.demo.repository.pagos",
    entityManagerFactoryRef = "pagosEntityManagerFactory",
    transactionManagerRef = "transactionManager"
)
public class PagosDBConfig {

    @Primary
    @Bean(name = "pagosDataSource")
    public DataSource pagosDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName("pagosDB");
        ds.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        Properties xaProps = new Properties();
        xaProps.setProperty("databaseName", "pagos_db");
        xaProps.setProperty("serverName", "localhost");
        xaProps.setProperty("portNumber", "3308");
        xaProps.setProperty("user", "root");
        xaProps.setProperty("password", "root");
        ds.setXaProperties(xaProps);
        ds.setPoolSize(5);
        return ds;
    }

    @Primary
    @Bean(name = "pagosEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean pagosEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("pagosDataSource") DataSource dataSource) {

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.transaction.factory_class", "org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator");
        props.put("hibernate.transaction.jta.platform", "com.atomikos.icatch.jta.hibernate.AtomikosJtaPlatform");
        props.put("jakarta.persistence.transactionType", "JTA");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.show_sql", "true");

        return builder
                .dataSource(pagosDataSource())
                .packages("com.example.demo.model")
                .persistenceUnit("pagosPU")
                .properties(props)
                .jta(true)
                .build();
    }
}

