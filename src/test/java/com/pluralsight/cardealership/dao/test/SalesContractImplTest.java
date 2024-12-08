package com.pluralsight.cardealership.dao.test;

import com.pluralsight.cardealership.dao.SalesContractImpl;
import com.pluralsight.cardealership.model.SalesContract;
import com.pluralsight.cardealership.model.Vehicle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalesContractImplTest {
    static SalesContractImpl dao;
    
    @BeforeAll
    static void setup() {
        dao = new SalesContractImpl("DB_URL", "DB_USR", "DB_PASS");
    }


    @Test
    void findAllSalesContracts() {
        List<SalesContract> salesContractList = dao.findAllSalesContracts();
        assertNotNull(salesContractList);
        assertFalse(salesContractList.isEmpty());
    }

    @Test
    void findSalesContractById() {
        SalesContract salesContract = dao.findSalesContractById(2);
        assertNotNull(salesContract);
        assertEquals(2, salesContract.getContractID());
    }

    @Test
    void addSalesContract() {
        Vehicle vehicle = new Vehicle(12321, 2022, "Toyota", "Camry", "Sedan", "Blue", 4500, 22000.00, true);
        SalesContract salesContract = new SalesContract(1, "2023-01-01", "John Doe", "john.doe@example.com", 1, vehicle, true);
        dao.addSalesContract(salesContract);

        SalesContract contractById = dao.findSalesContractById(1);
        assertNotNull(contractById);
        assertEquals("John Doe", contractById.getCustomerName());
    }


    @Test
    void deleteSalesContract() {
        dao.deleteSalesContract(1);
        SalesContract salesContract = dao.findSalesContractById(1);
        assertNull(salesContract);
    }
}