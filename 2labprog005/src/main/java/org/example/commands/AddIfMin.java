package org.example.commands;

import org.example.collection.Coordinates;
import org.example.collection.LocationFrom;
import org.example.collection.LocationTo;
import org.example.collection.Route;
import org.example.utils.CollectionManager;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AddIfMin extends AbstrantCommands {
    public AddIfMin(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String arg) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("DEBUG: Executing command -> add_if_min with argument -> " + arg);

        // Route Name Validation
        System.out.print("Enter route name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println(" Route name cannot be empty. Please enter again.");
            System.out.print("Enter route name: ");
            name = scanner.nextLine().trim();
        }

        // X Coordinate
        int x = getValidInteger(scanner, "Enter x coordinate: ");

        // Y Coordinate
        int y = getValidInteger(scanner, "Enter y coordinate: ");

        // Distance Validation (Must be greater than 1)
        int distance;
        while (true) {
            System.out.print("Enter distance: ");
            String input = scanner.nextLine().trim();try
            {
                distance = Integer.parseInt(input);
                if (distance > 1) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println(" Distance must be greater than 1. Enter distance again:");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid integer is allowed.");
            }
        }

        // LocationFrom Inputs
        float fromX = getValidFloat(scanner, "Enter from location x: ");
        long fromY = getValidLong(scanner, "Enter from location y: ");
        float fromZ = getValidFloat(scanner, "Enter from location z: ");

        System.out.print("Enter from location name: ");
        String fromName = scanner.nextLine().trim();
        while (fromName.isEmpty()) {
            System.out.println(" From location name cannot be empty. Please enter again.");
            System.out.print("Enter from location name: ");
            fromName = scanner.nextLine().trim();
        }

        // LocationTo Inputs
        float toX = getValidFloat(scanner, "Enter to location x: ");
        double toY = getValidDouble(scanner, "Enter to location y: ");
        long toZ = getValidLong(scanner, "Enter to location z: ");

        // Create the new Route with validated inputs
        Route newRoute = new Route(
                System.currentTimeMillis(), // Generates a new unique ID
                name,
                new Coordinates(x, y),
                LocalDateTime.now(),
                new LocationFrom(fromX, fromY, fromZ, fromName),
                new LocationTo(toX, toY, toZ),
                distance
        );

        // Check and add only if it's the minimum
        boolean added = collectionManager.addIfMin(newRoute);

        if (added) {
            System.out.println("Collection added successfully.");
        } else {
            System.out.println("Collection was NOT added because the entered distance was not the minimum.");
        }
    }

    // Helper method to get a valid integer
    private int getValidInteger(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid integer is allowed.");
            }
        }
    }

    // Helper method to get a valid float
    private float getValidFloat(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();try {
                return Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid decimal number is allowed.");
            }
        }
    }

    // Helper method to get a valid long
    private long getValidLong(Scanner scanner, String message) {
        while (true) {
             System.out.print(message);
             String input = scanner.nextLine().trim();try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid long integer is allowed.");
            }
        }
    }

    // Helper method to get a valid double



    private double getValidDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Only a valid decimal number is allowed.");
            }
        }
    }
}
