package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String JDBC_DRIVER = "org.h2.Driver";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String databaseFileName = getArgsParam(args, "databaseFileName", "carsharing");
        String url = "jdbc:h2:./src/carsharing/db/%s".formatted(databaseFileName);

        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(url);
        connection.setAutoCommit(true);
        createTables(connection);
    }

    private static void createTables(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = """
              CREATE TABLE IF NOT EXISTS company (
                  `id` INTEGER,
                  `name` VARCHAR
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