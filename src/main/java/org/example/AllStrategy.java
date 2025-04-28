package org.example;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AllStrategy extends SearchStrategy {
    @Override
    public Set<Integer> match(String[] people, Map<String, Set<Integer>> invertedIndex, String[] queryWords) {
        Set<Integer> result = null;

        for (String word : queryWords) {
            Set<Integer> indexes = invertedIndex.get(word);

            if (indexes == null) {
                return Collections.emptySet();
            }

            if (result == null) {
                result = new HashSet<>(indexes);
            } else {
                result.retainAll(indexes);
            }
        }
        return result != null ? result : Collections.emptySet();
    }
}

