import org.example.DataLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class DataLoaderTest {
    private Path tempFile;
    private ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputContent));
    }

    @AfterEach
    void tearDown() throws IOException {
        System.setOut(originalOut);
        if (tempFile != null && Files.exists(tempFile)) {
            Files.delete(tempFile);
        }
    }

    @Test
    void testLoadDataFromExistingFile() throws IOException {
        tempFile = Files.createTempFile("testData", ".txt");
        Files.write(tempFile, "Alice Smith\nBob Johnson\nCharlie Brown\n".getBytes());
        DataLoader dataLoader = new DataLoader(tempFile.toString());
        String[] data = dataLoader.loadData();
        assertArrayEquals(new String[]{
                "Alice Smith", "Bob Johnson", "Charlie Brown"
        }, data);
    }

    @Test
    void testLoadDataFromNonExistingFile() throws IOException {
        String invalidFilename = "invalidFilename.txt";
        DataLoader dataLoader = new DataLoader(invalidFilename);
        String[] data = dataLoader.loadData();
        assertEquals(0, data.length);
        String output = outputContent.toString();
        assertTrue(output.contains("File not found: " + invalidFilename));
    }

    @Test
    void testLoadDataFromEmptyFile() throws IOException {
        tempFile = Files.createTempFile("testData", ".txt");
        DataLoader dataLoader = new DataLoader(tempFile.toString());
        String[] data = dataLoader.loadData();
        assertEquals(0, data.length);
    }
}

