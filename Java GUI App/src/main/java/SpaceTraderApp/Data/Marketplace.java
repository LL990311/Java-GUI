package SpaceTraderApp.Data;

public class Marketplace {
    int pricePerUnit;
    int purchasePricePerUnit;
    int quantityAvailable;
    int sellPricePerUnit;
    int spread;
    String symbol;
    int volumePerUnit;

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public int getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public void setPurchasePricePerUnit(int purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public int getSellPricePerUnit() {
        return sellPricePerUnit;
    }

    public void setSellPricePerUnit(int sellPricePerUnit) {
        this.sellPricePerUnit = sellPricePerUnit;
    }

    public int getSpread() {
        return spread;
    }

    public void setSpread(int spread) {
        this.spread = spread;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getVolumePerUnit() {
        return volumePerUnit;
    }

    public void setVolumePerUnit(int volumePerUnit) {
        this.volumePerUnit = volumePerUnit;
    }
}
