import org.example.AllStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AllStrategyTest {
    private AllStrategy strategy;
    private String[] people;
    private Map<String, Set<Integer>> invertedIndex;

    @BeforeEach
    void setUp() {
        strategy = new AllStrategy();
        people = new String[]{
                "Alice Smith",
                "Bob Johnson",
                "Alice Johnson"
        };
        invertedIndex = new HashMap<>();
        invertedIndex.put("alice", Set.of(0, 2));
        invertedIndex.put("smith", Set.of(0));
        invertedIndex.put("bob", Set.of(1));
        invertedIndex.put("johnson", Set.of(1, 2));
    }

    @Test
    void testAllStrategySingleWordMatch() {
        Set<Integer> result = strategy.match(people, invertedIndex, new String[]{"alice"});
        assertEquals(Set.of(0, 2), result);
    }

    @Test
    void testAllStrategyMultipleWordsMatchOnePerson() {
        Set<Integer> result = strategy.match(people, invertedIndex, new String[]{"alice", "smith"});
        assertEquals(Set.of(0), result);
    }

    @Test
    void testAllStrategyMultipleWordsMatchAnotherPerson() {
        Set<Integer> result = strategy.match(people, invertedIndex, new String[]{"bob", "johnson"});
        assertEquals(Set.of(1), result);
    }

    @Test
    void testAllStrategyNoCommonPerson() {
        Set<Integer> result = strategy.match(people, invertedIndex, new String[]{"smith", "johnson"});
        assertEquals(Set.of(), result);
    }

    @Test
    void testAllStrategyNoMatches() {
        Set<Integer> result = strategy.match(people, invertedIndex, new String[]{"charlie"});
        assertEquals(Set.of(), result);
    }

    @Test
    void testAllStrategyEmptyQuery() {
        Set<Integer> result = strategy.match(people, invertedIndex, new String[]{""});
        assertEquals(Set.of(), result);
    }
}
