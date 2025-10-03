package db;

import java.sql.*;

public class loginDB {

    public static String writeLoginToDB(String username, String password) throws SQLException {
        Connection connection = DBManager.getConnection();

        String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
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
}
