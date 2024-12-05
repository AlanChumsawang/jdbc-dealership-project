package com.pluralsight.cardealership.dao;

import com.pluralsight.cardealership.config.DatabaseConfig;
import com.pluralsight.cardealership.model.Vehicle;

import java.sql.*;
import java.util.ArrayList;

public class VehicleDaoImpl implements VehicleDao {

    @Override
    public ArrayList<Vehicle> findAllVehicles() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicles";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                vehicles.add(createVehicleFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public ArrayList<Vehicle> findVehicleByMakeModel(String make, String model) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicles WHERE Make = ? AND Model = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                vehicles.add(createVehicleFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

    @Override
    public ArrayList<Vehicle> findVehicleByPrice(double minPrice, double maxPrice) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicles WHERE Price >= ? and Price <= ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, String.valueOf(minPrice));
            preparedStatement.setString(2, String.valueOf(maxPrice));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                vehicles.add(createVehicleFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }

        @Override
        public ArrayList<Vehicle> findVehicleByYear(int year) {
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            String query = "SELECT * FROM Vehicles WHERE Year = ?";

            try (Connection connection = DatabaseConfig.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, String.valueOf(year));
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    vehicles.add(createVehicleFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return vehicles;
        }

        @Override
        public ArrayList<Vehicle> findVehicleByColor(String color) {
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            String query = "SELECT * FROM Vehicles WHERE Color = ?";

            try (Connection connection = DatabaseConfig.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, String.valueOf(color));
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    vehicles.add(createVehicleFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return vehicles;
        }

        @Override
        public ArrayList<Vehicle> findVehicleByMileage(int mileage) {
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            String query = "SELECT * FROM Vehicles WHERE Odometer <= ?";

            try (Connection connection = DatabaseConfig.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, String.valueOf(mileage));
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    vehicles.add(createVehicleFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return vehicles;
        }

        @Override
        public ArrayList<Vehicle> findVehicleByType(String type) {
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            String query = "SELECT * FROM Vehicles WHERE Type = ?";

            try (Connection connection = DatabaseConfig.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, String.valueOf(type));
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    vehicles.add(createVehicleFromResultSet(resultSet));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return vehicles;
        }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        return new Vehicle(
                resultSet.getInt("VIN"),
                resultSet.getInt("Year"),
                resultSet.getString("Make"),
                resultSet.getString("Model"),
                resultSet.getString("Type"),
                resultSet.getString("Color"),
                resultSet.getInt("Odometer"),
                resultSet.getDouble("Price")
        );
    }
}