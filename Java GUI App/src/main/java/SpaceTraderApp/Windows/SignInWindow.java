package SpaceTraderApp.Windows;

import SpaceTraderApp.Data.User;
import SpaceTraderApp.Data.UserData;
import com.sun.tools.javac.Main;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInWindow {
    private final AnchorPane pane;
    private final Stage stage;
    private TextField inputUsername;

    public SignInWindow() {
        stage = new Stage();
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 600, 170);
        stage.setScene(scene);
        stage.setTitle("Sign in Window");
        stage.show();
        initButtons();
        initInputFields();
        initText();
    }

    private void initInputFields() {
        inputUsername = new TextField();
        inputUsername.setLayoutX(50);
        inputUsername.setLayoutY(60);
        inputUsername.setPrefWidth(400);
        inputUsername.setPromptText("Please input your username");
        pane.getChildren().add(inputUsername);
    }

    private void signInAction() throws IOException {
        String username = inputUsername.getText();
        UserData userInfo = MainWindow.mode.createUser(username);
        if (userInfo.getToken() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Username has already been claimed");
            alert.show();
        }else {
            MainWindow.token = userInfo.getToken();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Sign in successfully"
                    + "\nYou can copy your token at command line");
            System.out.println(MainWindow.token);
            alert.show();
            stage.close();
        }
    }


    private void initButtons() {
        Button signInButton = new Button();
        signInButton.setLayoutX(250);
        signInButton.setLayoutY(130);
        signInButton.setPrefWidth(100);
        signInButton.setText("Sign In");
        signInButton.requestFocus();
        pane.getChildren().add(signInButton);
        signInButton.setOnAction(event -> {
            try {
                signInAction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void initText() {
        Text username = new Text(50,40,"Username:");
        pane.getChildren().add(username);
    }
}
