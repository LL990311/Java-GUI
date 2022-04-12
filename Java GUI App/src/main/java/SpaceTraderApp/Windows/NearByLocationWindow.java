package SpaceTraderApp.Windows;

import SpaceTraderApp.Data.Locations;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.geometry.Orientation;

import java.io.IOException;

public class NearByLocationWindow {
    private final Stage stage;
    private final AnchorPane pane;
    private TextFlow textFlow;
    private ScrollBar scrollBar = new ScrollBar();
    private TextField symbol;
    private Button requestBtn;

    public NearByLocationWindow() throws IOException {
        stage = new Stage();
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 300, 400);
        stage.setScene(scene);
        stage.setTitle("Nearby Location Info");
        stage.show();

        initButtons();
        initInputFields();

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

    }

    private void initInputFields() {
        symbol = new TextField();
        symbol.setLayoutX(50);
        symbol.setLayoutY(60);
        symbol.setPrefWidth(200);
        symbol.setPromptText("Symbol");
        pane.getChildren().add(symbol);
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
            String symbol1 = symbol.getText();
            pane.getChildren().removeAll(symbol,requestBtn);
            Locations locations = MainWindow.mode.getNearByLocation(symbol1,MainWindow.token);
            textFlow = new TextFlow();

            if (locations.getLocationList().size() == 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid Input!");
                alert.show();
                stage.close();
            }else {
                for (int i = 0; i < locations.getLocationList().size(); i++) {
                    Text location = new Text("Location:\n"
                            +"\tallowsConstruction: " + locations.getLocationList().get(i).isAllowsConstruction() + "\n"
                            +"\tname: " + locations.getLocationList().get(i).getName() + "\n"
                            +"\tsymbol: " + locations.getLocationList().get(i).getSymbol() + "\n"
                            +"\ttype: " + locations.getLocationList().get(i).getType() + "\n"
                            +"\tx: " + locations.getLocationList().get(i).getX() + "\n"
                            +"\ty: " + locations.getLocationList().get(i).getY() + "\n");
                    textFlow.getChildren().add(location);
                }
                this.pane.getChildren().add(textFlow);
            }


        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Something went Wrong XoX");
            alert.show();
            stage.close();
        }

    }
}
