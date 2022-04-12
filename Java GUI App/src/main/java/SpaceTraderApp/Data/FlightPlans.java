package SpaceTraderApp.Data;

import java.util.List;

public class FlightPlans {
    public List<FlightPlan> flightPlans;
    public FlightPlan flightPlan;

    public List<FlightPlan> getFlightPlans() {
        return flightPlans;
    }

    public FlightPlan getFlightPlan() {
        return flightPlan;
    }

    public void setFlightPlans(List<FlightPlan> flightPlans) {
        this.flightPlans = flightPlans;
    }

    public void setFlightPlan(FlightPlan flightPlan) {
        this.flightPlan = flightPlan;
    }
}
