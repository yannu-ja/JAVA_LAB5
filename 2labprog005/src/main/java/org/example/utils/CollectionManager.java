package org.example.utils;

import org.example.collection.Coordinates;
import org.example.collection.LocationFrom;
import org.example.collection.LocationTo;
import org.example.collection.Route;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages a collection of Route objects.
 * Stores and retrieves data from a CSV file.
 /
 */
public class CollectionManager {
    // The set that stores all routes.
    private final HashSet<Route> routeSet = new HashSet<>();
    private final String CSV_FILE ; // = "routes.csv";
    private final LocalDateTime initializationDate;
    private CommandManager commandManager;

    /**
     * Initializes the collection manager and loads data from the storage file.
     * Users can witch between:  -Hardcoded CSV file path
     *  Environment variable file path
     */
    public CollectionManager(CommandManager commandManager) {

        this.initializationDate = LocalDateTime.now();  // Store the initialization time
        this.commandManager = commandManager;


        /*Option 1: Uses Hardcoded File Path
         * Uncomment this line and omment out the Environment Variable option if you want to use a static file path.
         */

        //this.CSV_FILE = "routes.csv";  // Static file path
       // if (CSV_FILE == null || CSV_FILE.isEmpty())

          //  System.out.println("Error: Environment variable 'ROUTE_FILE' is not set or empty.");
//      System.exit(1); // Stop execution if the filename is not provided




        /* or you uae Option 2: Use Environment Variable-Based File Path**
         * Uncomment this and comment out the hardcoded path above if I want use the environment variable path .
         */
        String filePath = System.getenv("ROUTE_FILE");
        if (filePath == null || filePath.isEmpty()) {
            System.out.println(" Error: Environment variable 'ROUTE_FILE' is not set or empty.");
            System.exit(1); // Stop execution if the file path is missing
        }
        this.CSV_FILE = filePath; // Dynamic file path



              loadFromCSV();




    }



    public HashSet<Route> getRouteSet() {
        return routeSet;

    }
    /**
     * Assigns a new command manager for script execution.
     *  commandManager The command manager instance.
     */
    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }


    /**
     * Retrieves a route by its unique ID.
     *  id The ID of the route to retrieve.
     *  The Route object if found, otherwise null.
     */
    public Route getRouteById(long id) {
        for (Route route : routeSet) {
            if (route.getId() == id) {
                return route;
            }
        }
        return null;
    }

    /**
     * Updates an existing route by ID.
     * Returns `true` if the update is successful, `false` if the ID does not exist.
     */
    public boolean updateRouteById(long id, Route updatedRoute) {
        Route existingRoute = getRouteById(id);
        if (existingRoute == null) {
            return false; // ID not found
        }

        // Removes  old entry and add the updated one
        routeSet.remove(existingRoute);
        routeSet.add(updatedRoute);
        return true;
    }
/*
        * Adds a new route to the collection and saves changes to the CSV file.
     * route The new {@code Route} object to be added.
            */

    public void addRoute(Route route) {
        boolean added = routeSet.add(route);
        if (added) {
            System.out.println("Route added successfully.");
        } else {
            System.out.println("Route with the same ID already exists.");
        }
        System.out.println("Current Routes in CollectionManager -> " + routeSet.size());

        // Immediately saves after I use the add command
        saveToCSV();
    }


    //Removes a route by its ID.
    public void removeRouteById(long id) {
        Route toRemove = null;
        for (Route route : routeSet) {
            if (route.getId() == id) {
                toRemove = route;
                break;
            }
        }
        if (toRemove != null) {
            routeSet.remove(toRemove);
            System.out.println("Route removed successfully.");
        } else {
            System.out.println("Route with ID " + id + " not found.");
        }
    }
    /**
     * Clears the entire collection.
     * Saves changes to the CSV file after clearing.
     */
    public void clearRoutes() {
        routeSet.clear();
        saveToCSV();
        System.out.println("ALL routes have been cleared.");
    }


    public void showRoutes() {
        if (routeSet.isEmpty()) {
            System.out.println("No routes available.");
        } else {
            routeSet.forEach(System.out::println);
        }
    }

    /**
     * Displays information about the collection, including type, initialization date, and number of elements.
     */
    public void displayCollectionInfo() {
        System.out.println("Collection Type: " + routeSet.getClass().getSimpleName());
        System.out.println("Initialization Date: " + initializationDate);
        System.out.println("Number of Elements: " + routeSet.size());
    }

    /**
     * Reads and executes commands from a script file.
     * The script contains commands in the same form as user input.
     *
     *  fileName The script file containing commands.
     */
    public void executeScript(String fileName) {
        File scriptFile = new File(fileName);

        if (!scriptFile.exists() || !scriptFile.isFile()) {
            System.out.println("Error: Script file not found -> " + fileName);
            return;
        }

        try (Scanner fileScanner = new Scanner(scriptFile)) {
            System.out.println("Executing script: " + fileName);

            while (fileScanner.hasNextLine()) {
                String command = fileScanner.nextLine().trim();

                if (!command.isEmpty()) {
                    System.out.println("> " + command);
                    commandManager.executeCommand(command);
                }
            }

            System.out.println("Script execution completed.");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading script file: " + e.getMessage());
        }
    }

    /*Adds a new Route to the collection only if its distance is the smallest.
     * If the collection is empty, the route is automatically added.
     * / newRoute The route to be checked and possibly added.
     *  `true` if the route was added, `false` if it was not the minimum.
     */
    public boolean addIfMin(Route newRoute) {
        if (routeSet.isEmpty()) {
            // If no elements exist, add it directly
            routeSet.add(newRoute);
            saveToCSV();
            return true;
        }

        // Find the current minimum distance in the collection
        int minDistance = routeSet.stream()
                .mapToInt(Route::getDistance)
                .min()
                .orElse(Integer.MAX_VALUE); // Default to max if no valid entries

        // Check if the new route has a smaller distance than the current minimum
        if (newRoute.getDistance() < minDistance) {
            routeSet.add(newRoute);
            saveToCSV();
            return true;
        }

        return false;
    }
    public boolean removeGreater(int referenceDistance) {
        Iterator<Route> iterator = routeSet.iterator();
        boolean removed = false;

        while (iterator.hasNext()) {
            Route route = iterator.next();
            if (route.getDistance() > referenceDistance) {
                iterator.remove();
                removed = true;
            }
        }


        saveToCSV();
        return removed;

    }



    public boolean removeLower(int referenceDistance) {
        Iterator<Route> iterator = routeSet.iterator();
        boolean removed = false;

        while (iterator.hasNext()) {
            Route route = iterator.next();
            if (route.getDistance() < referenceDistance) {
                iterator.remove();
                removed = true;
            }
        }


        saveToCSV();
        return removed;
    }


    public int getTotalDistance() {
        return routeSet.stream().mapToInt(Route::getDistance).sum();
    }

    public int countRoutesByDistance(int distance) {
        return (int) routeSet.stream().filter(route -> route.getDistance() == distance).count();
    }



    public List<Integer> getDistancesAscending() {
        return routeSet.stream()
                .map(route -> route.getDistance())  // Extract distance values
                .sorted()                           // Sort in ascending order
                .collect(Collectors.toList());      // Converts it  to list
    }




    public void saveToCSV() {
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(CSV_FILE), StandardCharsets.UTF_8)) {
            writer.write("id,name,x,y,creationDate,from_x,from_y,from_z,from_name,to_x,to_y,to_z,distance\n");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (Route route : routeSet) {
                writer.write(route.getId() + "," +
                        route.getName() + "," +
                        route.getCoordinates().getX() + "," +
                        route.getCoordinates().getY() + "," +
                        route.getCreationDate().format(formatter) + "," +
                        route.getFrom().getX() + "," +
                        route.getFrom().getY() + "," +
                        route.getFrom().getZ() + "," +
                        route.getFrom().getName() + "," +
                        route.getTo().getX() + "," +
                        route.getTo().getY() + "," +
                        route.getTo().getZ() + "," +
                        route.getDistance() + "\n");
            }

            System.out.println("Routes saved to CSV file.");
        } catch (IOException e) {
            System.out.println("Error saving to CSV: " + e.getMessage());
        }
    }



    public void loadFromCSV() {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            System.out.println("CSV file not found. A new collection will be created.");
            return;
        }

        try (Scanner scanner = new Scanner(new FileInputStream(CSV_FILE), StandardCharsets.UTF_8)) {
            if (scanner.hasNextLine()) scanner.nextLine(); // Skip header line

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); // Use "," as the delimiter


                if (parts.length != 13) { // Adjusted to match new fields
                    System.out.println("Invalid line format: " + line);
                    continue;
                }

                try {
                    long id = Long.parseLong(parts[0]);
                    String name = parts[1];
                    int x = Integer.parseInt(parts[2]);
                    int y = Integer.parseInt(parts[3]);
                    LocalDateTime creationDate = LocalDateTime.parse(parts[4], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    Float fromX = Float.parseFloat(parts[5]);
                    Long fromY = Long.parseLong(parts[6]);
                    float fromZ = Float.parseFloat(parts[7]);
                    String fromName = parts[8];
                    float toX = Float.parseFloat(parts[9]);
                    Double toY = Double.parseDouble(parts[10]);
                    Long toZ = Long.parseLong(parts[11]);
                    int distance = Integer.parseInt(parts[12]);

                    Route route = new Route(
                            id,
                            name,
                            new Coordinates(x, y),
                            creationDate,
                            new LocationFrom(fromX, fromY, fromZ, fromName),
                            new LocationTo(toX, toY, toZ),
                            distance
                    );
                    if (!routeSet.contains(route)) {
                        routeSet.add(route);
                    }

                }
                catch (NumberFormatException e) {
                    System.out.println("Error parsing number: " + e.getMessage());
                }
            }
            System.out.println("Routes successfully loaded from CSV -> " + routeSet.size());
        }
        catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }
    }




}

