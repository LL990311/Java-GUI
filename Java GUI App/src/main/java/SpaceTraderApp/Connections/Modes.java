package SpaceTraderApp.Connections;

import SpaceTraderApp.Data.*;

import java.io.IOException;

public interface Modes {

    User getUser(String token) throws IOException;

    GameStatus gameStatus() throws IOException;

    UserData createUser(String username) throws IOException;

    Loans getLoans(String token) throws IOException;

    Loans takeOutLoan(String type,String token) throws IOException;

    Loans getMyLoans(String token) throws IOException;

    Ships getAvailableShips(String token, String systemSymbol) throws IOException;

    Ships buyShip(String token, String location, String type) throws IOException;

    Ships getMyShips(String token) throws IOException;

    PurchaseInfo purchaseFuel(String shipId, String good,int quantity, String token) throws IOException;

    MarketPlaces getMarketInfo(String location, String token) throws IOException;

    Locations getNearByLocation(String systemSymbol, String token) throws IOException;

    FlightPlans createFlightPlan(String shipId, String destId, String token) throws IOException;

    FlightPlans getCurFlightPlan(String flightId, String token) throws IOException;

    PurchaseInfo sellGood(String shipId, String good, int quantity, String token) throws IOException;
}
