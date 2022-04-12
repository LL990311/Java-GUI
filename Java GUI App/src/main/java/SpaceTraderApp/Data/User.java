package SpaceTraderApp.Data;

public class User {
    private float credits;
    private String joinedAt;
    private float shipCount;
    private float structureCount;
    private String username;


    // Getter Methods

    public float getCredits() {
        return credits;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public float getShipCount() {
        return shipCount;
    }

    public float getStructureCount() {
        return structureCount;
    }

    public String getUsername() {
        return username;
    }

    // Setter Methods

    public void setCredits(float credits) {
        this.credits = credits;
    }

    public void setJoinedAt(String joinedAt) {
        this.joinedAt = joinedAt;
    }

    public void setShipCount(float shipCount) {
        this.shipCount = shipCount;
    }

    public void setStructureCount(float structureCount) {
        this.structureCount = structureCount;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
