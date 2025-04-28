//import org.example.Main;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class MainTest {
//
//    @Test
//    void testNoDataFileProvided() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        Main.main(new String[]{});
//
//        String output = outContent.toString();
//        assertTrue(output.contains("No data file provided"));
//    }
//
//    @Test
//    void testMainWithInvalidFile() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        Main.main(new String[]{"--data", "nonexistentfile.txt"});
//
//        String output = outContent.toString();
//        assertTrue(output.contains("File not found"));
//    }
//}

