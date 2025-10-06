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
    basePackages = "com.example.demo.repository.inventario",
    entityManagerFactoryRef = "inventarioEntityManagerFactory",
    transactionManagerRef = "transactionManager"
)
public class InventarioDBConfig {

    @Primary
    @Bean(name = "inventarioDataSource")
    public DataSource inventarioDataSource() {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setUniqueResourceName("inventarioDB");
        ds.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        Properties xaProps = new Properties();
        xaProps.setProperty("databaseName", "inventario_db");
        xaProps.setProperty("serverName", "localhost");
        xaProps.setProperty("portNumber", "3307");
        xaProps.setProperty("user", "root");
        xaProps.setProperty("password", "root");
        ds.setXaProperties(xaProps);
        ds.setPoolSize(5);
        return ds;
    }

    @Primary
    @Bean(name = "inventarioEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean inventarioEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("inventarioDataSource") DataSource dataSource) {

        Map<String, Object> props = new HashMap<>();
        props.put("hibernate.transaction.factory_class", "org.hibernate.engine.transaction.jta.platform.internal.JtaPlatformInitiator");
        props.put("hibernate.transaction.jta.platform", "com.atomikos.icatch.jta.hibernate.AtomikosJtaPlatform");
        props.put("jakarta.persistence.transactionType", "JTA");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.show_sql", "true");

        return builder
                .dataSource(inventarioDataSource())
                .packages("com.example.demo.model")
                .persistenceUnit("inventarioPU")
                .properties(props)
                .jta(true)
                .build();
    }
}

