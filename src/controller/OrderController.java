package controller;

import db.DbConnection;
import model.ItemDetails;
import model.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderController {
    public boolean placeOrder(Order order) {
        try {
            PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("INSERT INTO `Order` Value (?,?,?)");

            statement.setObject(1, order.getId());
            statement.setObject(2, order.getDate());
            statement.setObject(3, order.getCusId());

            return statement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    /*private boolean saveOrderDetails(String orderID,ArrayList<ItemDetails> items) throws SQLException, ClassNotFoundException {
        for (ItemDetails temp:items){
            DbConnection.getInstance().getConnection().prepareStatement("INSERT ");
        }
    }
}*/
}

