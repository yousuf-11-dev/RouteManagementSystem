// DataLoader.java
// This class loads some sample data into the system so we can test it
// These are Pakistani cities with realistic route info


public class DataLoader {

    // this method adds sample cities and routes to the graph
    public static void loadSampleData(Graph graph) {
        System.out.println("Loading sample data...");

        // --- add cities ---
        graph.addCity("Karachi");
        graph.addCity("Lahore");
        graph.addCity("Islamabad");
        graph.addCity("Peshawar");
        graph.addCity("Quetta");
        graph.addCity("Multan");
        graph.addCity("Faisalabad");
        graph.addCity("Rawalpindi");

        // --- add railway routes ---
        // Karachi to Lahore
        Route r1 = new Route("Karachi", "Lahore", 1210, 3500, "railway", "Express-1", 1020);
        graph.addRoute(r1);

        // Lahore to Islamabad
        Route r2 = new Route("Lahore", "Islamabad", 375, 1200, "railway", "Express-2", 240);
        graph.addRoute(r2);

        // Islamabad to Peshawar
        Route r3 = new Route("Islamabad", "Peshawar", 175, 800, "railway", "Express-3", 120);
        graph.addRoute(r3);

        // Multan to Lahore
        Route r4 = new Route("Multan", "Lahore", 340, 1000, "railway", "Express-4", 210);
        graph.addRoute(r4);

        // Faisalabad to Lahore
        Route r5 = new Route("Faisalabad", "Lahore", 128, 600, "railway", "Express-5", 90);
        graph.addRoute(r5);

        // Rawalpindi to Islamabad (short route)
        Route r6 = new Route("Rawalpindi", "Islamabad", 15, 150, "railway", "Local-1", 30);
        graph.addRoute(r6);

        // Karachi to Multan
        Route r7 = new Route("Karachi", "Multan", 870, 2800, "railway", "Express-6", 680);
        graph.addRoute(r7);

        // --- add flight routes ---
        // Karachi to Islamabad
        Route f1 = new Route("Karachi", "Islamabad", 1400, 15000, "flight", "PK-301", 110);
        graph.addRoute(f1);

        // Karachi to Lahore
        Route f2 = new Route("Karachi", "Lahore", 1210, 12000, "flight", "PK-101", 90);
        graph.addRoute(f2);

        // Lahore to Peshawar
        Route f3 = new Route("Lahore", "Peshawar", 525, 9000, "flight", "PK-201", 60);
        graph.addRoute(f3);

        // Karachi to Quetta
        Route f4 = new Route("Karachi", "Quetta", 700, 10000, "flight", "PK-401", 75);
        graph.addRoute(f4);

        // Islamabad to Quetta
        Route f5 = new Route("Islamabad", "Quetta", 1100, 13000, "flight", "PK-501", 100);
        graph.addRoute(f5);

        System.out.println("\nSample data loaded successfully!");
        System.out.println("Cities added: 8 | Routes added: 12\n");
    }
}
