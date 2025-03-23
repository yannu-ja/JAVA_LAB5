package org.example.commands;

import org.example.utils.CollectionManager;
import java.util.Scanner;

public class Clear extends AbstrantCommands {
    public Clear(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String arg) {
        Scanner scanner = new Scanner(System.in);  //  Initialize Scanner here ONLY for this command

        System.out.print("Are you sure you want to clear all routes? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        // continously Keep asking until the user enters a valid response
        while (!confirmation.equals("yes") && !confirmation.equals("no")) {
            System.out.print("Invalid input. Please enter 'yes' or 'no': ");
            confirmation = scanner.nextLine().trim().toLowerCase();
        }

        if (confirmation.equals("no")) {
            System.out.println("Operation cancelled.");
        } else {
            collectionManager.clearRoutes();
            System.out.println("All routes have been cleared.");
        }
    }
}
