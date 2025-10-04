package db;

import bo.CartItem;
import bo.Item;
import bo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CheckoutDB {

    public static void addItemsToDB(List<CartItem> items, User user) throws SQLException {
        if (items == null || items.isEmpty()) {
            System.out.println("No items to save.");
            return;
        }

        // 1️⃣ Hämta user_id från Login-tabellen
        int userId = 0;
        String userSql = "SELECT id FROM Login WHERE username = ? AND password = ?";

        try (Connection con = DBManager.getConnection();
             PreparedStatement userPs = con.prepareStatement(userSql)) {

            userPs.setString(1, user.getUsername());
            userPs.setString(2, user.getPassword());

            try (ResultSet rs = userPs.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("id");
                } else {
                    System.out.println("No matching user found in Login table!");
                    return; // Avbryt om ingen användare hittas
                }
            }

            // 2️⃣ Spara varorna i checkout-tabellen
            String sql = "INSERT INTO checkout (user_id, product_id, name, descr, price, quantity, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {

                for (CartItem item : items) {
                    Item product = item.getProduct();

                    ps.setInt(1, userId);  // ✅ Här sätter vi user_id vi hittade
                    ps.setInt(2, product.getId());
                    ps.setString(3, product.getName());
                    ps.setString(4, product.getDescr());
                    ps.setDouble(5, product.getPrice());
                    ps.setInt(6, item.getQuantity());
                    ps.setDouble(7, product.getPrice() * item.getQuantity());

                    ps.addBatch();
                }

                ps.executeBatch();
                System.out.println("Alla varor har sparats i checkout-tabellen för användare med ID: " + userId);
            }
        }
    }
}