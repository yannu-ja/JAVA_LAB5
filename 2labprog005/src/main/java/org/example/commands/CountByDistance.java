package org.example.commands;

import org.example.utils.CollectionManager;

import java.util.Scanner;

public class CountByDistance extends AbstrantCommands {
    public CountByDistance(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String arg) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Executing command -> count_by_distance with argument -> " + arg);

        // Get valid integer for distance
        int targetDistance = getValidInteger(scanner, "Enter distance to count: ");

        // Count occurrences of this distance
        int count = collectionManager.countRoutesByDistance(targetDistance);

        // Display the result
        System.out.println(" Number of routes with distance " + targetDistance + " is: " + count);
    }

    /**
     * Helper method to get a valid integer input
     */
    private int getValidInteger(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid integer is allowed.");
            }
        }
    }
}
