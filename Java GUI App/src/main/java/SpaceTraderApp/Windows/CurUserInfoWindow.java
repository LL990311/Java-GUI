package SpaceTraderApp.Windows;

import SpaceTraderApp.Data.User;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class CurUserInfoWindow {
    private final Stage stage;
    private final AnchorPane pane;

    TextFlow textFlow;

    public CurUserInfoWindow() throws IOException, ParseException {
        stage = new Stage();
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 300,400);
        pane.setPadding(new Insets(10,10,10,10));
        stage.setScene(scene);
        stage.setTitle("User Info");
        stage.show();

        initText();
    }

    public void initText() throws IOException, ParseException {
        try {
            User user = MainWindow.mode.getUser(MainWindow.token);

            textFlow = new TextFlow();
            textFlow.setLineSpacing(1.5);
            Text userName = new Text("Username: " + user.getUsername() + "\n"
                    + "Credits: " + user.getCredits() + "\n"
                    + "JoinedAt: " + user.getJoinedAt() + "\n"
                    + "shipCount: " + user.getShipCount() + "\n"
                    + "StructureCount: " + user.getStructureCount() + "\n");
            userName.setFont(Font.font(14));

            textFlow.getChildren().add(userName);

            this.pane.getChildren().add(textFlow);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You need to login first!");
            alert.show();
            stage.close();
        }

    }
}
