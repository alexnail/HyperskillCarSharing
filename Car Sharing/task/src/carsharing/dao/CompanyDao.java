package carsharing.dao;

import carsharing.entity.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao {
    private static final CompanyDao INSTANCE = new CompanyDao();
    private final Connection connection = ConnectionManager.getConnection();

    private CompanyDao() {

    }

    public static CompanyDao get() {
        return INSTANCE;
    }

    public List<Company> getAll() {
        List<Company> companies = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = """
               SELECT * 
               FROM company;""";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                companies.add(new Company(id, name));
            }
            return companies;
        } catch (SQLException e) {
            System.out.println("Failed to get company list.");
            e.printStackTrace();
        }
        return List.of();
    }

    public boolean add(String companyName) {
        String sql = "INSERT INTO company(name) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, companyName);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.printf("Failed add [%s] company.%n", companyName);
            e.printStackTrace();
            return false;
        }
    }
}
