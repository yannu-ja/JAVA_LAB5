package org.example.utils;

import java.util.Scanner;

/**
 * Manages interactive user input and executes commands via the CommandManager .
 * This class provides a command-line interface for interacting with the application.
 */
public class ConsoleManager {
    //The command manager that processes user commands.
    private final CommandManager commandManager;
    /**
     * Constructs a ConsoleManager with a specified  CommandManager.
     * Initializes a  for reading user input.
     *  CommandManager The command manager responsible for executing user commands.
     */
    private final Scanner scanner;

    public ConsoleManager(CommandManager commandManager) {
        this.commandManager = commandManager;
        this.scanner = new Scanner(System.in); // Assign scanner from constructor
    }
    /**
     * Retrieves the  Scanner instance used for user input.
     *return The scanner instance.
     */
    public Scanner getScanner() {  // Add a getter for Scanner
        return scanner;
    }

    /**
     * Starts the interactive mode for user command execution.
     * The method continuously reads input from the user until the "exit" command is entered.
     */

    public void start() {
        System.out.println("WELCOME TO THE ROUTE COLLECTION APP");
        System.out.println("Enter help for a manual, or exit to stop the program.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program...");
                System.exit(5);
                break;
            }

            commandManager.executeCommand(input);
        }
    }
}
