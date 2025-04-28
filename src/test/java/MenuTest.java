import org.example.Menu;
import org.example.PeopleDatabase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class MenuTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalOut;
    private PeopleDatabase realDatabase;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        realDatabase = new PeopleDatabase(new String[]{"Alice Smith", "Bob Johnson"});
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    @Test
    void testInvalidStrategyInput() {
        String input = "1\nFOO\nsomeQuery\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Menu menu = new Menu(realDatabase);
        menu.start();

        String output = outContent.toString();
        assertTrue(output.contains("Invalid strategy selected."));
        assertTrue(output.contains("Bye!"));
    }

    @Test
    void testExitOption() {
        String input = "0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Menu menu = new Menu(realDatabase);
        menu.start();

        String output = outContent.toString();
        assertTrue(output.contains("Bye!"));
    }

    @Test
    void testMenuPrintAllAndExit() {
        PeopleDatabase mockDatabase = mock(PeopleDatabase.class);

        String input = "2\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Menu menu = new Menu(mockDatabase);
        menu.start();

        verify(mockDatabase, times(1)).printAll();
        String output = outContent.toString();

        assertTrue(output.contains("=== Menu ==="));
        assertTrue(output.contains("Bye!"));
    }

    @Test
    void testAllStrategySearch() {
        String input = "1\nALL\nAlice\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Menu menu = new Menu(realDatabase);
        menu.start();

        String output = outContent.toString();
        assertTrue(output.contains("Select a matching strategy: ALL, ANY, NONE"));
        assertTrue(output.contains("Enter a name or email to search all suitable people."));
        assertTrue(output.contains("Alice Smith") || output.contains("No matching people found"));
    }

    @Test
    void testAnyStrategySearch() {
        String input = "1\nANY\nSmith\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Menu menu = new Menu(realDatabase);
        menu.start();

        String output = outContent.toString();
        assertTrue(output.contains("Select a matching strategy: ALL, ANY, NONE"));
    }

    @Test
    void testNoneStrategySearch() {
        String input = "1\nNONE\nBob\n0\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Menu menu = new Menu(realDatabase);
        menu.start();

        String output = outContent.toString();
        assertTrue(output.contains("Select a matching strategy: ALL, ANY, NONE"));
    }
}
