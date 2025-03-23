package org.example.commands;

import org.example.collection.Coordinates;
import org.example.collection.LocationFrom;
import org.example.collection.LocationTo;
import org.example.collection.Route;
import org.example.utils.CollectionManager;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UpdateById extends AbstrantCommands {
    private final Scanner scanner = new Scanner(System.in);

    public UpdateById(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String arg) {
        if (arg.isEmpty()) {
            System.out.println(" Error: No ID provided. Usage: update_by_id <id>");
            return;
        }

        long id;
        try {
            id = Long.parseLong(arg);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid numeric ID.");
            return;
        }

        Route existingRoute = collectionManager.getRouteById(id);
        if (existingRoute == null) {
            System.out.println("Error: No route found with ID: " + id);
            return;
        }

        System.out.println(" Updating Route with ID: " + id);

        // Route Name (Cannot be null)
        System.out.print("Enter new route name: ");
        String newName = scanner.nextLine().trim();
        while (newName.isEmpty()) {
            System.out.println(" Route name cannot be empty. Please enter again.");
            System.out.print("Enter new route name: ");
            newName = scanner.nextLine().trim();
        }

        // Coordinates (x, y) - Cannot be null
        Long x = getValidLong(scanner, "Enter new X coordinate (cannot be null): ");
        Integer y = getValidInteger(scanner, "Enter new Y coordinate (cannot be null): ");

        // Distance (Must be > 1)
        int distance;
        while (true) {
            System.out.print("Enter new distance (must be greater than 1): ");
            try {
                distance = Integer.parseInt(scanner.nextLine().trim());
                if (distance <= 1) throw new IllegalArgumentException(" Error: Distance must be greater than 1.");
                break;
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid integer is allowed.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // LocationFrom (Cannot be null)
        Float fromX = getValidFloat(scanner, "Enter new 'From' location X (cannot be null): ");
        Long fromY = getValidLong(scanner, "Enter new 'From' location Y (cannot be null): ");
        float fromZ = getValidFloat(scanner, "Enter new 'From' location Z: ");

        System.out.print("Enter new 'From' location name (cannot be null): ");
        String fromName = scanner.nextLine().trim();
        while (fromName.isEmpty()) {
            System.out.println(" 'From' location name cannot be empty. Please enter again.");
            System.out.print("Enter 'From' location name: ");
            fromName = scanner.nextLine().trim();
        }

        // LocationTo (Cannot be null)
        float toX = getValidFloat(scanner, "Enter new 'To' location X: ");
        Double toY = getValidDouble(scanner, "Enter new 'To' location Y (cannot be null): ");
        Long toZ = getValidLong(scanner, "Enter new 'To' location Z (cannot be null): ");

        // Create the updated route
        Route updatedRoute = new Route(
                id,
                newName,
                new Coordinates(x, y),
                LocalDateTime.now(),
                new LocationFrom(fromX, fromY, fromZ, fromName),
                new LocationTo(toX, toY, toZ),
                distance
        );

        boolean success = collectionManager.updateRouteById(id, updatedRoute);
        if (success) {
            System.out.println(" Route updated successfully!");
        } else {
            System.out.println(" Failed to update route. Route with ID " + id + " not found.");
        }
    }

    // The Helper methods for input validation

    /**
     * Ensures a valid integer input (cannot be null)
     */
    private int getValidInteger(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Only a valid integer is allowed.");
            }
        }
    }

    /**
     * Ensures a valid float input (cannot be null)
     */
    private float getValidFloat(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                return Float.parseFloat(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid float is allowed.");
            }
        }
    }


    private long getValidLong(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid long is allowed.");
            }
        }
    }

    /**
     * Ensures a valid double input (cannot be null)
     */
    private double getValidDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Only a valid double is allowed.");
            }
        }
    }
}
