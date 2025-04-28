package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SearchStrategy {
    public final List<String> search(String[] people, Map<String, Set<Integer>> invertedIndex, String[] queryWords) {
        Set<Integer> resultIndexes = match(people, invertedIndex, queryWords);
        List<String> results = new ArrayList<>();
        for (int i : resultIndexes) {
            results.add(people[i]);
        }
        return results;
    }

    public abstract Set<Integer> match(String[] people, Map<String, Set<Integer>> invertedIndex, String[] queryWords);
}



