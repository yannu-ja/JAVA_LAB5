
package org.example.commands;

import org.example.commands.AbstrantCommands;


import org.example.utils.CollectionManager;

public class ExecuteScript extends AbstrantCommands {
    public ExecuteScript(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override


    public void execute(String arg) {
        if (arg == null || arg.trim().isEmpty()) {
            System.out.println("Error: Please provide a script file name.");
            return;
        }

        collectionManager.executeScript(arg.trim());
    }
}
