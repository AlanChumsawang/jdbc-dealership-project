package com.pluralsight.cardealership.test;

import com.pluralsight.cardealership.model.Dealership;
import com.pluralsight.cardealership.model.Vehicle;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.*;

public class DealershipFileManagerTest {
    private Dealership dealership;
    private Vehicle vehicle;

    @Test
    void getDealership() {

        // Read the dealership information from the CSV file
        // Try to read the file and create a dealership object
        try {
            FileReader fileReader = new FileReader("src/test/resources/test_inventory.csv");// Use a test file
            BufferedReader bufReader = new BufferedReader(fileReader);// Create a buffered reader
            String currentLine = bufReader.readLine();// Read the first line
            String[] dealershipInfo = currentLine.split("[|]");// Split the line at each | and place into dealership info
            String dealershipName = dealershipInfo[0];// Get the dealership name
            String dealershipAddress = dealershipInfo[1];// Get the dealership address
            String dealershipPhone = dealershipInfo[2];// Get the dealership phone
            dealership = new Dealership(dealershipName, dealershipPhone, dealershipAddress);// Create a dealership object
            while ((currentLine = bufReader.readLine()) != null) {// Read the rest of the lines
                String[] vehicleInfo = currentLine.split("[|]");// Split the line at each | and place into vehicle info
                // Create a vehicle object variables from the vehicle info
                int vin = Integer.parseInt(vehicleInfo[0]);
                int year = Integer.parseInt(vehicleInfo[1]);
                String make = vehicleInfo[2];
                String model = vehicleInfo[3];
                String type = vehicleInfo[4];
                String color = vehicleInfo[5];
                int odometer = Integer.parseInt(vehicleInfo[6]);
                double price = Double.parseDouble(vehicleInfo[7]);
                // Use the vehicle object variables to create a vehicle object
                vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                // Add the vehicle to the dealership
                dealership.addVehicle(vehicle);
            }
        } catch (Exception e) {
            System.out.println("File not found");
            throw new RuntimeException();
        }
        assertEquals(3, dealership.getAllVehicles().size());
    }

    @Test
    void saveDealership() {
        // Implement the test for saving the dealership to a file
    }
}