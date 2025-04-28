package org.example;

import java.util.List;

public class SearchEngine {
    private final PeopleDatabase peopleDatabase;

    public SearchEngine(PeopleDatabase peopleDatabase) {
        this.peopleDatabase = peopleDatabase;
    }

    public void performSearch(String query, SearchStrategy strategy) {
        String[] queryWords = query.toLowerCase().split("\\s+");
        List<String> results = strategy.search(peopleDatabase.getPeople(), peopleDatabase.getInvertedIndex(), queryWords);

        if (results.isEmpty()) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(results.size() + " persons found:");
            for (String person : results) {
                System.out.println(person);
            }
        }
    }
}

