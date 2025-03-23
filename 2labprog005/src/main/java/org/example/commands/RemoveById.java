package org.example.commands;

import org.example.utils.CollectionManager;

public class RemoveById extends AbstrantCommands {
    public RemoveById(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String arg) {
        long id = Long.parseLong(arg);
        collectionManager.removeRouteById(id);
    }
}
