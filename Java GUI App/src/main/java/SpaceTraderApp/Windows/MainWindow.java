package SpaceTraderApp.Windows;

import SpaceTraderApp.Connections.Modes;
import SpaceTraderApp.Data.GameStatus;
import SpaceTraderApp.Data.User;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import SpaceTraderApp.Connections.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.Objects;

public class MainWindow {

    private static MainWindow mainWindow;
    private final Scene scene;
    private final AnchorPane anchorPane;

    public static Modes mode;
    public static String token = "";

    public static String version ="";

    private Button loginBtn;
    private Button signInBtn;
    private Button curUserInfoBtn;
    private Button availableLoan;
    private Button requestLoan;
    private Button myLoan;
    private Button listShips;
    private Button buyShip;
    private Button myShips;
    private Button purchaseGood;
    private Button viewMarketInfo;
    private Button tradeGood;
    private Button nearbyLocation;
    private Button createFlightPlan;
    private Button viewFlightPlan;

    /*
    Initialization MainWindow
     */
    private MainWindow() throws IOException {
        anchorPane = new AnchorPane();
        scene = new Scene(anchorPane, 1000, 800);

        if (Objects.equals(MainWindow.version, "online")) {
            mode = Online.getInstance();
        }else {
            mode = Offline.getInstance();
        }

        //Initialization components
        initGameVersionText();
        initButtons();
        initBtnActions();
        initText();
    }

    private void initText() {
        Text text = new Text(360, 400, "Space Trader");
        text.setFill(Color.web("#45068a"));
        text.setFont(Font.font(null, FontWeight.SEMI_BOLD,64));
        text.setRotate(45);

        DropShadow ds = new DropShadow();
        ds.setOffsetY(2.0);
        ds.setColor(Color.web("#25034a"));

        text.setEffect(ds);
        anchorPane.getChildren().add(text);
    }

    public static  MainWindow getInstance() throws IOException{
        if (mainWindow == null) {
            mainWindow = new MainWindow();
        }
        return mainWindow;
    }

    public Scene getScene() {
        return scene;
    }

    private void initGameVersionText() throws IOException {
        Label gameVersion = new Label();
        if (MainWindow.version.equals("online")) {
            System.out.println(MainWindow.version);
            gameVersion.setText("# Online Mode");
            gameVersion.setTextFill(Color.web("#85fc23"));
        }else {
            gameVersion.setText("# Offline Mode");
            gameVersion.setTextFill(Color.web("#b3baad"));
        }

        Label gameStatus = new Label();

        gameStatus.setLayoutX(25);
        gameStatus.setLayoutY(10);

        GameStatus gameStatusInfo = mode.gameStatus();
        gameStatus.setText("Game Status: \n" + gameStatusInfo.getStatus());
        gameStatus.setTextFill(Color.web("#9e1b40"));

        gameVersion.setLayoutX(900);
        gameVersion.setLayoutY(10);
        anchorPane.getChildren().addAll(gameVersion,gameStatus);
    }

    private void initButtons() {
        loginBtn = new Button();
        signInBtn = new Button();
        Button[] buttons = {loginBtn,signInBtn};
        String[] texts = {"Log in", "Sign in"};
        for (int i = 0; i < buttons.length; i++) {
            Button button = buttons[i];
            button.setText(texts[i]);
            button.setLayoutX(350 + 200 * i);
            button.setLayoutY(700);
            button.setPrefWidth(100);
            button.setPrefHeight(30);
            anchorPane.getChildren().add(button);
        }

        curUserInfoBtn = new Button();
        availableLoan = new Button();
        requestLoan = new Button();
        myLoan = new Button();
        listShips = new Button();
        buyShip = new Button();
        myShips = new Button();
        purchaseGood = new Button();
        viewMarketInfo = new Button();
        tradeGood = new Button();
        nearbyLocation = new Button();
        createFlightPlan = new Button();
        viewFlightPlan = new Button();

        Button[] buttons1 = {curUserInfoBtn,availableLoan,requestLoan,myLoan,listShips,buyShip, myShips
                , purchaseGood, viewMarketInfo, tradeGood, nearbyLocation, createFlightPlan, viewFlightPlan};
        String[] texts1 = {"User Info","Available Loan","Request Loan","My Loan","Available Ships","Buy Ship",
                "My ship","PurchaseGood", "Market Info", "Sell Good", "Location", "Create Flight","Current Flight"};
        for (int i = 0; i < buttons1.length; i++) {
            Button button = buttons1[i];
            button.setText(texts1[i]);
            button.setLayoutX(25);
            button.setLayoutY(80 + 50 * i);
            button.setPrefWidth(100);
            button.setPrefHeight(30);
            anchorPane.getChildren().add(button);
        }
    }

    private void initBtnActions() {
        loginBtn.setOnAction(event -> new LoginWindow());
        signInBtn.setOnAction(event -> new SignInWindow());
        curUserInfoBtn.setOnAction(event -> {
            try {
                new CurUserInfoWindow();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        });
        availableLoan.setOnAction(event -> {
            try {
                new AvailableLoanWindow();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        });
        requestLoan.setOnAction(event -> {
            try {
                new RequestLoanWindow();
            } catch (IOException | org.apache.hc.core5.http.ParseException e) {
                e.printStackTrace();
            }
        });

        myLoan.setOnAction(event -> {
            try {
                new MyLoanWindow();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        });
        listShips.setOnAction(event -> {
            try {
                new AvailableShipWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        buyShip.setOnAction(event -> {
            try {
                new PurchaseShipWindow();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        });
        myShips.setOnAction(event -> {
            try {
                new MyShipWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        purchaseGood.setOnAction(event -> {
            try {
                new PurchaseGoodWindow();
            } catch (IOException | org.apache.hc.core5.http.ParseException e) {
                e.printStackTrace();
            }
        });
        viewMarketInfo.setOnAction(event -> {
            try {
                new MarketInfoWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        nearbyLocation.setOnAction(event -> {
            try {
                new NearByLocationWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        createFlightPlan.setOnAction(event -> {
            try {
                new CreateFlightWindow();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        });
        viewFlightPlan.setOnAction(event -> {
            try {
                new CurrentFlightWindow();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        tradeGood.setOnAction(event -> {
            try {
                new SellGoodWindow();
            } catch (IOException | org.apache.hc.core5.http.ParseException e) {
                e.printStackTrace();
            }
        });
    }

}
