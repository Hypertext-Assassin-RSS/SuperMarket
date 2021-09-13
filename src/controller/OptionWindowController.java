package controller;


import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;

public class OptionWindowController {
    public AnchorPane optionWindowContext;
    public AnchorPane ApaneOne;
    public JFXButton btnPlaceOrder;
    public JFXButton btnSaveItem;
    public JFXButton btnSaveSupplier;
    public JFXButton btnManageStock;
    public JFXButton btnLoadItems;
    public JFXButton btnLoadOrder;

    private static void Initialize(Parent load, JFXButton btnManageOrder) {
        Scene scene = btnManageOrder.getScene();
        load.translateXProperty().set(scene.getWidth());

        AnchorPane optionWindowContext = (AnchorPane) btnManageOrder.getScene().getRoot();
        optionWindowContext.getChildren().clear();
        optionWindowContext.getChildren().add(load);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(load.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            optionWindowContext.getChildren().remove(optionWindowContext);
        });
        timeline.play();
    }

    public void openPlaceOrderForm(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/placeOrderForm.fxml"));
        Initialize(load, btnPlaceOrder);
    }

    public void openSaveItem(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/Item/saveItem.fxml"));
        Initialize(load, btnSaveItem);
        /*URL resource = getClass().getResource("../view/manageOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) optionWindowContext.getScene().getWindow();
        window.setScene(new Scene(load));*/
    }


    public void openSaveSuplier(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/Suplier/saveSupplier.fxml"));
        Initialize(load, btnSaveSupplier);
    }

    public void openmanageStock(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/manageStock.fxml"));
        Initialize(load, btnManageStock);

    }

    public void loadItems(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/loadItem.fxml"));
        Initialize(load, btnLoadItems);
    }

    public void loadAllOrders(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/LoadAllOrder.fxml"));
        Initialize(load, btnLoadOrder);
    }
}

    //=============================================================================


   /* private void loadSplashScreen(){
        try {
            AppInitializer.isSplashLoaded = true;

            StackPane pane = FXMLLoader.load(getClass().getResource(("/genuineCoder/splash/splash.fxml")));
            ApaneOne.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("/genuineCoder/main/main.fxml")));
                    ApaneOne.getChildren().setAll(parentContent);
                } catch (IOException ex) {
                    Logger.getLogger(OptionWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(OptionWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}*/
