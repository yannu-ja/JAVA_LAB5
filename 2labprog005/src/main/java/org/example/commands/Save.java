package org.example.commands;

import org.example.utils.CollectionManager;

public class Save extends AbstrantCommands {
    public Save(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String arg) {
        collectionManager.saveToCSV();
    }
}
