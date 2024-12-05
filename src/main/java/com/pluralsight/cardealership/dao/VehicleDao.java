package com.pluralsight.cardealership.dao;

import com.pluralsight.cardealership.model.Vehicle;

import java.util.ArrayList;

public interface VehicleDao {
    ArrayList<Vehicle> findAllVehicles();
    ArrayList<Vehicle> findVehicleByMakeModel(String make, String model);
    ArrayList<Vehicle> findVehicleByPrice(double minPrice, double maxPrice);
    ArrayList<Vehicle> findVehicleByYear(int year);
    ArrayList<Vehicle> findVehicleByColor(String color);
    ArrayList<Vehicle> findVehicleByMileage(int mileage);
    ArrayList<Vehicle> findVehicleByType(String type);
}