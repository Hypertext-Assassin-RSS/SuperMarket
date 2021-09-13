package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.LogInModel;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class LogInFormController {

    public JFXTextField txtUser;
    public JFXTextField txtPassword;
    public AnchorPane LoginFromContext;
    public Label txtWarning;


    public void logIn(ActionEvent actionEvent) throws ClassNotFoundException {
        String password = txtPassword.getText();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Business",
                    "root",
                    "1234");
            Statement stm = con.createStatement();
            String query = "SELECT * FROM Employ WHERE password='" + password + "'";

            ResultSet set = stm.executeQuery(query);

            if (set.next()){

                String tempUser = set.getString(1);
                String tempPassword = set.getString(2);

                txtUser.setText(tempUser);
                txtPassword.setText(tempPassword);

                URL resource = getClass().getResource("../view/optionWindow.fxml");
                Parent load = FXMLLoader.load(resource);
                Stage stage = new Stage();
                Scene scene = new Scene(load);
                stage.setScene(scene);
                stage.show();

            }else{
                txtWarning.setText("Try Again !");
                new Alert(Alert.AlertType.WARNING,"Wrong user Name Or Password").show();
            }


        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();

        }

    }
}
