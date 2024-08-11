package carsharing.dao;

import carsharing.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {
    private static final CustomerDao INSTANCE = new CustomerDao();
    private final Connection connection = ConnectionManager.getConnection();

    private CustomerDao() {}

    public static CustomerDao get() {
        return INSTANCE;
    }

    public boolean add(String customerName) {
        String sql = "INSERT INTO customer(name) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, customerName);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.printf("Failed to add [%s] customer.%n", customerName);
            e.printStackTrace();
            return false;
        }
    }

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            String sql = "SELECT * FROM customer";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Customer customer = getCustomer(rs);
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println("Failed to get customers list.");
            e.printStackTrace();
        }
        return customers;
    }

    private Customer getCustomer(ResultSet rs) throws SQLException {
        return new Customer(rs.getInt("id"),
                rs.getString("name"),
                rs.getObject("rented_car_id", Integer.class));
    }
}
