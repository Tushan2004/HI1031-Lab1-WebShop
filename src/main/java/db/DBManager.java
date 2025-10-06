package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages database connections for the webshop.
 * Loads the MySQL JDBC driver and provides a method to get a database connection.
 */
public class DBManager {

    static {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** JDBC URL for the webshop database */
    private static final String URL =
            "jdbc:mysql://localhost:3306/webshop?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    /** Database username */
    private static final String USER = "root";

    /** Database password */
    private static final String PASS = "";

    /**
     * Returns a new connection to the webshop database.
     *
     * @return a Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
