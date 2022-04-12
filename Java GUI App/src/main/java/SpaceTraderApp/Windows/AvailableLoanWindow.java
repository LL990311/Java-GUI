package SpaceTraderApp.Windows;

import SpaceTraderApp.Data.Loans;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class AvailableLoanWindow {
    private final Stage stage;
    private final AnchorPane pane;

    public AvailableLoanWindow() throws IOException, ParseException {
        stage = new Stage();
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 300,400);
        stage.setScene(scene);
        stage.setTitle("Available Loan");
        stage.show();

        initText();
    }

    private void initText() throws IOException {
        Loans loans = MainWindow.mode.getLoans(MainWindow.token);
        Text loansText = new Text();
        loansText.setLineSpacing(1.5);
        try {
            for (int i = 0; i < loans.getLoans().size(); i++) {
                loansText.setText("type: " + loans.getLoans().get(i).getType() +"\n"
                        +"amount: " + loans.getLoans().get(i).getAmount() + "\n"
                        +"rate: " + loans.getLoans().get(i).getRate() + "\n"
                        +"termInDays: " + loans.getLoans().get(i).getTermInDays() + "\n"
                        +"collateralRequired: " + loans.getLoans().get(i).isCollateralRequired() + "\n");
            }
            TextFlow textFlow = new TextFlow();
            textFlow.getChildren().addAll(loansText);
            this.pane.getChildren().add(textFlow);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You need to login first");
            alert.show();
            stage.close();
        }

    }
}
