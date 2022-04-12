package SpaceTraderApp.Windows;

import SpaceTraderApp.Data.MarketPlaces;
import SpaceTraderApp.Data.Marketplace;
import SpaceTraderApp.Data.PurchaseInfo;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;

public class MarketInfoWindow {
    private final Stage stage;
    private final AnchorPane pane;
    private ScrollBar scrollBar = new ScrollBar();
    private TextFlow textFlow;

    private TextField symbol;
    private Button requestBtn;

    public MarketInfoWindow() throws IOException {
        stage = new Stage();
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 300, 400);
        stage.setScene(scene);
        stage.setTitle("MarketInfo");
        stage.show();
        initInputFields();
        initButtons();

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
                intiText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void intiText() throws IOException{
        try {
            String symbol1 = symbol.getText();
            pane.getChildren().removeAll(symbol,requestBtn);
            MarketPlaces locations = MainWindow.mode.getMarketInfo(symbol.getText(),MainWindow.token);
            if (locations == null) {
                System.out.println("______________________-");
            }
            textFlow = new TextFlow();
            textFlow.setLineSpacing(1.5);
            if (locations.getMarketplaces() == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "No market in given location");
                alert.show();
                stage.close();
            }else {
                Text location = new Text("MarketLocation:\n");
                textFlow.getChildren().add(location);
                for (int i = 0; i < locations.getMarketplaces().size(); i++) {
                    Text marketInfo = new Text("\tMarketplace:\n"
                            +"\t\tsymbol: " + locations.getMarketplaces().get(i).getSymbol() + "\n"
                            +"\t\tvolumePerUnit: " + locations.getMarketplaces().get(i).getVolumePerUnit() + "\n"
                            +"\t\tpricePerUnit: " + locations.getMarketplaces().get(i).getPricePerUnit() + "\n"
                            +"\t\tspread: " + locations.getMarketplaces().get(i).getSpread() + "\n"
                            +"\t\tpurchasedPricePerUnit: " + locations.getMarketplaces().get(i).getPurchasePricePerUnit() + "\n"
                            +"\t\tsellPricePerUnit: " + locations.getMarketplaces().get(i).getSellPricePerUnit() + "\n"
                            +"\t\tquantityAvailable: " + locations.getMarketplaces().get(i).getQuantityAvailable() + "\n\n");
                    textFlow.getChildren().add(marketInfo);
                }
            }
            this.pane.getChildren().add(textFlow);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Marketplace listings are only visible to docked ships at this location.");
            alert.show();
            stage.close();
        }

    }
}