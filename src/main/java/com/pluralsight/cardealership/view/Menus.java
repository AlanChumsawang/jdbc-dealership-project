package com.pluralsight.cardealership.view;

public class Menus {

    private static String mainMenu = """
                                                           
                               psst... If you want to access the admin menu, type 'Admin'\s
                        \n
                        ==============================
                        Welcome to the Car Dealership
                        ==============================
                1. View all vehicles
                2. Search for a vehicle
                3. Exit
                Choose an option:\s""";

    private static String searchMenu = """
                \n
                1. Search by Price
                2. Search by Make and Model
                3. Search by Year
                4. Search by Color
                5. Search by Mileage
                6. Search by Type
                7. Return to Main Menu
                Chose an option: """;


    public  static String adminMenu = """
                \n
                1. Add Vehicle
                2. Remove Vehicle
                3. View All Contracts
                4. View Last 10 Contracts
                5. Return to Main Menu
                Choose an option: """;

    public static void getMainMenu() {
        System.out.print(mainMenu);
    }

    public static void getSearchMenu() {
        System.out.print(searchMenu);
    }

    public static void getAdminMenu() {
        System.out.print(adminMenu);
    }
}

