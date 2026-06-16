// Main.java
// This is the main entry point of the project
// It shows a console menu and lets the user interact with the system
// All features from the project report are implemented here

import java.util.Scanner;

public class Main {

    // all our main objects
    static Graph graph = new Graph();
    static DijkstraSearch dijkstra = new DijkstraSearch(graph);
    static BFSSearch bfs = new BFSSearch(graph);
    static SearchHistory history = new SearchHistory();
    static RouteStatistics stats = new RouteStatistics(graph);
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("  Dynamic Railway & Flight Route Management");
        System.out.println("           System - DS Project");
        System.out.println("============================================");

        // load some sample data so user can test right away
        DataLoader.loadSampleData(graph);

        // main loop
        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    searchMenu();
                    break;
                case 3:
                    shortestPathMenu();
                    break;
                case 4:
                    cheapestPathMenu();
                    break;
                case 5:
                    statisticsMenu();
                    break;
                case 6:
                    history.displayHistory();
                    break;
                case 7:
                    graph.displayAllCities();
                    break;
                case 8:
                    System.out.println("Thank you for using Route Management System. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        sc.close();
    }

    // main menu
    static void showMainMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Admin Panel (Add/Remove routes)");
        System.out.println("2. Search Route Between Cities");
        System.out.println("3. Find Shortest Path (by Distance)");
        System.out.println("4. Find Cheapest Path (by Cost)");
        System.out.println("5. Route Statistics");
        System.out.println("6. View Search History");
        System.out.println("7. View All Cities");
        System.out.println("8. Exit");
        System.out.println("================================");
    }

    // admin panel
    static void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Admin Panel ---");
            System.out.println("1. Add New City");
            System.out.println("2. Add New Route");
            System.out.println("3. Remove Route");
            System.out.println("4. Remove City");
            System.out.println("5. View Routes from a City");
            System.out.println("6. Back to Main Menu");

            int ch = getIntInput("Enter choice: ");

            switch (ch) {
                case 1:
                    System.out.print("Enter city name: ");
                    String cityName = sc.nextLine().trim();
                    graph.addCity(cityName);
                    break;

                case 2:
                    addNewRoute();
                    break;

                case 3:
                    System.out.print("Enter route name to remove (e.g. Express-1): ");
                    String routeName = sc.nextLine().trim();
                    graph.removeRoute(routeName);
                    break;

                case 4:
                    System.out.print("Enter city name to remove: ");
                    String removeCity = sc.nextLine().trim();
                    graph.removeCity(removeCity);
                    break;

                case 5:
                    System.out.print("Enter city name: ");
                    String viewCity = sc.nextLine().trim();
                    graph.displayRoutesFromCity(viewCity);
                    break;

                case 6:
                    back = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // helper to add a route via user input
    static void addNewRoute() {
        System.out.println("\n--- Add New Route ---");
        System.out.print("From city: ");
        String from = sc.nextLine().trim();

        System.out.print("To city: ");
        String to = sc.nextLine().trim();

        System.out.print("Route name (e.g. Express-10): ");
        String rName = sc.nextLine().trim();

        System.out.print("Route type (railway/flight): ");
        String rType = sc.nextLine().trim().toLowerCase();

        double dist = getDoubleInput("Distance (km): ");
        double cost = getDoubleInput("Cost: ");
        int time = getIntInput("Travel time (minutes): ");

        Route newRoute = new Route(from, to, dist, cost, rType, rName, time);
        graph.addRoute(newRoute);
    }

    // search menu using BFS
    static void searchMenu() {
        System.out.println("\n--- Search Route ---");
        System.out.print("Enter source city: ");
        String src = sc.nextLine().trim();

        System.out.print("Enter destination city: ");
        String dest = sc.nextLine().trim();

        history.addSearch(src, dest, "BFS");
        bfs.isPathExists(src, dest);
        bfs.getAllReachableCities(src);
    }

    // shortest path menu
    static void shortestPathMenu() {
        System.out.println("\n--- Find Shortest Path ---");
        System.out.print("Enter source city: ");
        String src = sc.nextLine().trim();

        System.out.print("Enter destination city: ");
        String dest = sc.nextLine().trim();

        history.addSearch(src, dest, "Shortest Path");
        dijkstra.findShortestPath(src, dest);
    }

    // cheapest path menu
    static void cheapestPathMenu() {
        System.out.println("\n--- Find Cheapest Path ---");
        System.out.print("Enter source city: ");
        String src = sc.nextLine().trim();

        System.out.print("Enter destination city: ");
        String dest = sc.nextLine().trim();

        history.addSearch(src, dest, "Cheapest Path");
        dijkstra.findCheapestPath(src, dest);
    }

    // statistics menu
    static void statisticsMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Route Statistics ---");
            System.out.println("1. General Stats");
            System.out.println("2. Routes Sorted by Cost");
            System.out.println("3. Routes Sorted by Distance");
            System.out.println("4. Most Expensive Route");
            System.out.println("5. Cheapest Route");
            System.out.println("6. Back");

            int ch = getIntInput("Enter choice: ");

            switch (ch) {
                case 1: stats.showGeneralStats(); break;
                case 2: stats.displayRoutesByCost(); break;
                case 3: stats.displayRoutesByDistance(); break;
                case 4: stats.findMostExpensiveRoute(); break;
                case 5: stats.findCheapestRoute(); break;
                case 6: back = true; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    // helper to safely read int input from user
    static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = Integer.parseInt(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    // helper to safely read double input from user
    static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double val = Double.parseDouble(sc.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
