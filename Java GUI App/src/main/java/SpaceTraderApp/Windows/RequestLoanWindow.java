package SpaceTraderApp.Windows;

import SpaceTraderApp.Data.Loans;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.Objects;

public class RequestLoanWindow {
    private final Stage stage;
    private final AnchorPane pane;
    private TextField inputType;

    public RequestLoanWindow() throws IOException, ParseException {
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
        text.setLayoutX(25);
        text.setLayoutY(35);
        inputType = new TextField();
        inputType.setLayoutX(90);
        inputType.setLayoutY(70);
        inputType.setPrefWidth(120);
        inputType.setPromptText("Type");
        pane.getChildren().addAll(inputType,text);
    }

    private void RequestAction() throws IOException {
        String type = inputType.getText();
        try {
            Loans loans = MainWindow.mode.takeOutLoan(type,MainWindow.token);
            if(loans.getCredits() != 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "request successfully");
                alert.show();
                stage.close();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Something went Wrong!");
                alert.show();
                stage.close();
            }
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Check your input and Login status!");
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
                RequestAction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
