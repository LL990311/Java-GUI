package SpaceTraderApp.Windows;

import SpaceTraderApp.Data.FlightPlans;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class CreateFlightWindow {
    private final Stage stage;
    private final AnchorPane pane;
    private TextField inputShipID;
    private TextField inputDestination;

    public CreateFlightWindow()
            throws IOException, ParseException {
        stage = new Stage();
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 300,170);
        stage.setScene(scene);
        stage.setTitle("Create Flight plan");
        stage.show();

        initButtons();
        initInputFields();

    }

    private void initInputFields() {
        inputShipID = new TextField();
        inputShipID.setLayoutX(50);
        inputShipID.setLayoutY(30);
        inputShipID.setPrefWidth(200);
        inputShipID.setPromptText("ShipID");
        pane.getChildren().add(inputShipID);

        inputDestination = new TextField();
        inputDestination.setLayoutX(50);
        inputDestination.setLayoutY(80);
        inputDestination.setPrefWidth(200);
        inputDestination.setPromptText("Destination");
        pane.getChildren().add(inputDestination);
    }

    private void CreateFlightAction() throws IOException {
        try {
            String shipID = inputShipID.getText();
            String destination = inputDestination.getText();
            FlightPlans flightPlans = MainWindow.mode.createFlightPlan(shipID,destination,MainWindow.token);
            String id = flightPlans.getFlightPlan().getId();
            Alert alert;
            if (flightPlans.getFlightPlan() == null) {
                alert = new Alert(Alert.AlertType.WARNING, "Invalid input!");
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION, "request successfully!");
            }
            alert.show();
            stage.close();
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
                CreateFlightAction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
