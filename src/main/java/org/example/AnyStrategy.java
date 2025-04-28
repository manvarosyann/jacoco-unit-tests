package org.example;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AnyStrategy extends SearchStrategy {
    @Override
    public Set<Integer> match(String[] people, Map<String, Set<Integer>> invertedIndex, String[] queryWords) {
        Set<Integer> result = new HashSet<>();
        for (String word : queryWords) {
            Set<Integer> indexes = invertedIndex.get(word);
            if (indexes != null) {
                result.addAll(indexes);
            }
        }
        return result;
    }
}
