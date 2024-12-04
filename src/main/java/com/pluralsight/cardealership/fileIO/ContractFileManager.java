package com.pluralsight.cardealership.fileIO;

import com.pluralsight.cardealership.model.*;

import java.io.*;

public class ContractFileManager {
    public void saveContract(Contract contract) {
        try {
            FileWriter writer = new FileWriter("src/main/resources/contracts.csv", true);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("\n" + contract.formatContract(contract, contract.getVehicle()));
            buffer.close();

        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void loadContracts(Dealership dealership) {
        try {
            FileReader fileReader = new FileReader("src/main/resources/contracts.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            String currentLine;
            while ( (currentLine = bufReader.readLine()) != null) {
                String[] contractInfo = currentLine.split("[|]");
                String startDate = contractInfo[1];
                String customerName = contractInfo[2];
                String customerEmail = contractInfo[3];
                int vehicleVin = Integer.parseInt(contractInfo[4]);
                int vehicleYear = Integer.parseInt(contractInfo[5]);
                String vehicleMake = contractInfo[6];
                String vehicleModel = contractInfo[7];
                String vehicleType = contractInfo[8];
                String vehicleColor = contractInfo[9];
                int contractId = Integer.parseInt(contractInfo[10]);
                double vehiclePrice = Double.parseDouble(contractInfo[11]);

                Vehicle vehicle = new Vehicle(vehicleVin,vehicleYear,vehicleMake,vehicleModel,vehicleType,vehicleColor,0, vehiclePrice);
                if (contractInfo[0].equalsIgnoreCase("Lease")) {
                    LeaseContract contract = new LeaseContract(contractInfo[1], contractInfo[2], contractInfo[3],
                            Integer.parseInt(contractInfo[10]), vehicle, Double.parseDouble(contractInfo[15]));

                    dealership.addContract(contract);
                } else {
                    double totalCost = Double.parseDouble(contractInfo[15]);
                    boolean financed = Boolean.parseBoolean(contractInfo[16]);
                    SalesContract contract = new SalesContract(startDate, customerName, customerEmail,
                            contractId, vehicle, totalCost,
                            financed);

                    dealership.addContract(contract);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    }
