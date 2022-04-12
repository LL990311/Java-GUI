package SpaceTraderApp.Windows;

import SpaceTraderApp.Data.Ships;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class PurchaseShipWindow {
    private final Stage stage;
    private final AnchorPane pane;
    private TextField inputType;
    private TextField inputLocation;

    public PurchaseShipWindow() throws IOException, ParseException {
        stage = new Stage();
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 300,170);
        stage.setScene(scene);
        stage.setTitle("Request Loan");
        stage.show();

        initButtons();
        initInputFields();

    }

    private void initInputFields() {
        Text text = new Text();
        text.setText("Please remember always login first!!!");
        text.setFill(Color.web("#c21010"));
        text.setLayoutX(30);
        text.setLayoutY(15);
        pane.getChildren().add(text);

        inputType = new TextField();
        inputType.setLayoutX(50);
        inputType.setLayoutY(35);
        inputType.setPrefWidth(100);
        inputType.setPromptText("Type");
        pane.getChildren().add(inputType);

        inputLocation = new TextField();
        inputLocation.setLayoutX(50);
        inputLocation.setLayoutY(85);
        inputLocation.setPrefWidth(100);
        inputLocation.setPromptText("Location");
        pane.getChildren().add(inputLocation);
    }

    private void BuyAction() throws IOException {
        try {
            String location = inputLocation.getText();
            String type = inputType.getText();
            Ships ships = MainWindow.mode.buyShip(MainWindow.token,location,type);
            Alert alert;
            if (ships.getShip().getId() == null) {
                alert = new Alert(Alert.AlertType.WARNING, "Invalid input!");
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION, "request successfully!" +
                        "\nYour shipId is showing at command line.");
            }
            alert.show();
            stage.close();
        }catch (NullPointerException e) {
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
                BuyAction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
