package org.example;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NoneStrategy extends SearchStrategy {
    @Override
    public Set<Integer> match(String[] people, Map<String, Set<Integer>> invertedIndex, String[] queryWords) {
        Set<Integer> allIndexes = new HashSet<>();
        for (int i = 0; i < people.length; i++) {
            allIndexes.add(i);
        }

        for (String word : queryWords) {
            Set<Integer> indexes = invertedIndex.get(word);
            if (indexes != null) {
                allIndexes.removeAll(indexes);
            }
        }
        return allIndexes;
    }
}

