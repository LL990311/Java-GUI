package SpaceTraderApp.Data;

import org.json.JSONPropertyName;

import java.util.List;

public class Ship {
    public String type;
    public String _class;
    public int maxCargo;
    public int speed;
    public String manufacturer;
    public int plating;
    public int weapons;
    public List<PurchaseLocation> purchaseLocations;
    public List<Cargo> cargo;
    public String id;
    public String location;
    public int spaceAvailable;

    public void setType(String type) {
        this.type = type;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public void setMaxCargo(int maxCargo) {
        this.maxCargo = maxCargo;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPlating(int plating) {
        this.plating = plating;
    }

    public void setWeapons(int weapons) {
        this.weapons = weapons;
    }

    public void setPurchaseLocations(List<PurchaseLocation> purchaseLocations) {
        this.purchaseLocations = purchaseLocations;
    }

    public void setCargo(List<Cargo> cargo) {
        this.cargo = cargo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSpaceAvailable(int spaceAvailable) {
        this.spaceAvailable = spaceAvailable;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<Cargo> getCargo() {
        return cargo;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public int getSpaceAvailable() {
        return spaceAvailable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int x;
    public int y;

    public String getType() {
        return type;
    }

    public String get_class() {
        return _class;
    }

    public int getMaxCargo() {
        return maxCargo;
    }

    public int getSpeed() {
        return speed;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getPlating() {
        return plating;
    }

    public int getWeapons() {
        return weapons;
    }

    public List<PurchaseLocation> getPurchaseLocations() {
        return purchaseLocations;
    }
}
