package com.pluralsight.cardealership.model;

public class SalesContract extends Contract {
    private double salesTax = 0.05 * getVehicle().getPrice();
    private double recordingFee = 100.00;
    private double processingFee;
    private boolean isFinanced;
    private double annualInterestRate;
    private int loanTerm;

    public SalesContract(String startDate, String customerName, String customerEmail, int customerId, Vehicle vehicle,
                         double totalPrice, boolean isFinanced) {
        super(startDate, customerName, customerEmail, customerId, vehicle);
        if (vehicle.getPrice() < 10000) {
            this.processingFee = 295.00;
        } else {
            this.processingFee = 495.00;
        }
        this.isFinanced = isFinanced;
        if (isFinanced && vehicle.getPrice() >= 10000) {
            this.annualInterestRate = 0.0425;
            this.loanTerm = 24;
        } else if (isFinanced && vehicle.getPrice() < 10000) {
            this.annualInterestRate = 0.0525;
            this.loanTerm = 48;
        } else {
            this.annualInterestRate = 0.0;
        }
        monthlyPayment = getMonthlyPayment();
    }

    @Override
    public String toString() {
        System.out.println("Sales Contract");
        System.out.println("Date: " + getStartDate());
        System.out.println("Customer: " + getCustomerName() + " (" + getCustomerEmail() + ")");
        System.out.println("Customer ID: " + getCustomerId());
        System.out.println("Vehicle: " + getVehicle().getYear() + " " + getVehicle().getMake() + " " + getVehicle().getModel());
        System.out.println("Price: " + getVehicle().getPrice());
        System.out.println("Sales Tax: " + salesTax);
        System.out.println("Recording Fee: " + recordingFee);
        System.out.println("Processing Fee: " + processingFee);
        System.out.println("Total Price: " + getTotalPrice());
        if (isFinanced) {
            System.out.println("Financed: Yes");
            System.out.println("Annual Interest Rate: " + annualInterestRate);
            System.out.println("Loan Term: " + loanTerm + " months");
            System.out.println("Monthly Payment: " + monthlyPayment);
        } else {
            System.out.println("Financed: No");
        }
        return null;
    }

    @Override
    public double getTotalPrice() {
        return Math.round(getVehicle().getPrice() + salesTax + recordingFee + processingFee);
    }

    @Override
    public double getMonthlyPayment() {
        return getTotalPrice() / loanTerm;
    }


    @Override
    public String formatContract(Contract contract, Vehicle vehicle) {
        return String.format("SALE |%-10s|%-15s|%-30s|%-7d|%-5d|%-10s|%-10s|%-10s|%-10s|%-7d|%-10.2f|%-10.2f|%-10.2f|%-10.2f|%-9.2f|%-7b|%-7.3f",
                contract.getStartDate(), contract.getCustomerName(), contract.getCustomerEmail(), vehicle.getVin(), vehicle.getYear(), vehicle.getMake(),
                vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), contract.getCustomerId(), vehicle.getPrice(), salesTax, recordingFee,
                processingFee, getTotalPrice(), isFinanced, annualInterestRate);
    }
}