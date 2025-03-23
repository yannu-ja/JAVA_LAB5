package org.example.commands;

import org.example.utils.CollectionManager;

/**
 * Displays information about the collection, including type, initialization date, and number of elements.
 */
public class Info extends AbstrantCommands {

       public Info(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String arg) {
        System.out.println("Executing command -> info");
        collectionManager.displayCollectionInfo();
    }
}
