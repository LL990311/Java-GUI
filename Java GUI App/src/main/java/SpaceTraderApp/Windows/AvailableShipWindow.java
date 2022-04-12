package SpaceTraderApp.Windows;

import SpaceTraderApp.Data.Ships;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;

public class AvailableShipWindow {
    private final Stage stage;
    private final AnchorPane pane;
    private TextFlow textFlow;
    private ScrollBar scrollBar = new ScrollBar();

    private TextField inputSymbol;
    private Button requestBtn;
    private Text text;

    public AvailableShipWindow() throws IOException {
        stage = new Stage();
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 300, 400);
        stage.setScene(scene);
        stage.setTitle("Available Ship Info");
        stage.show();

        pane.getChildren().add(scrollBar);
        scrollBar.setLayoutX(scene.getWidth() - scrollBar.getWidth());
        scrollBar.setMin(0);
        scrollBar.setOrientation(Orientation.VERTICAL);
        scrollBar.setPrefHeight(400);
        scrollBar.setMax(10000);
        scrollBar.setUnitIncrement(100);
        scrollBar.setBlockIncrement(100);


        scrollBar.valueProperty().addListener((ov, old_val, new_val) -> {
            textFlow.setLayoutY(-new_val.doubleValue());
        });

        initInputFields();
        initButtons();

    }

    private void initInputFields() {
        text = new Text();
        text.setText("Please remember always login first!!!");
        text.setFill(Color.web("#c21010"));
        text.setLayoutX(25);
        text.setLayoutY(35);
        inputSymbol = new TextField();
        inputSymbol.setLayoutX(90);
        inputSymbol.setLayoutY(70);
        inputSymbol.setPrefWidth(120);
        inputSymbol.setPromptText("Type");
        pane.getChildren().addAll(inputSymbol,text);
    }

    private void initButtons() {
        requestBtn = new Button();
        requestBtn.setLayoutX(100);
        requestBtn.setLayoutY(130);
        requestBtn.setPrefWidth(100);
        requestBtn.setText("Confirm");
        requestBtn.requestFocus();
        pane.getChildren().add(requestBtn);
        requestBtn.setOnAction(event -> {
            try {
                initText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initText() throws IOException {
        try {
            String symbol = inputSymbol.getText();
            pane.getChildren().removeAll(inputSymbol,requestBtn,text);
            Ships ships = MainWindow.mode.getAvailableShips(MainWindow.token, symbol);
            textFlow = new TextFlow();
            for (int i = 0; i < ships.getShips().size(); i++) {
                Text shipText = new Text("Ships:\n"
                        +"\t\ttype: " + ships.getShips().get(i).getType() + "\n"
                        +"\t\tclass: " + ships.getShips().get(i).get_class() + "\n"
                        +"\t\tmaxCargo: " + ships.getShips().get(i).getMaxCargo() + "\n"
                        +"\t\tspeed: " + ships.getShips().get(i).getSpeed() + "\n"
                        +"\t\tManufacturer: " + ships.getShips().get(i).getManufacturer() + "\n"
                        +"\t\tplating: " + ships.getShips().get(i).getPlating() + "\n"
                        +"\t\tweapons: " + ships.getShips().get(i).getWeapons() + "\n");

                textFlow.getChildren().add(shipText);

                for (int j = 0; j < ships.getShips().get(i).getPurchaseLocations().size(); j++) {
                    Text purchaseLocations = new Text("\t\tpurchaseLocation:\n"
                            +"\t\t\t\tsystem: " + ships.getShips().get(i).getPurchaseLocations().get(j).getSystem() + "\n"
                            +"\t\t\t\tlocation: " + ships.getShips().get(i).getPurchaseLocations().get(j).getLocation() + "\n"
                            +"\t\t\t\tprice: " + ships.getShips().get(i).getPurchaseLocations().get(j).getPrice() + "\n");

                    textFlow.getChildren().add(purchaseLocations);
                }

            }

            this.pane.getChildren().add(textFlow);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Something went wrong XoX");
            alert.show();
            stage.close();
        }

    }
}
