package com.pluralsight.cardealership.dao.test;

import com.pluralsight.cardealership.dao.VehicleDao;
import com.pluralsight.cardealership.dao.VehicleDaoImpl;
import com.pluralsight.cardealership.model.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class VehicleDaoTest {
    static VehicleDao dao;

    @BeforeAll
    static void setup() {
        dao = new VehicleDaoImpl("DB_URL", "DB_USR", "DB_PASS");
    }


    @Test
    void findAllVehicles(){
        List<Vehicle> vehicleList = dao.findAllVehicles();
        int expectedListSize = 8;

        assertEquals(expectedListSize, vehicleList.size());
    }

}


