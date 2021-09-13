package controller.Item;

import com.jfoenix.controls.JFXTextField;
import controller.ItemController;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import model.Item;

import java.sql.SQLException;

public class SaveItemController {
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQTYonHand;
    public TableView tblStock;

    public void saveItem(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Item item1 =new Item(
                txtItemCode.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtQTYonHand.getText())
        );



        if(saveItem(item1))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();


    }

    public void clearData(ActionEvent actionEvent) {
    }
    boolean saveItem(Item item) throws SQLException, ClassNotFoundException {
        return new ItemController().saveItem(item);

    }
}
