package org.example.utils;

import org.example.commands.*;

import java.util.HashMap;
import java.util.Map;

/**
 * The  CommandManager class is responsible for managing and executing
 * various commands in the application.
 * It registers all available commands and allows users to execute them
 * dynamically based on input.
 */
public class CommandManager {
    private final CollectionManager collectionManager;
    /** A map storing command names as keys and their corresponding command objects as values. */

    private final Map<String, AbstrantCommands> commands = new HashMap<>();

    public CommandManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        registerCommands();
    }

    /**
     * Registers all available commands in the system.
     * Commands are stored in a map, allowing for efficient lookup and execution.
     */
    private void registerCommands() {
        commands.put("help", new Help(collectionManager));
        commands.put("show", new Show(collectionManager));
        commands.put("Add", new Add(collectionManager));
        commands.put("update_by_id", new UpdateById(collectionManager));
        commands.put("remove_by_id", new RemoveById(collectionManager));
        commands.put("save", new Save(collectionManager));
        commands.put("exit", new Exit(collectionManager));
        commands.put("clear", new Clear(collectionManager));
        commands.put("info", new Info(collectionManager));
        commands.put("execute_script", new ExecuteScript(collectionManager));
        commands.put("add_if_min", new AddIfMin(collectionManager));
        commands.put("remove_greater", new RemoveGreater(collectionManager));
        commands.put("sum_of_distance", new SumOfDistance(collectionManager));
        commands.put("print_field_ascending_distance", new PrintFieldAscendingDistance(collectionManager));


        commands.put("count_by_distance", new CountByDistance(collectionManager));

        commands.put("remove_lower", new RemoveLower(collectionManager));



    }

    /**
     * Executes the specified command based on user input.
     * The input is split into a command name and an optional argument.
     * The command is then retrieved from the registry and executed.
     * commandLine The full command input from the user.
     */

    public void executeCommand(String commandLine) {
        if (commandLine == null || commandLine.trim().isEmpty()) {
            return; // Ignore empty inputs
        }
        // Split command into name and argument

        String[] parts = commandLine.trim().split("\\s+", 2);
        String commandName = parts[0];
        String argument = (parts.length > 1) ? parts[1] : "";
        System.out.println(" Executing command -> " + commandName + " with argument -> " + argument);

        // Retrieve command from the registry
        AbstrantCommands command = commands.get(commandName);

        if (command != null) {
            command.execute(argument);
        } else {
            System.out.println("Error: Unknown command '" + commandName + "'. Type 'help' for a list of commands.");

            System.out.println("Error: Unknown command '" + commandName + "'. Type 'help' for a list of commands.");
        }
    }
}