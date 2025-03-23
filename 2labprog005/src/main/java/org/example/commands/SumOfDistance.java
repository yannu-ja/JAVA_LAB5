package org.example.commands;

import org.example.utils.CollectionManager;

public class SumOfDistance extends AbstrantCommands {
    public SumOfDistance(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String arg) {
        System.out.println(" Executing command -> sum_of_distance with argument -> " + arg);

        int totalDistance = collectionManager.getTotalDistance();

        System.out.println(" The total sum of distances in the collection: " + totalDistance);
    }
}
