import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashMapPhoneBook {
    Map<String, Integer> names = new HashMap<>();

    public HashMapPhoneBook(String fileName) {
        makePhoneBook(fileName);
    }

    private void makePhoneBook(String fileName) {
        try {
            File file = new File(fileName);
            Scanner fileInput = new Scanner(file);
            while (fileInput.hasNextLine()) {
                String[] line = fileInput.nextLine().split(" ");
                String name = extractNamesFromLine(line);
                int number = Integer.parseInt(line[0]);
                this.names.put(name, number);
            }
            fileInput.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String extractNamesFromLine(String[] ls) {
        StringBuilder name = new StringBuilder();
        for (int i = 1; i < ls.length; i++) {
            name.append(ls[i]).append(" ");
        }
        return name.toString().trim();
    }
}