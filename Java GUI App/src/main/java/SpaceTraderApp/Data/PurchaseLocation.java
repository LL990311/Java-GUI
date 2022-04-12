package SpaceTraderApp.Data;

public class PurchaseLocation {
    public String system;
    public String location;
    public int price;

    public String getSystem() {
        return system;
    }

    public String getLocation() {
        return location;
    }

    public int getPrice() {
        return price;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSystem(String system) {
        this.system = system;
    }
}
