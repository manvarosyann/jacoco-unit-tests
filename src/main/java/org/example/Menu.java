package org.example;

import java.util.Scanner;

public class Menu {
    private final PeopleDatabase database;
    private final Scanner scanner;

    public Menu(PeopleDatabase database) {
        this.database = database;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        SearchEngine engine = new SearchEngine(database);

        while (true) {
            printMenu();
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    String strategyInput = scanner.nextLine();
                    System.out.println("Enter a name or email to search all suitable people.");
                    String query = scanner.nextLine();

                    SearchStrategy strategy;

                    switch (strategyInput) {
                        case "ALL":
                            strategy = new AllStrategy();
                            break;
                        case "ANY":
                            strategy = new AnyStrategy();
                            break;
                        case "NONE":
                            strategy = new NoneStrategy();
                            break;
                        default:
                            System.out.println("Invalid strategy selected.");
                            continue;
                    }

                    engine.performSearch(query, strategy);
                    break;
                case 2:
                    database.printAll();
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
            }
        }
    }

    private void printMenu() {
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }
}


