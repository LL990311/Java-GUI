package SpaceTraderApp.Windows;

import SpaceTraderApp.Data.User;
import SpaceTraderApp.Data.UserData;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginWindow {

    private final AnchorPane anchorPane;
    private final Stage stage;
    private TextField inputToken;

    public LoginWindow() {
        stage = new Stage();
        anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane, 600, 170);
        stage.setScene(scene);
        stage.setTitle("Login Window");
        stage.show();
        initButtons();
        initInputFields();
        initText();
    }

    private void initInputFields() {
        inputToken = new TextField();
        inputToken.setLayoutX(50);
        inputToken.setLayoutY(60);
        inputToken.setPrefWidth(400);
        inputToken.setPromptText("Please input your token");
        anchorPane.getChildren().add(inputToken);
    }

    private void loginAction() throws IOException {
        String inputTokenText = inputToken.getText();
        User userInfo = MainWindow.mode.getUser(inputTokenText);
        if (userInfo != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Login in successfully");
            alert.show();
            MainWindow.token = inputTokenText;
            stage.close();
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please check your input!");
            alert.show();
        }
    }

    private void initButtons() {
        Button loginButton = new Button();
        loginButton.setLayoutX(250);
        loginButton.setLayoutY(130);
        loginButton.setPrefWidth(100);
        loginButton.setText("Login");
        loginButton.requestFocus();
        anchorPane.getChildren().add(loginButton);
        loginButton.setOnAction(event -> {
            try{
                loginAction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initText() {
        Text token = new Text(50, 50, "Token:");
        anchorPane.getChildren().addAll(token);
    }

}
