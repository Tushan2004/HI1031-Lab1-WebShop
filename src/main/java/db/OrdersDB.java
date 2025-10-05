package db;

import bo.Order;
import bo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersDB {

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
                    System.out.println("Ingen användare hittades med angivna inloggningsuppgifter.");
                    return orders; // Returnera tom lista
                }
            }
            int counter = 0;

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