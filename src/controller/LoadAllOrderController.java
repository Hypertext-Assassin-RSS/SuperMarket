package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.OrderDetails;
import view.tm.orderTm;

import java.sql.*;
import java.util.ArrayList;

public class LoadAllOrderController {

    public TableView <orderTm>tblOrder;
    public TableColumn colOrderId;
    public TableColumn colItemCode;
    public TableColumn colQTY;
    public TableColumn colDiscount;

    public void initialize() throws SQLException, ClassNotFoundException {

        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));


        loadAllItems();

    }



    private void loadAllItems() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Business",
                "root", "1234");
        Statement stm = con.createStatement();
        String query = "SELECT * FROM `Order Detail`";
        ResultSet rst = stm.executeQuery(query);
/*
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
*/
        ArrayList<OrderDetails> details = new ArrayList<>();
        while (rst.next()) {

            details.add(new OrderDetails(
                  rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4)
            ));
        }
        setOrders(details);
    }

    private void setOrders(ArrayList<OrderDetails> details) {

        ObservableList<orderTm> obList = FXCollections.observableArrayList();
        details.forEach(orderDetails ->{
            obList.add(
                    new orderTm(
                            orderDetails.getOrderId(),orderDetails.getItemNo(),orderDetails.getQTY(),orderDetails.getDiscount()
                    ));
        });
        tblOrder.setItems(obList);

    }
}
