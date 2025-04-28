import org.example.ArgumentParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class ArgumentParserTest {
    @Test
    void testGetFileName() {
        String[] args = {"--data", "file.txt"};
        String filename = ArgumentParser.getFilename(args);
        assertEquals("file.txt", filename);
    }

    @Test
    void testGetFilenameNoDataOption() {
        String[] args = {"--another", "option"};
        String filename = ArgumentParser.getFilename(args);
        assertNull(filename);
    }

    @Test
    void testGetFilenameNoFilename() {
        String[] args = {"--data"};
        String filename = ArgumentParser.getFilename(args);
        assertNull(filename);
    }

    @Test
    void testGetFileNameEmptyArgs() {
        String[] args = {""};
        String filename = ArgumentParser.getFilename(args);
        assertNull(filename);
    }
}
