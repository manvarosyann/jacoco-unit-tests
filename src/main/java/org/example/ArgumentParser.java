package org.example;

public class ArgumentParser {
    public static String getFilename(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if ("--data".equals(args[i])) {
                return args[i + 1];
            }
        }
        return null;
    }
}

