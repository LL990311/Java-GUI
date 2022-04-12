package SpaceTraderApp.Data;

public class PurchaseInfo {
    public int credits;
    public Order order;
    public Ship ship;

    public int getCredits() {
        return credits;
    }

    public Order getOrder() {
        return order;
    }

    public Ship getShip() {
        return ship;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }
}
