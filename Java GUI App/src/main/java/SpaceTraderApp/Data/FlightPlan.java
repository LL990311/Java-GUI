package SpaceTraderApp.Data;

public class FlightPlan {
    private String arrivesAt;
    private String createdAt;
    private String departure;
    private String destination;
    private float distance;
    private float fuelConsumed;
    private float fuelRemaining;
    private String id;
    private String shipId;
    private String terminatedAt = null;
    private float timeRemainingInSeconds;


    // Getter Methods

    public String getArrivesAt() {
        return arrivesAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public float getDistance() {
        return distance;
    }

    public float getFuelConsumed() {
        return fuelConsumed;
    }

    public float getFuelRemaining() {
        return fuelRemaining;
    }

    public String getId() {
        return id;
    }

    public String getShipId() {
        return shipId;
    }

    public String getTerminatedAt() {
        return terminatedAt;
    }

    public float getTimeRemainingInSeconds() {
        return timeRemainingInSeconds;
    }

    // Setter Methods

    public void setArrivesAt(String arrivesAt) {
        this.arrivesAt = arrivesAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setFuelConsumed(float fuelConsumed) {
        this.fuelConsumed = fuelConsumed;
    }

    public void setFuelRemaining(float fuelRemaining) {
        this.fuelRemaining = fuelRemaining;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }

    public void setTerminatedAt(String terminatedAt) {
        this.terminatedAt = terminatedAt;
    }

    public void setTimeRemainingInSeconds(float timeRemainingInSeconds) {
        this.timeRemainingInSeconds = timeRemainingInSeconds;
    }
}