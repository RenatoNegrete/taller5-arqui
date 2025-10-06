package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import jakarta.transaction.UserTransaction;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@Configuration
public class JtaConfig {

    @Bean(name = "transactionManager")
    public JtaTransactionManager jtaTransactionManager() throws Exception {
        UserTransaction userTransaction = new UserTransactionImp();
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }
}
