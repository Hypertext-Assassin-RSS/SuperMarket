package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class manageStockController {
    public AnchorPane saveItemContext;
    public JFXTextField txtSupplierName;
    public JFXComboBox cmbSupplierID;
    public JFXComboBox cmbItemCode;
    public JFXTextField txtDescri;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQTYAvail;
    public JFXTextField txtNewQTY;
    public JFXButton btnCancel;
    public JFXButton btnAddToStock;
    public TableView tblStock;
    public JFXButton btnConfirm;

    public void clear(ActionEvent actionEvent) {
    }

    public void addToStock(ActionEvent actionEvent) {
    }

    public void confirm(ActionEvent actionEvent) {
    }
}
