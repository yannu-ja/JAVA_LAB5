package org.example.commands;

import org.example.utils.CollectionManager;

public class Help extends AbstrantCommands {
    public Help(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String arg) {
        String manual = """
                help: Show available commands
                info: Display collection info
                show: Display all stored routes
                add: Add a new route to the collection
                update id: Update an existing route
                remove_by_id id: Remove a route by its ID
                clear: Clear the collection
                save: Save collection to CSV
                execute_script file_name: Run commands from a script file
                exit: Exit the program
                """;
        System.out.println(manual);
    }
}
