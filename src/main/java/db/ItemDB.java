package db;

import bo.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

/**
 * Handles database operations related to items/products in the webshop.
 */
public class ItemDB {

    /**
     * Searches and returns a collection of items from the database.
     * Currently, the parameter {@code item_group} is not used in the query.
     *
     * @param item_group the group/category of items to search (currently ignored)
     * @return a Collection of Item objects retrieved from the database
     */
    public static Collection<Item> searchItems(String item_group) {
        Vector<Item> v = new Vector<>();
        try (Connection con = DBManager.getConnection();
             Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery("SELECT id, name, description, price FROM T_ITEM");

            while (rs.next()) {
                int i = rs.getInt("id");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                double price = rs.getDouble("price");
                v.addElement(new Item(i, name, desc, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return v;
    }
}
