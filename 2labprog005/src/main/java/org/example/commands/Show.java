package org.example.commands;

import org.example.collection.Route;
import org.example.utils.CollectionManager;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Show extends AbstrantCommands {
    public Show(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String arg) {
        List<Route> sortedRoutes = new ArrayList<>(collectionManager.getRouteSet());
        sortedRoutes.sort(Comparator.comparingLong(Route::getId)); // Sorting manually

        if (sortedRoutes.isEmpty()) {
            System.out.println("No routes to display.");
        } else {
            sortedRoutes.forEach(System.out::println); //
        }
    }
    }

