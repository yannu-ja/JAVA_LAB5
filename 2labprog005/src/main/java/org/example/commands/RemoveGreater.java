package org.example.commands;

import org.example.collection.Route;
import org.example.utils.CollectionManager;

import java.util.Iterator;
import java.util.Scanner;

public class RemoveGreater extends AbstrantCommands {
    public RemoveGreater(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String arg) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("DEBUG: Executing command -> remove_greater with argument -> " + arg);

        // Validate Distance Input
        int distance;
        while (true) {
            System.out.print("Enter reference distance: ");
            String input = scanner.nextLine().trim();
            try {
                distance = Integer.parseInt(input);
                if (distance >= 1) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println("  Distance must be **greater than or equal to 1**. Enter distance again:");
                }
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Only a valid integer is allowed.");
            }
        }

        // Remove all routes with distance greater than the specified value
        boolean removed = collectionManager.removeGreater(distance);

        if (removed) {
            System.out.println(" Routes with distance greater than " + distance + " have been removed.");
        } else {
            System.out.println("  No routes were removed because none had a distance greater than " + distance + ".");}

    }
}
