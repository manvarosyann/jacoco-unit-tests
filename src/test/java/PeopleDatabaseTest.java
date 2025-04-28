import org.example.PeopleDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class PeopleDatabaseTest {
    private PeopleDatabase peopleDatabase;

    @BeforeEach
    void setUp() {
        String[] people = {
                "Alice Smith",
                "Bob Johnson",
        };
        peopleDatabase = new PeopleDatabase(people);
    }

    @Test
    void testGetPeople() {
        String[] people = peopleDatabase.getPeople();
        assertArrayEquals(new String[]{"Alice Smith", "Bob Johnson"}, people);
    }

    @Test
    void testInvertedIndex() {
        Map<String, Set<Integer>> index = peopleDatabase.getInvertedIndex();

        assertTrue(index.containsKey("alice"));
        assertTrue(index.containsKey("smith"));
        assertTrue(index.containsKey("bob"));
        assertTrue(index.containsKey("johnson"));

        assertEquals(Set.of(0), index.get("alice"));
        assertEquals(Set.of(0), index.get("smith"));
        assertEquals(Set.of(1), index.get("bob"));
        assertEquals(Set.of(1), index.get("johnson"));
    }

    @Test
    void testGetPeopleReturnsArray() {
        PeopleDatabase db = new PeopleDatabase(new String[]{"Alice Smith", "Bob Johnson"});
        String[] people = db.getPeople();
        assertEquals(2, people.length);
    }

    @Test
    void testGetInvertedIndexNotNull() {
        PeopleDatabase db = new PeopleDatabase(new String[]{"Alice Smith", "Bob Johnson"});
        assertNotNull(db.getInvertedIndex());
    }

    @Test
    void testPrintAllOutputsCorrectly() {
        PeopleDatabase db = new PeopleDatabase(new String[]{"Alice Smith", "Bob Johnson"});

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        db.printAll();
        String output = outContent.toString();

        assertTrue(output.contains("Alice Smith"));
        assertTrue(output.contains("Bob Johnson"));
    }

    @Test
    void testEmptyPeopleList() {
        PeopleDatabase db = new PeopleDatabase(new String[]{});

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        db.printAll();
        String output = outContent.toString();

        assertNotNull(output);
    }
}
