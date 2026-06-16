// Route.java
// This class holds all the information about a single route between two cities


public class Route {

    String fromCity;
    String toCity;
    double distance;   // in kilometers
    double cost;       // in PKR or USD depending on type
    String routeType;  // either "railway" or "flight"
    String routeName;  // like "PK-101" or "Express-5"
    int travelTime;    // in minutes

    // constructor to create a new route
    public Route(String fromCity, String toCity, double distance, double cost, String routeType, String routeName, int travelTime) {
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.distance = distance;
        this.cost = cost;
        this.routeType = routeType;
        this.routeName = routeName;
        this.travelTime = travelTime;
    }

    // getter methods
    public String getFromCity() {
        return fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public double getDistance() {
        return distance;
    }

    public double getCost() {
        return cost;
    }

    public String getRouteType() {
        return routeType;
    }

    public String getRouteName() {
        return routeName;
    }

    public int getTravelTime() {
        return travelTime;
    }

    // setter methods - useful when admin wants to update route info
    public void setCost(double newCost) {
        this.cost = newCost;
    }

    public void setDistance(double newDistance) {
        this.distance = newDistance;
    }

    public void setTravelTime(int newTime) {
        this.travelTime = newTime;
    }

    // this method just prints route info in a readable way
    @Override
    public String toString() {
        return "[" + routeType.toUpperCase() + "] " + routeName + " | " +
               fromCity + " --> " + toCity +
               " | Distance: " + distance + " km" +
               " | Cost: " + cost +
               " | Time: " + travelTime + " mins";
    }
}
