//jag ska hämta session attributen,
// du kollar vilken användare man är inloggad med,
// när man vet så använder du id o gör select i databasen o där ska man matcha alla användar id
// o plocka ut dem o på något sätt visa upp det på jsp

package db;

import bo.Order;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDB {

    public static List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM checkout WHERE user_id = ?";

        try (Connection con = DBManager.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order o = new Order();
                    o.setId(rs.getInt("id"));
                    o.setUserId(rs.getInt("user_id"));
                    o.setProductId(rs.getInt("product_id"));
                    o.setName(rs.getString("name"));
                    o.setDescr(rs.getString("descr"));
                    o.setPrice(rs.getDouble("price"));
                    o.setQuantity(rs.getInt("quantity"));
                    o.setTotal(rs.getDouble("total"));
                    orders.add(o);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}