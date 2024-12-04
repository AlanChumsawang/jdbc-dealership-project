package com.pluralsight.cardealership.fileIO;

import com.pluralsight.cardealership.model.Dealership;
import com.pluralsight.cardealership.model.Vehicle;

import java.io.*;

public class DealershipFileManager {


    public Dealership getDealership(String file){
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String currentLine = bufReader.readLine();// Read the first line
            String[] dealershipInfo = currentLine.split("[|]");// Split the line at each | and place into dealership info
            String dealershipName = dealershipInfo[0];
            String dealershipAddress = dealershipInfo[1];
            String dealershipPhone = dealershipInfo[2];
            Dealership dealership = new Dealership(dealershipName, dealershipPhone, dealershipAddress);
            while ( (currentLine = bufReader.readLine()) != null){
                String[] vehicleInfo = currentLine.split("[|]");
                int vin = Integer.parseInt(vehicleInfo[0]);
                int year = Integer.parseInt(vehicleInfo[1]);
                String make = vehicleInfo[2];
                String model = vehicleInfo[3];
                String type =  vehicleInfo[4];
                String color = vehicleInfo[5];
                int odometer = Integer.parseInt(vehicleInfo[6]);
                double price = Double.parseDouble(vehicleInfo[7]);
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                dealership.addVehicle(vehicle);
            }
            return dealership;
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + file, e);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error parsing number in file: " + file, e);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error processing file: " + file, e);
        }
    }
    public void saveDealership(Dealership dealership){
        try{
            FileWriter fileWriter = new FileWriter("src/main/resources/inventory.csv");
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            bufWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n");
            for (Vehicle vehicle : dealership.getAllVehicles()){
                bufWriter.write(vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" + vehicle.getPrice() + "\n");
            }
            bufWriter.close();
        }
        catch (Exception e){
            System.out.println("File not found");
        }
    }
}
