import org.example.PeopleDatabase;
import org.example.SearchEngine;
import org.example.SearchStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SearchEngineTest {
    private PeopleDatabase peopleDatabase;
    private SearchStrategy searchStrategy;
    private SearchEngine searchEngine;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        peopleDatabase = mock(PeopleDatabase.class);
        searchStrategy = mock(SearchStrategy.class);
        searchEngine = new SearchEngine(peopleDatabase);
    }

    @Test
    void testPerformSearchWithResults() {
        String[] people = {"Alice Smith", "Bob Johnson"};
        when(peopleDatabase.getPeople()).thenReturn(people);
        when(peopleDatabase.getInvertedIndex()).thenReturn(null);
        when(searchStrategy.search(people, null, new String[]{"alice"})).thenReturn(List.of("Alice Smith"));
        searchEngine.performSearch("alice", searchStrategy);
        String output = outContent.toString();
        assertTrue(output.contains("1 persons found"));
        assertTrue(output.contains("Alice Smith"));
    }

    @Test
    void testPerformSearchWithoutResults() {
        String[] people = {"Alice Smith", "Bob Johnson"};
        when(peopleDatabase.getPeople()).thenReturn(people);
        when(peopleDatabase.getInvertedIndex()).thenReturn(null);
        when(searchStrategy.match(people, null, new String[]{"charlie"})).thenReturn(Set.of());
        searchEngine.performSearch("charlie", searchStrategy);
        String output = outContent.toString();
        assertTrue(output.contains("No matching people found."));
    }

    @Test
    void testPerformSearchMultipleResults() {
        String[] people = {"Alice Smith", "Bob Johnson", "Alice Johnson"};
        when(peopleDatabase.getPeople()).thenReturn(people);
        when(peopleDatabase.getInvertedIndex()).thenReturn(null);
        when(searchStrategy.search(people, null, new String[]{"alice"})).thenReturn(List.of("Alice Smith", "Alice Johnson"));
        searchEngine.performSearch("alice", searchStrategy);
        String output = outContent.toString();
        assertTrue(output.contains("2 persons found"));
        assertTrue(output.contains("Alice Smith"));
        assertTrue(output.contains("Alice Johnson"));
    }

    @Test
    void testNoMatchingPeople() {
        String[] people = {"Alice Smith", "Bob Johnson"};
        when(peopleDatabase.getPeople()).thenReturn(people);
        when(peopleDatabase.getInvertedIndex()).thenReturn(null);
        when(searchStrategy.match(people, null, new String[]{"nonexistent"})).thenReturn(Set.of());
        searchEngine.performSearch("nonexistent", searchStrategy);
        String output = outContent.toString();
        assertTrue(output.contains("No matching people found."));
    }

    @Test
    void testPerformSearchWithEmptyDatabase() {
        when(peopleDatabase.getPeople()).thenReturn(new String[]{});
        when(peopleDatabase.getInvertedIndex()).thenReturn(null);
        when(searchStrategy.search(new String[]{}, null, new String[]{"any"})).thenReturn(List.of());
        searchEngine.performSearch("any", searchStrategy);
        String output = outContent.toString();
        assertTrue(output.contains("No matching people found."));
    }
}
