package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    // Singleton-instans
    private static DBManager instance = null;
    private Connection con = null;

    // Privata getInstance-metoden
    private static DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    // Privat konstruktor
    private DBManager() {
        try {
            // Ladda MySQL JDBC-driver
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            // Skapa anslutning
            String url = "jdbc:mysql://localhost:3306/webshop?serverTimezone=UTC&useSSL=false";
            String user = "root";
            String password = "";
            con = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Publik metod för att hämta connection
    public static Connection getConnection() {
        return getInstance().con;
    }
}
