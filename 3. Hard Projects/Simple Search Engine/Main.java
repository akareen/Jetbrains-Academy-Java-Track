import java.io.File;
import java.util.*;

public class Main {
    static List<String> data = new ArrayList<>();
    static Map<String, List<Integer>> invertedIndex = new HashMap<>();
    static boolean[] printingLines;



    public static void readFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner fileInput = new Scanner(file);
            while (fileInput.hasNextLine()) {
                data.add(fileInput.nextLine());
            }
            fileInput.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void createInvertedIndex() {
        for (int i = 0; i < data.size(); i++) {
            String[] ls = data.get(i).split(" ");
            for (String str : ls) {
                str = str.toLowerCase();
                if (invertedIndex.containsKey(str))
                    invertedIndex.get(str).add(i);
                else
                    invertedIndex.put(str, new ArrayList<>(List.of(i)));
            }
        }
    }

    public static void searchAny(String[] ls) {
        for (String searchQuery : ls) {
            searchQuery = searchQuery.toLowerCase();
            if (invertedIndex.containsKey(searchQuery)) {
                for (Integer lineNum : invertedIndex.get(searchQuery))
                    printingLines[lineNum] = true;
            }
        }
    }

    public static void searchAll(String [] ls) {
        for (int i = 0; i < data.size(); i++) {
            int found = 0;
            String[] splitLine = data.get(i).split(" ");
            for (String line : splitLine) {
                for (String query : ls) {
                    if (query.equalsIgnoreCase(line))
                        found++;
                }
            }
            if (found >= ls.length)
                printingLines[i] = true;
        }
    }

    public static void printResultOnTrue() {
        int total = countNumber(true);
        if (total <= 0)
            System.out.println("No matching people found.");
        else {
            System.out.printf("%d persons found:%n", total);
            for (int i = 0; i < printingLines.length; i++)
                if (printingLines[i])
                    System.out.println(data.get(i));
        }
    }

    public static void printResultOnFalse() {
        int total = countNumber(false);
        System.out.printf("\n%d persons found:%n", total);
        for (int i = 0; i < printingLines.length; i++)
            if (!printingLines[i])
                System.out.println(data.get(i));
    }

    public static int countNumber(boolean flag) {
        int total = 0;
        for (boolean val : printingLines) {
            if (flag && val)
                total++;
            else if (!flag && !val)
                total++;
        }
        return total;
    }



    public static void printMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }

    public static void findAPerson() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nSelect a matching strategy: ALL, ANY, NONE");
        String choice = scanner.nextLine();
        System.out.println("\nEnter a name or email to search all suitable people.");
        String[] searchQuery = scanner.nextLine().split(" ");
        scanner.close();

        switch (choice) {
            case "ALL" -> {
                searchAll(searchQuery);
                printResultOnTrue();
            }
            case "ANY" -> {
                searchAny(searchQuery);
                printResultOnTrue();
            }
            case "NONE" -> {
                searchAny(searchQuery);
                printResultOnFalse();
            }
        }
    }

    public static void execution(String filename) {
        Scanner scanner = new Scanner(System.in);
        readFile(filename);
        createInvertedIndex();
        printingLines = new boolean[data.size()];

        while (true) {
            printMenu();
            String option = scanner.next();
            switch (option) {
                case "1" -> findAPerson();
                case "2" -> {
                    System.out.println("\n=== List of people ===");
                    for (String line : data)
                        System.out.println(line);
                }
                case "0" -> {
                    System.out.println("\nBye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("\nIncorrect option! Try again.");
            }
        }
    }

    public static void main(String[] args) {
        execution(args[1]);
    }
}