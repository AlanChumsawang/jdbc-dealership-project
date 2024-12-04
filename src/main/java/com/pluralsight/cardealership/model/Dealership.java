package com.pluralsight.cardealership.model;

import com.pluralsight.cardealership.fileIO.ContractFileManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;
    private ArrayList<Contract> contractsList;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<Vehicle>();
        this.contractsList = new ArrayList<Contract>();
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return inventory;
    }

    public Vehicle getVehicleByVin(int vin) {
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVin() == vin) {
                return vehicle;
            }
        }
        return null;
    }

    public ArrayList<Vehicle> getVehicleByPrice(double min, double max) {
        ArrayList<Vehicle> vehiclesWithinPriceRange = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                vehiclesWithinPriceRange.add(vehicle);
            }
        }
            return vehiclesWithinPriceRange; // Return the list of vehicles within the price range
        }

    public ArrayList<Vehicle> getVehicleByMakeModel(String make, String model){
        ArrayList<Vehicle> vehicleOfMakeModel = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                vehicleOfMakeModel.add(vehicle);
            }
        }
        return vehicleOfMakeModel;
    }

    public ArrayList<Vehicle> getVehicleByYear(int year){
        ArrayList<Vehicle> vehicleOfYear = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() == year) {
                vehicleOfYear.add(vehicle);
            }
        }
        return vehicleOfYear;
    }

    public ArrayList<Vehicle> getVehicleByColor(String color){
        ArrayList<Vehicle> vehicleOfColor = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                vehicleOfColor.add(vehicle);
            }
        }
        return vehicleOfColor;
    }

    public ArrayList<Vehicle> getVehicleByMileage(int odometer){
        ArrayList<Vehicle> vehiclesWithinMileage = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() <= odometer) {
                vehiclesWithinMileage.add(vehicle);
            }
        }
        return vehiclesWithinMileage;
    }

    public ArrayList<Vehicle> getVehicleByType(String type){
        ArrayList<Vehicle> vehicleOfType = new ArrayList<Vehicle>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(type)) {
                vehicleOfType.add(vehicle);
            }
        }
        return vehicleOfType;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
    public void purchaseVehicle(int vin, Scanner inputScanner) {
        LocalDate currentDate = LocalDate.now();
        String startDate = DateTimeFormatter.ofPattern("yyyyMMdd").format(currentDate);
        int customerId = (int) (Math.random() * 900000) + 100000;
        System.out.print("Enter your name: ");
        String customerName = inputScanner.nextLine();
        System.out.print("Enter your email: ");
        String customerEmail = inputScanner.nextLine();
        ContractFileManager contractFileManager = new ContractFileManager();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getVin() == vin) {
                System.out.println("\n" + vehicle.getYear() + " " + vehicle.getMake() + " " + vehicle.getModel() + "\n1:Purchase\n2:Lease\n3:Return to main menu");
                int purchaseOption = inputScanner.nextInt();
                inputScanner.nextLine();
                switch (purchaseOption) {
                    case 1:
                        System.out.print("Would you like to finance the vehicle? (Y/N): ");
                        String financeOption = inputScanner.nextLine();
                        if (financeOption.equalsIgnoreCase("Y")) {
                            SalesContract salesContract = new SalesContract(startDate, customerName, customerEmail,
                                    customerId, vehicle, vehicle.getPrice(), true);
                            contractFileManager.saveContract(salesContract);
                            inventory.remove(vehicle);
                        } else {
                            SalesContract salesContract = new SalesContract(startDate, customerName, customerEmail,
                                    customerId, vehicle, vehicle.getPrice(), false);
                            contractFileManager.saveContract(salesContract);
                        }

                        inventory.remove(vehicle);

                        return;
                    case 2:
                        LeaseContract leaseContract = new LeaseContract(startDate, customerName, customerEmail, customerId,
                                vehicle, vehicle.getPrice());
                        inventory.remove(vehicle);
                        contractFileManager.saveContract(leaseContract);
                        return;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid option");
                        return;
                }
                //inventory.remove(vehicle);
            }
        }
    }

    public ArrayList<Contract> getContractsList() {
        return contractsList;
    }

    public void addContract(Contract contract) {
        contractsList.add(contract);
    }

    public ArrayList<Contract> getLast10Contracts() {
        ArrayList<Contract> last10Contracts = new ArrayList<Contract>();
        int size = contractsList.size();
        if (size <= 10) {
            return contractsList;
        } else {
            for (int i = size - 10; i < size; i++) {
                last10Contracts.add(contractsList.get(i));
            }
            return last10Contracts;
        }
    }

    @Override
    public String toString() {
        return "Dealership{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", inventory=" + inventory +
                '}';
    }
}
