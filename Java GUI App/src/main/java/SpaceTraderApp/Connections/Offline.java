package SpaceTraderApp.Connections;

import SpaceTraderApp.Data.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Offline implements Modes{

    private static Offline offline;

    List<Ship> shipList = new ArrayList<>();
    List<Loan> loanList = new ArrayList<>();
    List<Cargo> cargoList = new ArrayList<>();
    List<Marketplace> marketplaceList = new ArrayList<>();
    FlightPlans flightPlans = new FlightPlans();
    private int credit = 0;


    private Offline(){}

    public static Offline getInstance() {
        if (offline == null) {
            offline = new Offline();
        }
        return offline;
    }
    @Override
    public User getUser(String token) throws IOException {
        if (token == null) {
            return null;
        }else {
            User dummy = new User();
            dummy.setCredits(0);
            dummy.setJoinedAt("dummy");
            dummy.setUsername("dummy");
            dummy.setStructureCount(0);
            dummy.setShipCount(100);
            return dummy;
        }
    }

    @Override
    public GameStatus gameStatus() throws IOException {
        GameStatus gameStatus = new GameStatus();
        gameStatus.setStatus("dummy status");
        return gameStatus;
    }

    @Override
    public UserData createUser(String username) throws IOException {
        if (username == null) {
            return null;
        }else {
            UserData dummy = new UserData();
            dummy.setToken("dummyToken");
            return dummy;
        }

    }

    @Override
    public Loans getLoans(String token) throws IOException {
        Loans loans = new Loans();
        List<Loan> loanList = new ArrayList<>();
        Loan loan = new Loan();
        loan.setAmount(100000000);
        loan.setCollateralRequired(true);
        loan.setRate(1000000);
        loan.setTermInDays(0);
        loan.setType("DUMMY");
        loanList.add(loan);
        loans.loans = loanList;
        return loans;

    }

    @Override
    public Loans takeOutLoan(String type, String token) throws IOException {
        if (type.equals("DUMMY")) {
            Loans loans = new Loans();
            loans.setCredits(1000000);
            Loan loan = new Loan();
            loan.setDue(String.valueOf(new Date()));
            loan.setId("dummy loan");
            loan.setRepaymentAmount(20000);
            loan.setStatus("CURRENT");
            loan.setType("STARTUP");
            loans.setLoan(loan);
            return loans;
        }
        return null;
    }

    @Override
    public Loans getMyLoans(String token) throws IOException {
        Loans loans = new Loans();
        List<Loan> loanList = new ArrayList<>();
        Loan loan = new Loan();
        loan.setAmount(100000000);
        loan.setCollateralRequired(true);
        loan.setRate(1000000);
        loan.setTermInDays(0);
        loan.setType("DUMMY LOAN");
        loanList.add(loan);
        loans.loans = loanList;
        return loans;
    }

    @Override
    public Ships getAvailableShips(String token, String systemSymbol) throws IOException {
        if (systemSymbol != null) {
            Ships ships = new Ships();
            List<Ship> shipList = new ArrayList<>();

            Ship ship = new Ship();
            ship.setType("dummy");
            ship.set_class("dummy");
            ship.setMaxCargo(1000);
            ship.setSpeed(1);
            ship.setManufacturer("me");
            ship.setPlating(1);
            ship.setWeapons(20);
            List<PurchaseLocation> purchaseLocations = new ArrayList<>();
            PurchaseLocation purchaseLocation = new PurchaseLocation();
            purchaseLocation.setPrice(10000);
            purchaseLocation.setLocation("dummy");
            purchaseLocation.setSystem("dummy");
            purchaseLocations.add(purchaseLocation);
            ship.setPurchaseLocations(purchaseLocations);

            Ship ship1 = new Ship();
            ship1.setType("dummy1");
            ship1.set_class("dummy1");
            ship1.setMaxCargo(10001);
            ship1.setSpeed(11);
            ship1.setManufacturer("me");
            ship1.setPlating(11);
            ship1.setWeapons(20);
            List<PurchaseLocation> purchaseLocations1 = new ArrayList<>();
            PurchaseLocation purchaseLocation1 = new PurchaseLocation();
            purchaseLocation1.setPrice(100001);
            purchaseLocation1.setLocation("dummy");
            purchaseLocation1.setSystem("dummy");
            purchaseLocations1.add(purchaseLocation1);
            ship1.setPurchaseLocations(purchaseLocations1);

            shipList.add(ship);
            shipList.add(ship1);
            ships.setShips(shipList);
            return ships;
        }
        return null;
    }

    @Override
    public Ships buyShip(String token, String location, String type) throws IOException {

        if (location != null && type != null) {
            Ships ships = new Ships();
            Ship ship = new Ship();
            ship.setId("1");
            ship.setLocation("dummy");
            ship.setX(0);
            ship.setY(0);
            ship.setSpaceAvailable(0);
            ship.setType("dummy");
            ship.set_class("null");
            ship.setMaxCargo(0);
            ship.setSpeed(0);
            ship.setManufacturer("me");
            ship.setPlating(0);
            ship.setWeapons(0);
            Cargo cargo = new Cargo();
            cargo.good = "null";
            cargo.quantity = 0;
            cargo.totalVolume = 0;
            cargoList.add(cargo);
            ship.setCargo(cargoList);

            shipList.add(ship);
            ships.setShips(shipList);
            ships.setCredits(10000);
            ships.setShip(ship);

//            user.setShips(shipList);
            credit -= 10000;
            return ships;
        }
        return null;
    }

    @Override
    public Ships getMyShips(String token) throws IOException {
        Ships ships = new Ships();
        Ship ship = new Ship();
        ship.setId("1");
        ship.setLocation("dummy");
        ship.setX(0);
        ship.setY(0);
        ship.setSpaceAvailable(0);
        ship.setType("dummy");
        ship.set_class("null");
        ship.setMaxCargo(0);
        ship.setSpeed(0);
        ship.setManufacturer("me");
        ship.setPlating(0);
        ship.setWeapons(0);
        Cargo cargo = new Cargo();
        cargo.good = "null";
        cargo.quantity = 0;
        cargo.totalVolume = 0;
        cargoList.add(cargo);
        ship.setCargo(cargoList);

        shipList.add(ship);
        ships.setShips(shipList);
        ships.setCredits(10000);
        ships.setShip(ship);

//            user.setShips(shipList);
        credit -= 10000;
        return ships;
    }

    @Override
    public PurchaseInfo purchaseFuel(String shipId, String good, int quantity, String token) throws IOException {
        PurchaseInfo purchaseGood = new PurchaseInfo();
        int cost = 2 * quantity;
        purchaseGood.setCredits(credit - cost);
        credit -= cost;
        Ship ship = shipList.get(0);
        Cargo cargo = new Cargo();
        cargo.setGood(good);
        cargo.setTotalVolume(cargo.getQuantity()+quantity);
        cargo.setQuantity(quantity);
        cargoList.add(cargo);
        ship.setCargo(cargoList);

        return purchaseGood;
    }

    @Override
    public MarketPlaces getMarketInfo(String location, String token) throws IOException {
        MarketPlaces marketPlaces = new MarketPlaces();
        Marketplace marketplace = new Marketplace();
        marketplace.setPricePerUnit(100);
        marketplace.setPurchasePricePerUnit(100);
        marketplace.setQuantityAvailable(1000);
        marketplace.setSpread(100);
        marketplace.setSymbol("DUMMY");
        marketplace.setVolumePerUnit(10);
        List<Marketplace> list = new ArrayList<>();
        list.add(marketplace);
        marketPlaces.setMarketplaces(list);
        return marketPlaces;
    }

    @Override
    public Locations getNearByLocation(String systemSymbol, String token) throws IOException {
        Locations locations = new Locations();
        Location location = new Location();
        location.setSymbol("DD-DD");
        location.setType("DUMMY");
        location.setName("DUUUUMMMMMMMYYY");
        location.setX(0);
        location.setY(0);
        List<Location> list = new ArrayList<>();
        list.add(location);
        locations.setLocationList(list);
        return locations;
    }

    @Override
    public FlightPlans createFlightPlan(String shipId, String destId, String token) throws IOException {
        FlightPlan flightPlan = new FlightPlan();
        List<FlightPlan> flightPlanList = new ArrayList<>();
        flightPlan.setArrivesAt(String.valueOf(new Date()));
        flightPlan.setDeparture("DD-DD");
        flightPlan.setDestination("DU-MY");
        flightPlan.setDistance(1);
        flightPlan.setFuelConsumed(1);
        flightPlan.setFuelConsumed(9);
        flightPlan.setId("dd-dm");
        flightPlan.setShipId("1");
        flightPlan.setTerminatedAt(null);
        flightPlan.setTimeRemainingInSeconds(100);
        flightPlanList.add(flightPlan);
        flightPlans.setFlightPlan(flightPlan);
        flightPlans.setFlightPlans(flightPlanList);
        return flightPlans;
    }

    @Override
    public FlightPlans getCurFlightPlan(String flightId, String token) throws IOException {
        return flightPlans;
    }

    @Override
    public PurchaseInfo sellGood(String shipId, String good, int quantity, String token) throws IOException {
        PurchaseInfo purchaseGood = new PurchaseInfo();
        purchaseGood.setCredits(credit);
        return purchaseGood;
    }
}
