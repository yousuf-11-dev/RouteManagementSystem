// RouteStatistics.java
// This class shows route statistics and analytics
// We use TreeMap here because TreeMap keeps data sorted automatically
// So we can easily display routes sorted by cost or distance

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;

public class RouteStatistics {

    Graph graph;

    public RouteStatistics(Graph graph) {
        this.graph = graph;
    }

    // display all routes sorted by cost using TreeMap
    // TreeMap naturally sorts by key so we use cost as key
    public void displayRoutesByCost() {
        System.out.println("\n--- All Routes Sorted by Cost ---");

        // TreeMap sorts by key automatically
        TreeMap<Double, ArrayList<Route>> sortedByCost = new TreeMap<>();

        // go through all cities and collect routes
        for (String city : graph.cityRoutes.keySet()) {
            ArrayList<Route> routes = graph.cityRoutes.get(city);
            for (Route r : routes) {
                // skip reverse routes to avoid duplicates
                if (r.getRouteName().endsWith("-R")) continue;

                double cost = r.getCost();

                if (!sortedByCost.containsKey(cost)) {
                    sortedByCost.put(cost, new ArrayList<>());
                }
                sortedByCost.get(cost).add(r);
            }
        }

        if (sortedByCost.isEmpty()) {
            System.out.println("No routes available.");
            return;
        }

        int count = 1;
        for (Double cost : sortedByCost.keySet()) {
            ArrayList<Route> routes = sortedByCost.get(cost);
            for (Route r : routes) {
                System.out.println(count + ". " + r);
                count++;
            }
        }
    }

    // display all routes sorted by distance
    public void displayRoutesByDistance() {
        System.out.println("\n--- All Routes Sorted by Distance ---");

        TreeMap<Double, ArrayList<Route>> sortedByDist = new TreeMap<>();

        for (String city : graph.cityRoutes.keySet()) {
            ArrayList<Route> routes = graph.cityRoutes.get(city);
            for (Route r : routes) {
                if (r.getRouteName().endsWith("-R")) continue;

                double dist = r.getDistance();

                if (!sortedByDist.containsKey(dist)) {
                    sortedByDist.put(dist, new ArrayList<>());
                }
                sortedByDist.get(dist).add(r);
            }
        }

        if (sortedByDist.isEmpty()) {
            System.out.println("No routes available.");
            return;
        }

        int count = 1;
        for (Double dist : sortedByDist.keySet()) {
            ArrayList<Route> routes = sortedByDist.get(dist);
            for (Route r : routes) {
                System.out.println(count + ". " + r);
                count++;
            }
        }
    }

    // show basic stats like total cities, total routes etc
    public void showGeneralStats() {
        System.out.println("\n--- General Statistics ---");

        int totalCities = graph.getTotalCities();
        int totalRoutes = 0;
        int railwayCount = 0;
        int flightCount = 0;

        for (String city : graph.cityRoutes.keySet()) {
            ArrayList<Route> routes = graph.cityRoutes.get(city);
            for (Route r : routes) {
                if (!r.getRouteName().endsWith("-R")) { // skip reverse routes
                    totalRoutes++;
                    if (r.getRouteType().equalsIgnoreCase("railway")) {
                        railwayCount++;
                    } else if (r.getRouteType().equalsIgnoreCase("flight")) {
                        flightCount++;
                    }
                }
            }
        }

        System.out.println("Total Cities: " + totalCities);
        System.out.println("Total Routes: " + totalRoutes);
        System.out.println("Railway Routes: " + railwayCount);
        System.out.println("Flight Routes: " + flightCount);
    }

    // find and display most expensive route
    public void findMostExpensiveRoute() {
        Route expensive = null;

        for (String city : graph.cityRoutes.keySet()) {
            ArrayList<Route> routes = graph.cityRoutes.get(city);
            for (Route r : routes) {
                if (r.getRouteName().endsWith("-R")) continue;
                if (expensive == null || r.getCost() > expensive.getCost()) {
                    expensive = r;
                }
            }
        }

        System.out.println("\n--- Most Expensive Route ---");
        if (expensive == null) {
            System.out.println("No routes found.");
        } else {
            System.out.println(expensive);
        }
    }

    // find cheapest route
    public void findCheapestRoute() {
        Route cheapest = null;

        for (String city : graph.cityRoutes.keySet()) {
            ArrayList<Route> routes = graph.cityRoutes.get(city);
            for (Route r : routes) {
                if (r.getRouteName().endsWith("-R")) continue;
                if (cheapest == null || r.getCost() < cheapest.getCost()) {
                    cheapest = r;
                }
            }
        }

        System.out.println("\n--- Cheapest Route ---");
        if (cheapest == null) {
            System.out.println("No routes found.");
        } else {
            System.out.println(cheapest);
        }
    }
}
