package org.example.commands;

import org.example.utils.CollectionManager;

public class Exit extends AbstrantCommands {
    public Exit(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override

      public void execute(String arg) {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
