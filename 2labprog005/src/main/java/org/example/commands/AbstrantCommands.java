package org.example.commands;

import org.example.utils.CollectionManager;

/**
        The  AbstrantCommands class serves as an abstract base class
 * for all command implementations in the application.
 * Each specific command should extend this class and implement the
 * (String) method to perform its designated operation.

 * This class ensures that every command has access to the
 * { CollectionManager} for managing the stored collection.
        */
public abstract class AbstrantCommands {
    protected final CollectionManager collectionManager;

    /**
     * Constructs a new command and associates it with a collection manager.
     *  collectionManager The collection manager instance to interact with the collection.
     */
    public AbstrantCommands(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Each command must implement this method.
     */
    public abstract void execute(String arg); // FIXED: No `{}` and removed @Override
}
