package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class databaseConnection {

    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost:3306/";
    private static final String database = "client_schedule";
    private static final String jdbURL = protocol + vendor + location + database + "?connectionTimeZone=SERVER";
    private static final String username = "sqlUser";
    private static final String password = "Passw0rd!";
    private static Connection connection;

    /**
     * Method to get current connection to the database.
     *
     * @return connection
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Open connection to the database.
     */
    public static void openConnection() {
        try {
            connection = DriverManager.getConnection(jdbURL, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close connection to the database.
     */
    public static void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
