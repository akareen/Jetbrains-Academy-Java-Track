import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {
    List<String> names = new ArrayList<>();

    public PhoneBook(String fileName, int startLocation) {
        makePhoneBook(fileName, startLocation);
    }

    private void makePhoneBook(String fileName, int startLocation) {
        File file = new File(fileName);
        try (Scanner fileInput = new Scanner(file)) {
            while (fileInput.hasNextLine())
                this.names.add(
                        extractNamesFromLine(fileInput.nextLine().split(" "), startLocation));
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String extractNamesFromLine(String[] ls, int startLocation) {
        StringBuilder name = new StringBuilder();
        for (int i = startLocation; i < ls.length; i++) {
            name.append(ls[i]).append(" ");
        }
        return name.toString().trim();
    }

    public List<String> getPhoneBook() {
        return this.names;
    }

    public boolean bubbleSort(long linearTime) {
        long start = System.currentTimeMillis();
        while (true) {
            int swaps = 0;
            for (int i = 0; i < this.names.size(); i++) {
                if (i + 1 < this.names.size() && this.names.get(i).compareTo(this.names.get(i + 1)) > 0) {
                    String temp = this.names.get(i);
                    this.names.set(i, this.names.get(i + 1));
                    this.names.set(i + 1, temp);
                    swaps++;
                }
            }
            long current = System.currentTimeMillis();
            if (swaps == 0)
                return true;
            if (current - start > linearTime)
                return false;
        }
    }

    //create a quicksort method for the names arraylist by alphabetic ascending order
    public void quickSort() {
        _quickSort(0, this.names.size() - 1);
    }

    private void _quickSort(int low, int high) {
        if (low < high) {
            int pivot = partition(low, high);
            _quickSort(low, pivot - 1);
            _quickSort(pivot + 1, high);
        }
    }

    private int partition(int low, int high) {
        String pivot = this.names.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (this.names.get(j).compareTo(pivot) < 0) {
                i++;
                String temp = this.names.get(i);
                this.names.set(i, this.names.get(j));
                this.names.set(j, temp);
            }
        }
        String temp = this.names.get(i + 1);
        this.names.set(i + 1, this.names.get(high));
        this.names.set(high, temp);
        return i + 1;
    }
}