package SpaceTraderApp.Data;

public class Order{
    public String good;
    public int pricePerUnit;
    public int quantity;
    public int total;

    public String getGood() {
        return good;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}