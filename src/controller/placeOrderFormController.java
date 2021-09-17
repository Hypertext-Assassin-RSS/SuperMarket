package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import model.ItemDetails;
import model.Order;
import view.tm.CartTm;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class placeOrderFormController {
    public JFXTextField txtItem;
    public JFXComboBox<String> cmbItemNo;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public Label txtTime;
    public Label txtDate;
    public JFXComboBox<String> cmbICusID;
    public TableView<CartTm> tblCart;
    public JFXTextField txtQTY;
    public Label lblTotal;
    public TableColumn colItemNo;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQTY;
    public TableColumn colTotal;

    int cartSelectedRowForRemove = -1;


    public void initialize() {

        colItemNo.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        loadDateAndTime();
        try {
            loadItemNo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            loadCusID();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbItemNo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tblCart.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });


    }

    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem(itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result");
        } else {
            txtDescription.setText(i1.getDescription());
            txtPackSize.setText(i1.getPackSize());
            txtUnitPrice.setText(String.valueOf(i1.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(i1.getQTY()));
        }
    }


    private void loadCusID() throws SQLException, ClassNotFoundException {
        List<String> cusID = new customerController().getCustomerID();
        cmbICusID.getItems().addAll(cusID);
    }

    private void loadItemNo() throws SQLException, ClassNotFoundException {
        List<String> itemNo = new ItemController().getItemNo();
        cmbItemNo.getItems().addAll(itemNo);
    }



    private void loadDateAndTime() {
        // load Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        txtDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            txtTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void ClearItem(ActionEvent actionEvent) {
    if (cartSelectedRowForRemove==-1){
        new Alert(Alert.AlertType.WARNING,"Please Select A Row");
    }else{
        observableList.remove(cartSelectedRowForRemove);
        calculate();
        tblCart.refresh();
    }

}

    public void ConfirmOrder(ActionEvent actionEvent) {
        ArrayList<ItemDetails> items = new ArrayList<>();
        for(CartTm tempTm:observableList){
            items.add(
                    new ItemDetails(
                       tempTm.getCode(),tempTm.getUnitPrice(),tempTm.getQTY()
                    )
            );
        }
        Order order = new Order(
          "O-002",txtDate.getText(),cmbICusID.getValue(),items
        );
        if (new OrderController().placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION).show();
        }else {
            new Alert(Alert.AlertType.WARNING).show();
        }
    }

    ObservableList<CartTm> observableList = FXCollections.observableArrayList();


    public void AddToCart(ActionEvent actionEvent) {
       String description = txtDescription.getText();
       String packSize = txtPackSize.getText();
       double unitPrice = Double.parseDouble(txtUnitPrice.getText());
       int QTY = Integer.parseInt(txtQTY.getText());
       double total = QTY * unitPrice;
       int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());

       if (qtyOnHand<QTY){
           new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();

           return;
       }

       CartTm tm = new CartTm(
               cmbItemNo.getValue(),description,packSize,unitPrice,QTY,total
       );

       int rowNumber = isExists(tm);

       if (rowNumber==-1){
           observableList.add(tm);
       }else{
           CartTm temp = observableList.get(rowNumber);
           CartTm newTm = new CartTm(
             temp.getCode(),
             temp.getDescription(),
             temp.getPackSize(),
             unitPrice,
                   temp.getQTY()+QTY,
             total+temp.getTotal()
           );

           observableList.remove(rowNumber);
           observableList.add(newTm);

       }
       tblCart.setItems(observableList);

       calculate();


    }
    private int isExists(CartTm tm) {
        for (int i = 0;i < observableList.size();i++){
           if (tm.getCode().equals(observableList.get(i).getCode())){
               return i;
           }
        }

        return -1;
    }

    void calculate() {
        double total =0;

        for(CartTm tm:observableList){
            total+=tm.getTotal();
        }
        lblTotal.setText(total+"/=");
    }


}




    //============================================================
 /*   private Customer loadCustomerIds() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer WHERE custID=?");
        stm.setObject(1,id
                );

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );

        } else {
            return null;
        }
    }



    public List<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item").executeQuery();
        List<String> ids= new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }

}
*/