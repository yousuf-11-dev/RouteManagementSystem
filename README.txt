====================================================
  Dynamic Railway & Flight Route Management System
  Data Structures Project
====================================================

PROJECT OVERVIEW
----------------
This is a console-based Java project for managing railway and flight routes
between cities. It was made as part of the Data Structures course project.

The system lets you:
  - Add and remove cities and routes
  - Search if a path exists between two cities (uses BFS + Queue)
  - Find the shortest path by distance (uses Dijkstra + Priority Queue)
  - Find the cheapest path by cost (uses Dijkstra + Priority Queue)
  - View route statistics sorted by cost/distance (uses TreeMap)
  - Keep track of search history with undo option (uses Stack)


FILES IN THIS PROJECT
---------------------
src/
  Route.java          - Represents a single route between two cities
  Graph.java          - Stores entire network using HashMap + ArrayList
  DijkstraSearch.java - Finds shortest and cheapest paths (Priority Queue)
  BFSSearch.java      - Breadth first search using Queue
  SearchHistory.java  - Stores search history using Stack
  RouteStatistics.java- Shows sorted stats using TreeMap
  DataLoader.java     - Loads sample Pakistani cities and routes
  Main.java           - Main file with menu system (run this)


HOW TO COMPILE AND RUN
-----------------------
Step 1: Make sure Java is installed on your computer.
        To check, open terminal/cmd and type:
        java -version

Step 2: Navigate to the src folder in terminal:
        cd path/to/RouteManagementSystem/src

Step 3: Compile all Java files:
        javac *.java

Step 4: Run the program:
        java Main

That's it! The program will start and show a menu.


DATA STRUCTURES USED
---------------------
1. HashMap      - Stores city-to-routes mapping (fast lookup)
2. ArrayList    - Stores list of routes for each city
3. Priority Queue - Used in Dijkstra algorithm for path optimization
4. Queue        - Used in BFS for route searching
5. Stack        - Stores search history with undo feature
6. TreeMap      - Displays routes sorted by cost or distance


SAMPLE DATA
-----------
When you run the program it automatically loads these Pakistani cities:
  - Karachi, Lahore, Islamabad, Peshawar
  - Quetta, Multan, Faisalabad, Rawalpindi

And these routes (mix of railway and flights):
  Railway: Express-1 to Express-6 and Local-1
  Flights:  PK-101, PK-201, PK-301, PK-401, PK-501

You can add more cities and routes from the Admin Panel in the menu.


NOTES
------
- Routes are bidirectional (if A to B exists, B to A also works)
- Reverse routes are marked with "-R" at the end of route name
- Route names must be unique for the remove feature to work


MADE BY
--------
Dynamic Railway & Flight Route Management System
Data Structures Course Project
