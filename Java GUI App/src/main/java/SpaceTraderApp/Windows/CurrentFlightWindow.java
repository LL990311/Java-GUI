package SpaceTraderApp.Windows;

import SpaceTraderApp.Data.FlightPlans;
import com.sun.tools.javac.Main;
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

public class CurrentFlightWindow{
    private final Stage stage;
    private final AnchorPane pane;
    private ScrollBar scrollBar = new ScrollBar();
    private TextFlow textFlow;

    private TextField symbol;
    private Button requestBtn;

    public CurrentFlightWindow() throws IOException{
        stage = new Stage();
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 300, 400);
        stage.setScene(scene);
        stage.setTitle("MarketInfo");
        stage.show();
        initInputFields();
        initButtons();
    }

    private void initInputFields() {
        symbol = new TextField();
        symbol.setLayoutX(50);
        symbol.setLayoutY(60);
        symbol.setPrefWidth(200);
        symbol.setPromptText("flightId");
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
            String flightId = symbol.getText();
            pane.getChildren().removeAll(symbol,requestBtn);
            FlightPlans flightPlans = MainWindow.mode.getCurFlightPlan(flightId, MainWindow.token);
            if (flightPlans == null) {
                System.out.println("______________________-");
            }
            textFlow = new TextFlow();
            textFlow.setLineSpacing(1.5);
            Text curFlight = new Text("FlightPlan:\n"
                    +"\t\tarriveAt: " + flightPlans.getFlightPlan().getArrivesAt() + "\n"
                    +"\t\tcreatedAt: " + flightPlans.getFlightPlan().getCreatedAt() + "\n"
                    +"\t\tdeparture: " + flightPlans.getFlightPlan().getDeparture() + "\n"
                    +"\t\tdestination: " + flightPlans.getFlightPlan().getDestination() + "\n"
                    +"\t\tdistance: " + flightPlans.getFlightPlan().getDistance() + "\n"
                    +"\t\tfuelConsumed: " + flightPlans.getFlightPlan().getFuelConsumed() + "\n"
                    +"\t\tfuelRemaining: " + flightPlans.getFlightPlan().getFuelRemaining() + "\n"
                    +"\t\tid: " + flightPlans.getFlightPlan().getId() + "\n"
                    +"\t\tshipId: " + flightPlans.getFlightPlan().getShipId() + "\n"
                    +"\t\tterminatedAt: " + flightPlans.getFlightPlan().getTerminatedAt() + "\n"
                    +"\t\ttimeRemainingSeconds: " + flightPlans.getFlightPlan().getTimeRemainingInSeconds() + "\n");
            textFlow.getChildren().add(curFlight);
            this.pane.getChildren().add(textFlow);
        }catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Something went Wrong XoX");
            alert.show();
            stage.close();
        }

    }
}
