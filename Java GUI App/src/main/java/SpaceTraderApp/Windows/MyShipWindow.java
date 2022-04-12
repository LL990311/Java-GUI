package SpaceTraderApp.Windows;

import SpaceTraderApp.Data.Ships;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

public class MyShipWindow {
    private final Stage stage;
    private final AnchorPane pane;
    private TextFlow textFlow;

    private final ScrollBar scrollBar = new ScrollBar();

    public MyShipWindow() throws IOException {
        stage = new Stage();
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 300, 400);
        stage.setScene(scene);
        stage.setTitle("My Ship Info");
        stage.show();
        initText();

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

    private void initText() throws IOException {
        try {
            Ships ships = MainWindow.mode.getMyShips(MainWindow.token);
            textFlow = new TextFlow();
            textFlow.setLineSpacing(1.5);
            for (int i = 0; i < ships.getShips().size(); i++) {
                Text shipText = new Text("Ships:\n"
//                        +"\t\tclass: " + ships.getShips().get(i).get_class() + "\n"
                        +"\t\tid: " + ships.getShips().get(i).getId() + "\n"
                        +"\t\tmaxCargo: " + ships.getShips().get(i).getMaxCargo() + "\n"
                        +"\t\tspeed: " + ships.getShips().get(i).getSpeed() + "\n"
                        +"\t\tManufacturer: " + ships.getShips().get(i).getManufacturer() + "\n"
                        +"\t\tplating: " + ships.getShips().get(i).getPlating() + "\n"
                        +"\t\tweapons: " + ships.getShips().get(i).getWeapons() + "\n"
                        +"\t\tspaceAvailable: " + ships.getShips().get(i).getSpaceAvailable() + "\n"
                        +"\t\tx: " + ships.getShips().get(i).getX() + "\n"
                        +"\t\ty: " + ships.getShips().get(i).getY() + "\n"
                );
                textFlow.getChildren().add(shipText);
                if (ships.getShips().get(i).getCargo().size() == 0) {
                    Text cargo = new Text("\t\tcargo: []\n");
                    textFlow.getChildren().add(cargo);
                }else {
                    for (int j = 0; j < ships.getShips().get(i).getCargo().size(); j++) {
                        Text cargo = new Text("\t\tcargo:\n"
                                +"\t\t\t\tgood: "+ships.getShips().get(i).getCargo().get(j).getGood()+ "\n"
                                +"\t\t\t\tquantity: "+ships.getShips().get(i).getCargo().get(j).getQuantity()+ "\n"
                                +"\t\t\t\ttotalVolume: "+ships.getShips().get(i).getCargo().get(j).getTotalVolume()+ "\n");
                        textFlow.getChildren().add(cargo);
                    }
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
