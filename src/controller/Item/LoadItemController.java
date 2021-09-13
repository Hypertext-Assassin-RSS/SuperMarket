package controller.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import view.tm.ItemTm;

import java.sql.*;
import java.util.ArrayList;

public class LoadItemController {
    public TableView <ItemTm>tblitems;



    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQTYonHand;

    public void initialize(){
        try {

            colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
            colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
            colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
            colQTYonHand.setCellValueFactory(new PropertyValueFactory<>("QTY"));


            loadAllItems();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    private void loadAllItems() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Business",
                "root", "1234");
        Statement stm = con.createStatement();
        String query = "SELECT * FROM Item";
        ResultSet rst = stm.executeQuery(query);
/*
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
*/
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()) {

            items.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)

            ));
        }
        setItemsToTable(items);
    }

    private void setItemsToTable(ArrayList<Item> items) {

        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        items.forEach(item ->{
            obList.add(
                    new ItemTm(item.getCode(),item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getQTY()));
        });
        tblitems.setItems(obList);
    }
}
