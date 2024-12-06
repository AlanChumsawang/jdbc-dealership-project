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

    @Test
    void findVehicleByMakeModel() {
        List<Vehicle> vehicleList = dao.findVehicleByMakeModel("Tesla", "Model Y");
        int expectedListSize = 1;

        assertEquals(expectedListSize, vehicleList.size());
    }

    @Test
    void findVehicleByPrice() {
        List<Vehicle> vehicleList = dao.findVehicleByPrice(10000, 20000);
        int expectedListSize = 1;

        assertEquals(expectedListSize, vehicleList.size());
    }

    @Test
    void findVehicleByYear() {
        List<Vehicle> vehicleList = dao.findVehicleByYear(2023);
        int expectedListSize = 2;

        assertEquals(expectedListSize, vehicleList.size());
    }

    @Test
    void findVehicleByColor() {
        List<Vehicle> vehicleList = dao.findVehicleByColor("Blue");
        int expectedListSize = 2;

        assertEquals(expectedListSize, vehicleList.size());
    }

    @Test
    void findVehicleByMileage() {
        List<Vehicle> vehicleList = dao.findVehicleByMileage(30000);
        int expectedListSize = 2;

        assertEquals(expectedListSize, vehicleList.size());
    }

    @Test
    void findVehicleByType() {
        List<Vehicle> vehicleList = dao.findVehicleByType("Sedan");
        int expectedListSize = 3;

        assertEquals(expectedListSize, vehicleList.size());
    }

}


