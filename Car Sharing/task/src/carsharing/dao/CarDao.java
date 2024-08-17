package carsharing.dao;

import carsharing.entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDao {
    private static final CarDao INSTANCE = new CarDao();
    private final Connection connection = ConnectionManager.getConnection();

    private CarDao() {
    }

    public static CarDao get() {
        return INSTANCE;
    }

    public List<Car> getCarsForCompany(Integer companyId) {
        List<Car> cars = new ArrayList<>();
        String sql = """
            SELECT c.* 
            FROM car c 
            WHERE c.company_id = ?
            AND c.id NOT IN (SELECT rented_car_id 
                             FROM customer 
                             WHERE rented_car_id IS NOT NULL)""";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, companyId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cars.add(new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("company_id")));
            }
        } catch (SQLException e) {
            System.out.printf("Failed to get company [%n] cars list.%n", companyId);
            e.printStackTrace();
        }
        return cars;
    }

    public boolean add(Integer companyId, String carName) {
        String sql = "INSERT INTO car(name, company_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, carName);
            ps.setInt(2, companyId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.printf("Failed to add [%s] car for company [%d].%n", carName, companyId);
            e.printStackTrace();
            return false;
        }
    }

    public Car getByName(String carName) {
        String sql = "SELECT * FROM car WHERE name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, carName);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("company_id"));
            }
        } catch (SQLException e) {
            System.out.printf("Failed to find a car with name [%s].%n", carName);
            e.printStackTrace();
        }
        return null;
    }

    public Car getById(Integer carId) {
        String sql = "SELECT * FROM car WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, carId);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return new Car(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("company_id"));
            }
        } catch (SQLException e) {
            System.out.printf("Failed to find a car with ID [%s].%n", carId);
            e.printStackTrace();
        }
        return null;
    }
}
