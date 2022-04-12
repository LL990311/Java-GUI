package SpaceTraderApp.Windows;

import SpaceTraderApp.Data.PurchaseInfo;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class PurchaseGoodWindow {
    private final Stage stage;
    private final AnchorPane pane;
    private TextField shipId;
    private TextField good;
    private TextField quantity;

    public PurchaseGoodWindow() throws IOException, ParseException {
        stage = new Stage();
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 300,200);
        stage.setScene(scene);
        stage.setTitle("Purchase Goods");
        stage.show();

        initButtons();
        initInputFields();

    }

    private void initInputFields() {
        shipId = new TextField();
        shipId.setLayoutX(50);
        shipId.setLayoutY(30);
        shipId.setPrefWidth(200);
        shipId.setPromptText("ShipID");
        pane.getChildren().add(shipId);

        good = new TextField();
        good.setLayoutX(50);
        good.setLayoutY(60);
        good.setPrefWidth(200);
        good.setPromptText("Good");
        pane.getChildren().add(good);

        quantity = new TextField();
        quantity.setLayoutX(50);
        quantity.setLayoutY(90);
        quantity.setPrefWidth(200);
        quantity.setPromptText("Quantity");
        pane.getChildren().add(quantity);
    }

    private void PurchaseFuelAction() throws IOException {
        try {
            String sid = shipId.getText();
            String goods = good.getText();
            String quan = quantity.getText();
            PurchaseInfo purchaseGood = MainWindow.mode.purchaseFuel(
                    sid,goods,Integer.parseInt(quan),MainWindow.token);
            if (purchaseGood.getCredits() == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid input");
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "request successfully");
                alert.show();
                stage.close();
            }

        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please type correctly!");
            alert.show();
            stage.close();
        }

    }

    private void initButtons() {
        Button requestBtn = new Button();
        requestBtn.setLayoutX(100);
        requestBtn.setLayoutY(130);
        requestBtn.setPrefWidth(100);
        requestBtn.setText("Confirm");
        requestBtn.requestFocus();
        pane.getChildren().add(requestBtn);
        requestBtn.setOnAction(event -> {
            try {
                PurchaseFuelAction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
