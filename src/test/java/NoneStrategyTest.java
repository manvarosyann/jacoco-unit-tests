import org.example.NoneStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoneStrategyTest {
    private NoneStrategy noneStrategy;
    private String[] people;
    private Map<String, Set<Integer>> invertedIndex;

    @BeforeEach
    void setUp() {
        noneStrategy = new NoneStrategy();
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
    void testNoneStrategyExcludesMatchedIndexes() {
        Set<Integer> result = noneStrategy.match(people, invertedIndex, new String[]{"alice"});
        assertEquals(Set.of(1, 2), result);
    }

    @Test
    void testNoneStrategyExcludesMultipleMatches() {
        Set<Integer> result = noneStrategy.match(people, invertedIndex, new String[]{"bob", "johnson"});
        assertEquals(Set.of(0, 2), result);
    }

    @Test
    void testNoneStrategyNoMatches() {
        Set<Integer> result = noneStrategy.match(people, invertedIndex, new String[]{"alice", "bob", "charlie"});
        assertEquals(Set.of(), result);
    }
}
