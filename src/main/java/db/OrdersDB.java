package db;

import bo.Order;
import bo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database operations related to orders.
 */
public class OrdersDB {

    /**
     * Retrieves a list of orders for a specific user.
     * Looks up the user's ID in the login table using username and password,
     * then selects all orders from the checkout table associated with that user ID.
     *
     * @param user the User object representing the logged-in user
     * @return a List of Order objects for the specified user; empty if no orders found
     */
    public static List<Order> getOrdersByUser(User user) {
        List<Order> orders = new ArrayList<>();

        int userId = 0;
        String userSql = "SELECT id FROM Login WHERE username = ? AND password = ?";
        String orderSql = "SELECT * FROM checkout WHERE user_id = ?";

        try (Connection con = DBManager.getConnection();
             PreparedStatement userPs = con.prepareStatement(userSql)) {

            userPs.setString(1, user.getUsername());
            userPs.setString(2, user.getPassword());

            try (ResultSet rs = userPs.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("id");
                } else {
                    return orders; // Return empty list if user not found
                }
            }

            try (PreparedStatement orderPs = con.prepareStatement(orderSql)) {
                orderPs.setInt(1, userId);

                try (ResultSet rs = orderPs.executeQuery()) {
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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
