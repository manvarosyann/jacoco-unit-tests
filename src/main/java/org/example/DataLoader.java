package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataLoader {
    private final String filename;

    public DataLoader(String filename) {
        this.filename = filename;
    }

    public String[] loadData() {
        File file = new File(filename);
        int lineCount = 0;

        try (Scanner counter = new Scanner(file)) {
            while (counter.hasNextLine()) {
                counter.nextLine();
                lineCount++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
            return new String[0];
        }

        String[] people = new String[lineCount];
        try (Scanner reader = new Scanner(file)) {
            int index = 0;
            while (reader.hasNextLine() && index < lineCount) {
                people[index++] = reader.nextLine().trim();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
        return people;
    }
}

