package SpaceTraderApp.Data;

import java.util.List;

public class Ships {
    public List<Ship> ships;
    public int credits;
    public Ship ship;

    public List<Ship> getShips() {
        return ships;
    }

    public int getCredits() {
        return credits;
    }

    public Ship getShip() {
        return ship;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }
}
