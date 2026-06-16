// DijkstraSearch.java
// This class is used to find the shortest/cheapest path between two cities
// We are using Dijkstra's algorithm with Priority Queue as learned in class
// Priority queue helps us always process the cheapest option first

import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class DijkstraSearch {

    // inner class to hold node info during dijkstra processing
    // each node has a city name and the cost to reach it from start
    static class CityNode {
        String cityName;
        double totalCost;
        String previousCity; // to help us trace back the path

        public CityNode(String cityName, double totalCost, String previousCity) {
            this.cityName = cityName;
            this.totalCost = totalCost;
            this.previousCity = previousCity;
        }
    }

    Graph graph; // reference to the main graph

    public DijkstraSearch(Graph graph) {
        this.graph = graph;
    }

    // find the cheapest path from source to destination
    // returns the list of cities in the path or null if no path found
    public ArrayList<String> findCheapestPath(String sourceCity, String destCity) {

        if (!graph.cityExists(sourceCity) || !graph.cityExists(destCity)) {
            System.out.println("One or both cities not found in system.");
            return null;
        }

        // cost map - tracks minimum cost to reach each city
        HashMap<String, Double> minCost = new HashMap<>();

        // previous city map - helps us rebuild the path at the end
        HashMap<String, String> prevCity = new HashMap<>();

        // set all initial costs to infinity (very high number)
        for (String city : graph.cityRoutes.keySet()) {
            minCost.put(city, Double.MAX_VALUE);
            prevCity.put(city, null);
        }

        // cost to reach source city from itself is 0
        minCost.put(sourceCity, 0.0);

        // priority queue sorted by cost (cheapest first)
        PriorityQueue<CityNode> pq = new PriorityQueue<>(
            Comparator.comparingDouble(node -> node.totalCost)
        );

        pq.add(new CityNode(sourceCity, 0.0, null));

        // set to track visited cities so we dont process them again
        ArrayList<String> visited = new ArrayList<>();

        while (!pq.isEmpty()) {
            CityNode current = pq.poll(); // get city with lowest cost

            // skip if already visited
            if (visited.contains(current.cityName)) {
                continue;
            }

            visited.add(current.cityName);

            // if we reached destination stop here
            if (current.cityName.equals(destCity)) {
                break;
            }

            // look at all neighboring cities
            ArrayList<Route> neighbors = graph.getRoutesFromCity(current.cityName);

            for (Route r : neighbors) {
                String neighbor = r.getToCity();
                double newCost = minCost.get(current.cityName) + r.getCost();

                // if we found a cheaper way to reach this neighbor
                if (newCost < minCost.get(neighbor)) {
                    minCost.put(neighbor, newCost);
                    prevCity.put(neighbor, current.cityName);
                    pq.add(new CityNode(neighbor, newCost, current.cityName));
                }
            }
        }

        // check if destination was reachable
        if (minCost.get(destCity) == Double.MAX_VALUE) {
            System.out.println("No path found from " + sourceCity + " to " + destCity);
            return null;
        }

        // rebuild the path by going backwards from destination
        ArrayList<String> path = new ArrayList<>();
        String current = destCity;

        while (current != null) {
            path.add(0, current); // add to front of list
            current = prevCity.get(current);
        }

        System.out.println("\n--- Cheapest Path Found ---");
        System.out.print("Path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) System.out.print(" --> ");
        }
        System.out.println();
        System.out.println("Total Cost: " + minCost.get(destCity));

        return path;
    }

    // find shortest path by distance instead of cost
    // same algorithm but we compare distance instead of cost
    public ArrayList<String> findShortestPath(String sourceCity, String destCity) {

        if (!graph.cityExists(sourceCity) || !graph.cityExists(destCity)) {
            System.out.println("One or both cities not found in system.");
            return null;
        }

        HashMap<String, Double> minDistance = new HashMap<>();
        HashMap<String, String> prevCity = new HashMap<>();

        for (String city : graph.cityRoutes.keySet()) {
            minDistance.put(city, Double.MAX_VALUE);
            prevCity.put(city, null);
        }

        minDistance.put(sourceCity, 0.0);

        PriorityQueue<CityNode> pq = new PriorityQueue<>(
            Comparator.comparingDouble(node -> node.totalCost)
        );

        pq.add(new CityNode(sourceCity, 0.0, null));

        ArrayList<String> visited = new ArrayList<>();

        while (!pq.isEmpty()) {
            CityNode current = pq.poll();

            if (visited.contains(current.cityName)) {
                continue;
            }

            visited.add(current.cityName);

            if (current.cityName.equals(destCity)) {
                break;
            }

            ArrayList<Route> neighbors = graph.getRoutesFromCity(current.cityName);

            for (Route r : neighbors) {
                String neighbor = r.getToCity();
                double newDist = minDistance.get(current.cityName) + r.getDistance();

                if (newDist < minDistance.get(neighbor)) {
                    minDistance.put(neighbor, newDist);
                    prevCity.put(neighbor, current.cityName);
                    pq.add(new CityNode(neighbor, newDist, current.cityName));
                }
            }
        }

        if (minDistance.get(destCity) == Double.MAX_VALUE) {
            System.out.println("No path found from " + sourceCity + " to " + destCity);
            return null;
        }

        ArrayList<String> path = new ArrayList<>();
        String curr = destCity;

        while (curr != null) {
            path.add(0, curr);
            curr = prevCity.get(curr);
        }

        System.out.println("\n--- Shortest Path Found ---");
        System.out.print("Path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) System.out.print(" --> ");
        }
        System.out.println();
        System.out.println("Total Distance: " + minDistance.get(destCity) + " km");

        return path;
    }
}
