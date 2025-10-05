package db;

import java.sql.*;

public class userDB {

    public static String writeUserToDB(String username, String password) throws SQLException {
        Connection connection = DBManager.getConnection();

        String sql = "INSERT INTO login (username, password) VALUES (?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, username);   // första ? blir username
        ps.setString(2, password);   // andra ? blir password

        int rows = ps.executeUpdate();  // kör INSERT (returnerar antal rader som påverkades)

        if (rows > 0) {
            return "User inserted successfully!";
        } else {
            return "Insert failed!";
        }
    }

    public static boolean searchUserInDB(String username, String password) {
        String sql = "SELECT * FROM `login` WHERE username = ? AND password = ?";

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
