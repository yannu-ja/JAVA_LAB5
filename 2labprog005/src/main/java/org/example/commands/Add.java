package org.example.commands;

import org.example.collection.Coordinates;
import org.example.collection.LocationFrom;
import org.example.collection.LocationTo;
import org.example.collection.Route;
import org.example.utils.CollectionManager;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Add extends AbstrantCommands {

    public Add(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String arg) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Executing command -> add with argument -> " + arg);

        // Route Name (Cannot be empty)
        System.out.print("Enter route name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println(" Route name cannot be empty. Please enter again.");
            System.out.print("Enter route name: ");
            name = scanner.nextLine().trim();
        }

        // X Coordinate (Must be > 0)
        int x = getValidInteger(scanner, "Enter X coordinate (must be an integer > 0): ", true);

        // Y Coordinate (Must be > 0)
        int y = getValidInteger(scanner, "Enter Y coordinate (must be an integer > 0): ", true);

        // Distance (Must be > 1)
        int distance;
        while (true) {
            System.out.print("Enter distance (must be greater than 1): ");
            try {
                distance = Integer.parseInt(scanner.nextLine().trim());
                if (distance <= 1) {
                    System.out.println(" Error: Distance must be greater than 1. Please enter again.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid integer is allowed.");
            }
        }

        // LocationFrom
        float fromX = getValidFloat(scanner, "Enter 'From' location X (cannot be 0): ", true);
        long fromY = getValidLong(scanner, "Enter 'From' location Y (cannot be 0): ", true);
        float fromZ = getValidFloat(scanner, "Enter 'From' location Z: ", false); // No constraint

        System.out.print("Enter 'From' location name: ");
        String fromName = scanner.nextLine().trim();
        while (fromName.isEmpty()) {
            System.out.println( " From location name cannot be empty. Please enter again.");
            System.out.print("Enter 'From' location name: ");
            fromName = scanner.nextLine().trim();
        }

        // LocationTo
        float toX = getValidFloat(scanner, "Enter 'To' location X: ", false);
        double toY = getValidDouble(scanner, "Enter 'To' location Y (cannot be 0): ", true);
        long toZ = getValidLong(scanner, "Enter 'To' location Z (cannot be 0): ", true);

        // Create Route
        Route newRoute = new Route(
                System.currentTimeMillis(),
                name,
                new Coordinates(x, y),
                LocalDateTime.now(),
                new LocationFrom(fromX, fromY, fromZ, fromName),
                new LocationTo(toX, toY, toZ),
                distance
        );

        collectionManager.addRoute(newRoute);
        System.out.println(" Route successfully added! ");
    }

    /**
     * Ensures user enters a valid integer input.
     * @param mustBePositive If true, input cannot be 0 or negative.
     */
    private int getValidInteger(Scanner scanner, String message, boolean mustBePositive) {
        while (true) {
            System.out.print(message);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (mustBePositive && value <= 0) {
                    throw new IllegalArgumentException(" Error: Value must be greater than 0.");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid integer is allowed.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Ensures user enters a valid float input.
     * @param mustBePositive If true, input cannot be 0.
     */
    private float getValidFloat(Scanner scanner, String message, boolean mustBePositive) {
        while (true) {
            System.out.print(message);
            try {
                float value = Float.parseFloat(scanner.nextLine().trim());
                if (mustBePositive && value == 0) {
                    throw new IllegalArgumentException(" Error: Value cannot be zero.");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid float is allowed.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Ensures user enters a valid long input.
     *  mustBePositive If true, input cannot be 0.
     */
    private long getValidLong(Scanner scanner, String message, boolean mustBePositive) {
        while (true) {
            System.out.print(message);
            try {
                long value = Long.parseLong(scanner.nextLine().trim());
                if (mustBePositive && value == 0) {
                    throw new IllegalArgumentException(" Error: Value cannot be zero.");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid long number is allowed.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Ensures user enters a valid double input.
     * @param mustBePositive If true, input cannot be 0.
     */
    private double getValidDouble(Scanner scanner, String message, boolean mustBePositive) {
        while (true) {
            System.out.print(message);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (mustBePositive && value == 0) {
                    throw new IllegalArgumentException(" Error: Value cannot be zero.");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid double is allowed.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
