// BFSSearch.java
// This class does Breadth First Search to find if a path exists between two cities
// BFS uses a Queue data structure - we process cities level by level
// Good for finding paths that have minimum number of stops

import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;

public class BFSSearch {

    Graph graph;

    public BFSSearch(Graph graph) {
        this.graph = graph;
    }

    // checks if there is any path between two cities
    // also prints all the cities we can reach from source
    public boolean isPathExists(String sourceCity, String destCity) {

        if (!graph.cityExists(sourceCity) || !graph.cityExists(destCity)) {
            System.out.println("City not found in the system.");
            return false;
        }

        // queue for BFS processing
        Queue<String> queue = new LinkedList<>();

        // visited list to avoid revisiting
        ArrayList<String> visited = new ArrayList<>();

        // previous map to rebuild path
        HashMap<String, String> prevCity = new HashMap<>();

        // start from source
        queue.add(sourceCity);
        visited.add(sourceCity);
        prevCity.put(sourceCity, null);

        while (!queue.isEmpty()) {
            String currentCity = queue.poll(); // remove front of queue

            // reached destination
            if (currentCity.equals(destCity)) {
                System.out.println("\n--- BFS Path Search Result ---");
                System.out.println("Path exists from " + sourceCity + " to " + destCity);

                // rebuild and print path
                ArrayList<String> path = new ArrayList<>();
                String temp = destCity;
                while (temp != null) {
                    path.add(0, temp);
                    temp = prevCity.get(temp);
                }

                System.out.print("Route: ");
                for (int i = 0; i < path.size(); i++) {
                    System.out.print(path.get(i));
                    if (i < path.size() - 1) System.out.print(" --> ");
                }
                System.out.println();
                System.out.println("Number of stops: " + (path.size() - 2)); // excluding source and dest
                return true;
            }

            // add all unvisited neighbors to queue
            ArrayList<Route> neighbors = graph.getRoutesFromCity(currentCity);
            for (Route r : neighbors) {
                String neighbor = r.getToCity();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    prevCity.put(neighbor, currentCity);
                }
            }
        }

        System.out.println("No path found from " + sourceCity + " to " + destCity);
        return false;
    }

    // get all cities reachable from a given source
    public ArrayList<String> getAllReachableCities(String sourceCity) {
        ArrayList<String> reachable = new ArrayList<>();

        if (!graph.cityExists(sourceCity)) {
            System.out.println("City not found: " + sourceCity);
            return reachable;
        }

        Queue<String> queue = new LinkedList<>();
        ArrayList<String> visited = new ArrayList<>();

        queue.add(sourceCity);
        visited.add(sourceCity);

        while (!queue.isEmpty()) {
            String curr = queue.poll();

            // dont add source itself to reachable list
            if (!curr.equals(sourceCity)) {
                reachable.add(curr);
            }

            ArrayList<Route> neighbors = graph.getRoutesFromCity(curr);
            for (Route r : neighbors) {
                String neighbor = r.getToCity();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        System.out.println("\n--- Cities Reachable from " + sourceCity + " ---");
        if (reachable.isEmpty()) {
            System.out.println("No cities reachable (city is isolated).");
        } else {
            for (int i = 0; i < reachable.size(); i++) {
                System.out.println((i + 1) + ". " + reachable.get(i));
            }
        }

        return reachable;
    }
}
