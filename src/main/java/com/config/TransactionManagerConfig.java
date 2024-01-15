package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class TransactionManagerConfig {

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource getBds) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(getBds);
        return transactionManager;
    }
}

