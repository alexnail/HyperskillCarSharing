package carsharing;

import carsharing.command.CommandPrompt;
import carsharing.dao.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String databaseFileName = getArgsParam(args, "databaseFileName", "carsharing");
        createTables(ConnectionManager.initConnection("jdbc:h2:./src/carsharing/db/%s".formatted(databaseFileName)));

        Scanner scanner = new Scanner(System.in);
        CommandPrompt prompt = new CommandPrompt(scanner);
        while (prompt.command().execute()) {
        }
    }

    private static void createTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = """
               CREATE TABLE IF NOT EXISTS company (
                   id INTEGER PRIMARY KEY AUTO_INCREMENT,
                   name VARCHAR UNIQUE NOT NULL
               )""";
            statement.executeUpdate(createTableQuery);
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