package com.pluralsight.cardealership.config;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class DatabaseConfig {
    private static BasicDataSource dataSource; //object to hold the information required to make a connection to the database

    public static BasicDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl(System.getenv("DB_URL"));
            dataSource.setUsername(System.getenv("DB_USR"));
            dataSource.setPassword(System.getenv("DB_PASS"));
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
}
