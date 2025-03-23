package org.example; // Add this line at the top

import org.example.utils.CollectionManager;
import org.example.utils.CommandManager;
import org.example.utils.ConsoleManager;
import java.util.Scanner; // Import Scanner

public class Main {
    public static void main(String[] args) {
        //temporary created commandmanager without collection manager

        CollectionManager collectionManager = new CollectionManager(null);
        // Now, create CommandManager and pass the CollectionManager
        CommandManager commandManager = new CommandManager(collectionManager );
        Scanner scanner = new Scanner(System.in); // Create Scanner object
        collectionManager.setCommandManager(commandManager);  //command manager set

        // Create ConsoleManager and start interactive mode

        ConsoleManager consoleManager = new ConsoleManager(commandManager); // Pass scanner

        consoleManager.start(); // Start interactive mode
    }
}
