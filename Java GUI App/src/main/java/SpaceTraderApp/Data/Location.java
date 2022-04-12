package SpaceTraderApp.Data;

public class Location {
    boolean allowsConstruction;
    String name;
    String symbol;
    String type;
    int x;
    int y;
    String[] traits;

    public String[] getTraits() {
        return traits;
    }

    public void setTraits(String[] traits) {
        this.traits = traits;
    }

    public boolean isAllowsConstruction() {
        return allowsConstruction;
    }

    public void setAllowsConstruction(boolean allowsConstruction) {
        this.allowsConstruction = allowsConstruction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
