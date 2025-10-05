package db;

import java.sql.*;

/**
 * Handles database operations related to users.
 */
public class UserDB {

    /**
     * Inserts a new user into the database.
     *
     * @param username the username of the new user
     * @param password the password of the new user
     * @return a String message indicating success or failure
     * @throws SQLException if a database access error occurs
     */
    public static String writeUserToDB(String username, String password) throws SQLException {
        String sql = "INSERT INTO login (username, password) VALUES (?, ?)";

        try (Connection connection = DBManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            int rows = ps.executeUpdate();
            return rows > 0 ? "User inserted successfully!" : "Insert failed!";
        }
    }

    /**
     * Searches for a user in the database by username and password.
     *
     * @param username the username to search for
     * @param password the password to match
     * @return true if a matching user is found, false otherwise
     */
    public static boolean searchUserInDB(String username, String password) {
        String sql = "SELECT * FROM login WHERE username = ? AND password = ?";

        try (Connection connection = DBManager.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
