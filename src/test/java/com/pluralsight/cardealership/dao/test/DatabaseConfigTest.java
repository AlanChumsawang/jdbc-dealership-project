package com.pluralsight.cardealership.dao.test;

import com.pluralsight.cardealership.config.DatabaseConfig;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConfigTest {

    @Test
    public void testGetDataSource() {
        BasicDataSource dataSource = DatabaseConfig.getDataSource();

        assertNotNull(dataSource, "DataSource should not be null");
        assertEquals(System.getenv("DB_URL"), dataSource.getUrl(), "Database URL should match");
        assertEquals(System.getenv("DB_USR"), dataSource.getUsername(), "Database username should match");
        assertEquals(System.getenv("DB_PASS"), dataSource.getPassword(), "Database password should match");
    }
}
