package SpaceTraderApp.Data;

public class Cargo{
    public String good;
    public int quantity;
    public int totalVolume;

    public String getGood() {
        return good;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalVolume() {
        return totalVolume;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalVolume(int totalVolume) {
        this.totalVolume = totalVolume;
    }
}
