package db;

import bo.CartItem;
import bo.Item;
import bo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Handles database operations related to checkout and orders.
 */
public class CheckoutDB {

    /**
     * Saves a list of items in the checkout table for a specific user.
     * Looks up the user ID in the login table using username and password,
     * then inserts each cart item into the checkout table.
     *
     * @param items the list of CartItem objects to save
     * @param user the User object representing the logged-in user
     * @throws SQLException if a database access error occurs
     */
    public static void addItemsToDB(List<CartItem> items, User user) throws SQLException {
        if (items == null || items.isEmpty()) {
            System.out.println("No items to save.");
            return;
        }

        int userId;
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
                    return;
                }
            }

            String sql = "INSERT INTO checkout (user_id, product_id, name, descr, price, quantity, total) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {

                for (CartItem item : items) {
                    Item product = item.getProduct();

                    ps.setInt(1, userId);
                    ps.setInt(2, product.getId());
                    ps.setString(3, product.getName());
                    ps.setString(4, product.getDescr());
                    ps.setDouble(5, product.getPrice());
                    ps.setInt(6, item.getQuantity());
                    ps.setDouble(7, product.getPrice() * item.getQuantity());

                    ps.addBatch();
                }

                ps.executeBatch();
            }
        }
    }
}
