// Graph.java
// This is the main graph class that stores all cities and their connections
// We are using adjacency list representation here using HashMap and ArrayList
// Each city maps to a list of routes going out from it

import java.util.HashMap;
import java.util.ArrayList;

public class Graph {

    // this hashmap stores city name -> list of routes from that city
    HashMap<String, ArrayList<Route>> cityRoutes;

    // constructor
    public Graph() {
        cityRoutes = new HashMap<>();
    }

    // add a new city to the graph if it doesnt already exist
    public void addCity(String cityName) {
        if (!cityRoutes.containsKey(cityName)) {
            cityRoutes.put(cityName, new ArrayList<>());
            System.out.println("City added: " + cityName);
        } else {
            System.out.println("City already exists: " + cityName);
        }
    }

    // add a route between two cities
    // routes are bidirectional (you can go both ways)
    public void addRoute(Route r) {
        // make sure both cities exist first
        addCity(r.getFromCity());
        addCity(r.getToCity());

        // add route in both directions
        cityRoutes.get(r.getFromCity()).add(r);

        // also add reverse route so travel works both ways
        Route reverseRoute = new Route(
            r.getToCity(), r.getFromCity(),
            r.getDistance(), r.getCost(),
            r.getRouteType(), r.getRouteName() + "-R",
            r.getTravelTime()
        );
        cityRoutes.get(r.getToCity()).add(reverseRoute);

        System.out.println("Route added: " + r.getFromCity() + " <--> " + r.getToCity());
    }

    // remove a route between two cities by route name
    public boolean removeRoute(String routeName) {
        boolean found = false;

        for (String city : cityRoutes.keySet()) {
            ArrayList<Route> routes = cityRoutes.get(city);
            // use iterator style loop to safely remove
            for (int i = 0; i < routes.size(); i++) {
                String rName = routes.get(i).getRouteName();
                // check both original and reverse route names
                if (rName.equals(routeName) || rName.equals(routeName + "-R")) {
                    routes.remove(i);
                    i--; // adjust index after removal
                    found = true;
                }
            }
        }

        if (found) {
            System.out.println("Route '" + routeName + "' removed successfully.");
        } else {
            System.out.println("Route '" + routeName + "' not found.");
        }

        return found;
    }

    // remove a city and all its routes
    public void removeCity(String cityName) {
        if (!cityRoutes.containsKey(cityName)) {
            System.out.println("City not found: " + cityName);
            return;
        }

        // remove the city from the map
        cityRoutes.remove(cityName);

        // also remove all routes that go TO this city from other cities
        for (String city : cityRoutes.keySet()) {
            ArrayList<Route> routes = cityRoutes.get(city);
            for (int i = 0; i < routes.size(); i++) {
                if (routes.get(i).getToCity().equals(cityName)) {
                    routes.remove(i);
                    i--;
                }
            }
        }

        System.out.println("City '" + cityName + "' and all its routes removed.");
    }

    // get all routes from a specific city
    public ArrayList<Route> getRoutesFromCity(String cityName) {
        if (cityRoutes.containsKey(cityName)) {
            return cityRoutes.get(cityName);
        }
        return new ArrayList<>(); // empty list if city not found
    }

    // display all cities in the system
    public void displayAllCities() {
        System.out.println("\n--- All Cities in System ---");
        if (cityRoutes.isEmpty()) {
            System.out.println("No cities added yet.");
            return;
        }
        int count = 1;
        for (String city : cityRoutes.keySet()) {
            System.out.println(count + ". " + city);
            count++;
        }
    }

    // display all routes from a specific city
    public void displayRoutesFromCity(String cityName) {
        System.out.println("\n--- Routes from " + cityName + " ---");
        ArrayList<Route> routes = getRoutesFromCity(cityName);

        if (routes.isEmpty()) {
            System.out.println("No routes found from " + cityName);
            return;
        }

        for (Route r : routes) {
            System.out.println(r);
        }
    }

    // check if a city exists in system
    public boolean cityExists(String cityName) {
        return cityRoutes.containsKey(cityName);
    }

    // get total number of cities
    public int getTotalCities() {
        return cityRoutes.size();
    }
}
