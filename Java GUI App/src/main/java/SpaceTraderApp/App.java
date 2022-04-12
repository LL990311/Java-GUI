package SpaceTraderApp;

import SpaceTraderApp.Windows.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

  // "ca508742-02a2-43e2-a451-a9eabbd05470"
    //cl1rmp5on92086015s6815obxvj
    //cl1rpxth2118727215s6lgsm0s2o

    @Override
    public void start(Stage stage) throws IOException {
        MainWindow mainWindow = MainWindow.getInstance();
        stage.setScene(mainWindow.getScene());
        stage.setTitle("SpaceTrader");
        stage.show();
    }
    public static void main(String[] args) {
        if (args.length > 0) {
            MainWindow.version = args[0];
            launch();
        }else {
            System.out.println("Please input 'gradle run --args=\"offline || online\"'");
            System.exit(0);
        }

    }
}