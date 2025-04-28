package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String filename = ArgumentParser.getFilename(args);

        if (filename == null) {
            System.out.println("No data file provided. Use --data <filename>");
            return;
        }

        DataLoader loader = new DataLoader(filename);
        PeopleDatabase database = new PeopleDatabase(loader.loadData());
        Menu menu = new Menu(database);
        menu.start();
    }
}
