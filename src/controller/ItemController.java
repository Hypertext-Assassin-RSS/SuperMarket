package controller;

import db.DbConnection;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemService {
    public List<String> getItemNo() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Item").executeQuery();
        List<String> itemNo = new ArrayList<>();
        while (rst.next()) {
            itemNo.add(
                    rst.getString(1)
            );
        }
        return itemNo;
    }

    @Override
    public boolean saveItem(Item item) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();

        String query = "INSERT INTO Item VALUES(?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, item.getCode());
        stm.setObject(2, item.getDescription());
        stm.setObject(3, item.getPackSize());
        stm.setObject(4, item.getUnitPrice());
        stm.setObject(5, item.getQTY());

        return stm.executeUpdate() > 0;
    }

    @Override
    public ArrayList<Item> loadItem() {
        return null;
    }

    @Override
    public boolean deleteItem(String itemCode) {
        return false;
    }

    public Item getItem(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item WHERE itemCode='" + itemCode + "'").
                executeQuery();
        if (rst.next()) {
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)

            );
        } else {
            return null;
        }
    }
}
