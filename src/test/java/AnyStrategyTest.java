import org.example.AnyStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnyStrategyTest {
    private AnyStrategy anyStrategy;
    private String[] people;
    private Map<String, Set<Integer>> invertedIndex;

    @BeforeEach
    void setUp() {
        anyStrategy = new AnyStrategy();
        people = new String[]{
                "Alice Smith",
                "Bob Johnson",
                "Charlie Brown"
        };

        invertedIndex = new HashMap<>();
        invertedIndex.put("alice", Set.of(0));
        invertedIndex.put("smith", Set.of(0));
        invertedIndex.put("bob", Set.of(1));
        invertedIndex.put("johnson", Set.of(1));
        invertedIndex.put("charlie", Set.of(2));
        invertedIndex.put("brown", Set.of(2));
    }

    @Test
    void testAnyStrategySingleMatch() {
        Set<Integer> result = anyStrategy.match(people, invertedIndex, new String[]{"alice"});
        assertEquals(Set.of(0), result);
    }

    @Test
    void testAnyStrategyMultipleMatch() {
        Set<Integer> result = anyStrategy.match(people, invertedIndex, new String[]{"alice", "bob"});
        assertEquals(Set.of(0, 1), result);
    }

    @Test
    void testAnyStrategyNoMatches() {
        Set<Integer> result = anyStrategy.match(people, invertedIndex, new String[]{"mickey"});
        assertEquals(Set.of(), result);
    }

    @Test
    void testAnyStrategyDuplicateWords() {
        Set<Integer> result = anyStrategy.match(people, invertedIndex, new String[]{"alice", "smith"});
        assertEquals(Set.of(0), result);
    }
}
