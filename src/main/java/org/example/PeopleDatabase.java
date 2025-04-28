package org.example;

import java.util.*;

public class PeopleDatabase {
    private final String[] people;
    private final Map<String, Set<Integer>> invertedIndex;

    public PeopleDatabase(String[] people) {
        this.people = people;
        this.invertedIndex = new HashMap<>();
        invertedIndexBuild();
    }

    private void invertedIndexBuild() {
        for (int i = 0; i < people.length; i++) {
            String[] words = people[i].toLowerCase().split("\\s+");
            for (String word : words) {
                invertedIndex.computeIfAbsent(word, k -> new HashSet<>()).add(i);
            }
        }
    }

    public Map<String, Set<Integer>> getInvertedIndex() {
        return invertedIndex;
    }

    public String[] getPeople() {
        return people;
    }

    public void printAll() {
        System.out.println("=== List of people ===");
        for (String person : people) {
            System.out.println(person);
        }
        System.out.println();
    }
}



