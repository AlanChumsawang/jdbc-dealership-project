package com.pluralsight.cardealership.config;

import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseConfig {
    private static BasicDataSource dataSource;

    public static BasicDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl("jdbc:mysql://localhost:3306/cardealership");
            dataSource.setUsername("root");
            dataSource.setPassword("password");
        }
        return dataSource;
    }
}
