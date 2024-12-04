package com.pluralsight.cardealership.test;

import com.pluralsight.cardealership.model.Dealership;
import com.pluralsight.cardealership.model.Vehicle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DealershipTest {
    private Dealership dealership;
    private Vehicle vehicle;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        // This method will be called before each test
        // create a dealership
        String dealershipName = "Test Dealership";
        String dealershipAddress = "1234 Dealership Rd";
        String dealershipPhone = "803-370-9163";
        dealership = new Dealership(dealershipName, dealershipAddress, dealershipPhone);// create a dealership

        // create vehicle variables
        int vin = 123456;
        int year = 2020;
        String make = "Toyota";
        String model = "Camry";
        String vehicleType = "Sedan";
        String color = "Blue";
        int odometer = 10000;
        double price = 20000.00;
        vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);// create a vehicle

        dealership.addVehicle(vehicle); // add vehicle to dealership
    }


    @org.junit.jupiter.api.Test
    void addVehicle() {
        // assert that the vehicle was added by checking the size of the inventory
        assertEquals(1, dealership.getAllVehicles().size());
    }

    @org.junit.jupiter.api.Test
    void removeVehicle() {
        // remove vehicle from dealership
        dealership.removeVehicle(vehicle);

        // assert that the vehicle was removed by checking the size of the inventory
        assertEquals(0, dealership.getAllVehicles().size());
    }

    @org.junit.jupiter.api.Test
    void getAllVehicles() {
        // assert that the vehicle list return the correct size
        assertEquals(1, dealership.getAllVehicles().size());
    }
}
