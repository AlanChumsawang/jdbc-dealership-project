package com.pluralsight.cardealership.view;

import com.pluralsight.cardealership.dao.VehicleDao;
import com.pluralsight.cardealership.dao.VehicleDaoImpl;
import com.pluralsight.cardealership.fileIO.ContractFileManager;
import com.pluralsight.cardealership.fileIO.DealershipFileManager;
import com.pluralsight.cardealership.model.Contract;
import com.pluralsight.cardealership.model.Dealership;
import com.pluralsight.cardealership.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Dealership dealership; // Declare the dealership variable
    DealershipFileManager fileManager;
    ContractFileManager contractFileManager;
    String inventoryFile = "src/main/resources/inventory.csv";
    private VehicleDao vehicleDao;
    // Constructor
//    private void init() {
//        fileManager = new DealershipFileManager();
//        dealership = fileManager.getDealership(inventoryFile);
//        contractFileManager = new ContractFileManager();
//        contractFileManager.loadContracts(dealership);
//
//    }

    public UserInterface() {

        vehicleDao = new VehicleDaoImpl("DB_URL", "DB_USR", "DB_PASS");
    }

    // Display the user interface
    public void display() {
        Scanner inputScanner = new Scanner(System.in); // Create a scanner for user input
        //init(); // Initialize the dealership
        boolean isDone = false;
        while (!isDone) {
            Menus.getMainMenu(); // Display the main menu
            String userInput = inputScanner.nextLine().toUpperCase(); // Wait for user input
            switch (userInput) {
                case "1":
                    displayVehicles(vehicleDao.findAllVehicles());
                    break;
                case "2":
                    // Search for a vehicle
                    searchMenu(inputScanner);
                    break;
                case "3":
                    isDone = true;
                    break;

                case "ADMIN":
                    adminMenu(inputScanner);
                    break;


                default:// Handle invalid input
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private void adminMenu(Scanner scanner){
        boolean isDone = false;
        while (!isDone) {
            Menus.getAdminMenu();
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "1":
                    // Add vehicle
                    addVehicleMenu(scanner);
                    break;
                case "2":
                    // Remove vehicle
                    removeVehicleMenu(scanner);
                    break;
                case "3":
                    // View all contracts
                    displayContracts(dealership.getContractsList());
                    break;
                case "4":
                    // View last 10 contracts
                    displayContracts(dealership.getLast10Contracts());
                    break;
                case "5":
                    // Return to main menu
                    isDone = true;
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        }
    }
    private void searchMenu(Scanner scanner){
            boolean isDone = false;
            while ( (!isDone)) {
                Menus.getSearchMenu();
                String userInput = scanner.nextLine();
                switch (userInput) {
                    case "1":
                        // Search by price
                        System.out.print("Enter minimum price: ");
                        double minPrice = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter maximum price: ");
                        double maxPrice = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline
                        displayVehicles(vehicleDao.findVehicleByPrice(minPrice, maxPrice));
                        break;
                    case "2":
                        // Search by make and model
                        System.out.print("Enter make: ");
                        String make = scanner.nextLine();
                        System.out.print("Enter model: ");
                        String model = scanner.nextLine();
                        displayVehicles(vehicleDao.findVehicleByMakeModel(make, model));
                        break;
                    case "3":
                        // Search by year
                        System.out.print("Enter year: ");
                        int year = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        displayVehicles(vehicleDao.findVehicleByYear(year));
                        break;
                    case "4":
                        // Search by color
                        System.out.print("Enter color: ");
                        String color = scanner.nextLine();
                        displayVehicles(vehicleDao.findVehicleByColor(color));
                        break;
                    case "5":
                        // Search by mileage
                        System.out.print("Enter maximum mileage: ");
                        int mileage = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        displayVehicles(vehicleDao.findVehicleByMileage(mileage));
                        break;
                    case "6":
                        // Search by type
                        System.out.print("Enter type: ");
                        String type = scanner.nextLine();
                        displayVehicles(vehicleDao.findVehicleByType(type));
                        break;
                    case "7":
                        // Return to main menu
                        isDone = true;
                        break;
                    default:
                        System.out.println("Invalid input. Please try again.");
                        searchMenu(scanner);

                }
            }
    }
    private void addVehicleMenu(Scanner inputScanner){
        System.out.print("Enter VIN: ");
        int vin = inputScanner.nextInt();
        inputScanner.nextLine(); // Consume newline
        System.out.print("Enter Year: ");
        int year = inputScanner.nextInt();
        inputScanner.nextLine();
        System.out.print("Enter Make: ");
        String make = inputScanner.nextLine();
        System.out.print("Enter Model: ");
        String model = inputScanner.nextLine();
        System.out.print("Enter Type: ");
        String type = inputScanner.nextLine();
        System.out.print("Enter Color: ");
        String color = inputScanner.nextLine();
        System.out.print("Enter Odometer: ");
        int odometer = inputScanner.nextInt();
        inputScanner.nextLine();
        System.out.print("Enter Price: ");
        double price = inputScanner.nextDouble();
        inputScanner.nextLine();
        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);
    }

    private void removeVehicleMenu(Scanner inputScanner){
        System.out.print("Enter VIN of the vehicle to remove: ");
        int vin = inputScanner.nextInt();
        inputScanner.nextLine(); // Consume newline
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                dealership.removeVehicle(vehicle);
                System.out.println("Vehicle removed.");
                fileManager.saveDealership(dealership);
                return;
            }
        }
    }

    private void displayVehicles(List<Vehicle> vehicleList){
        if (vehicleList.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        else {
            for (Vehicle vehicle : vehicleList) {
                System.out.print("\n" + vehicle.getYear() + " " + vehicle.getMake() + " " + vehicle.getModel() + " \n" + "    "
                        + "Type: " + vehicle.getVehicleType() + " | Mileage: " + vehicle.getOdometer() + " | Price: $" +
                        vehicle.getPrice() + " | Vin:" + vehicle.getVin() + " | Color: " + vehicle.getColor() + "\n");
            }
        }
//        System.out.println("\n Purchase a vehicle by entering the VIN or return to the main menu by entering 0: ");
//        int userInput = inputScanner.nextInt();
//        inputScanner.nextLine(); // Consume newline
//        if (userInput == 0) {
//            return;
//        }
//        else {
//            dealership.purchaseVehicle(userInput, inputScanner);
//            fileManager.saveDealership(dealership);
//        }
    }

    private void displayContracts(ArrayList<Contract> contractList){
        System.out.println();
        if (contractList.size() == 0) {
            System.out.println("No contracts found.");
            return;
        }
        else {
            for (Contract contract : contractList) {
                System.out.println(contract.formatContract(contract, contract.getVehicle()));
            }
        }
    }
}
