package carsharing;

import carsharing.command.Command;
import carsharing.command.CommandPrompt;
import carsharing.dao.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String databaseFileName = getArgsParam(args, "databaseFileName", "carsharing");
        createTables(ConnectionManager.initConnection("jdbc:h2:./src/carsharing/db/%s".formatted(databaseFileName)));

        CommandPrompt prompt = new CommandPrompt();
        while (true) {
            Command command = prompt.command();
            if (!command.execute()) break;
            prompt.updateContext(command);
        }
    }

    private static void createTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createCompanySql = """
               CREATE TABLE IF NOT EXISTS company (
                   id INTEGER PRIMARY KEY AUTO_INCREMENT,
                   name VARCHAR UNIQUE NOT NULL
               )""";
            statement.executeUpdate(createCompanySql);

            String createCarSql = """
               CREATE TABLE IF NOT EXISTS car (
                   id INTEGER PRIMARY KEY AUTO_INCREMENT,
                   name VARCHAR UNIQUE NOT NULL,
                   company_id INTEGER NOT NULL,
                   FOREIGN KEY (company_id) REFERENCES company(id)
               )""";
            statement.executeUpdate(createCarSql);

            String createCustomerSql = """
               CREATE TABLE IF NOT EXISTS customer (
                   id INTEGER PRIMARY KEY AUTO_INCREMENT,
                   name VARCHAR UNIQUE NOT NULL,
                   rented_car_id INTEGER,
                   FOREIGN KEY (rented_car_id) REFERENCES car(id)
               )""";
            statement.executeUpdate(createCustomerSql);
        }
    }

    private static String getArgsParam(String[] args, String param, String defaultValue) {
        for (int i = 0; i < args.length; i++) {
            if (param.equals(args[i]) && i + 1 < args.length) {
                return args[i + 1];
            }
        }
        return defaultValue;
    }
}