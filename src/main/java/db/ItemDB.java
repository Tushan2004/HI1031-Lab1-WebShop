package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

public class ItemDB extends bo.Item{

    public static Collection searchItems(String item_group){
        Vector v = new Vector<>();
        try {
            Connection con = DBManager.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select id, name, description from T_ITEM");

            while (rs.next()){
                int i = rs.getInt("id");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                v.addElement(new ItemDB(i,name,desc));
            }
        } catch (SQLException e){e.printStackTrace();}

        return v;
    }
    private ItemDB(int id, String name, String desc){
        super(id, name,desc);
    }
}
