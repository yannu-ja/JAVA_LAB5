package org.example.commands;

import org.example.utils.CollectionManager;
import java.util.List;
import java.util.stream.Collectors;

public class PrintFieldAscendingDistance extends AbstrantCommands {
    public PrintFieldAscendingDistance(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String arg) {
        System.out.println(" Executing command -> print_field_ascending_distance");

        // Get sorted distances
        List<Integer> sortedDistances = collectionManager.getDistancesAscending();

        if (sortedDistances.isEmpty()) {
            System.out.println(" No routes available in the collection.");
        } else {
            System.out.println("Distance values in ascending order:");
            sortedDistances.forEach(System.out::println);
        }
    }
}
