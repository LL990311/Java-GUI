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

public class MyLoanWindow {
    private final Stage stage;
    private final AnchorPane pane;

    public MyLoanWindow() throws IOException, ParseException {
        stage = new Stage();
        pane = new AnchorPane();
        Scene scene = new Scene(pane, 300,400);
        stage.setScene(scene);
        stage.setTitle("Available Loan");
        stage.show();

        initText();
    }

    private void initText() throws IOException {
        Loans loans = MainWindow.mode.getMyLoans(MainWindow.token);
        Text loansText = new Text();
        try {
            for (int i = 0; i < loans.getLoans().size(); i++) {
                loansText.setText("id: " + loans.getLoans().get(i).getId() +"\n"
                        +"due: " + loans.getLoans().get(i).getDue() + "\n"
                        +"repaymentAmount: " + loans.getLoans().get(i).getRepaymentAmount() + "\n"
                        +"status: " + loans.getLoans().get(i).getStatus() + "\n"
                        +"type: " + loans.getLoans().get(i).getType() + "\n");
            }
            TextFlow textFlow = new TextFlow();
            textFlow.getChildren().addAll(loansText);
            textFlow.setLineSpacing(1.5);
            this.pane.getChildren().add(textFlow);
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You need to login first");
            alert.show();
            stage.close();
        }

    }
}
