public class Main {
    static String pathFile = "/Users/ak/Downloads/Misc./Search Folder/directory.txt";
    static String pathFind = "/Users/ak/Downloads/Misc./Search Folder/find.txt";


    public static long linearSearch(PhoneBook mainFile, PhoneBook findFile) {
        System.out.println("Start searching (linear search)...");

        long startTime = System.currentTimeMillis();
        int found = Searching.linearSearch(mainFile.getPhoneBook(), findFile.getPhoneBook());
        long endTime = System.currentTimeMillis();

        Printing.printTimeTaken(endTime - startTime, findFile.getPhoneBook().size(), found);
        System.out.println();
        return endTime - startTime;
    }

    public static void bubbleSortJumpSearch(PhoneBook mainFile, PhoneBook findFile, long linearTime) {
        System.out.println("Start searching (bubble sort + jump search)...");

        long startTime = System.currentTimeMillis();
        long startSorting = System.currentTimeMillis();
        boolean fullySorted = mainFile.bubbleSort(linearTime);
        long endSorting = System.currentTimeMillis();

        long searchingTime = System.currentTimeMillis();
        int found;
        if (fullySorted)
            found = Searching.linearSearch(mainFile.getPhoneBook(), findFile.getPhoneBook());
        else
            found = Searching.jumpSearch(mainFile.getPhoneBook(), findFile.getPhoneBook());

        long endSearchingTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();

        Printing.printTimeTaken(endTime - startTime, findFile.getPhoneBook().size(), found);
        Printing.printSortingSearchingTime(0, endSorting - startSorting);
        Printing.printSortingSearchingTime(1, endSearchingTime - searchingTime);
        System.out.println();
    }

    public static void quickSortBinarySearch(PhoneBook mainFile, PhoneBook findFile) {
        System.out.println("Start searching (quick sort + binary search)...");

        long startTime = System.currentTimeMillis();
        long startSorting = System.currentTimeMillis();
        mainFile.quickSort();
        long endSorting = System.currentTimeMillis();

        long searchingTime = System.currentTimeMillis();
        int found = Searching.binarySearch(mainFile.getPhoneBook(), findFile.getPhoneBook());
        long endSearchingTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();

        Printing.printTimeTaken(endTime - startTime, findFile.getPhoneBook().size(), found);
        Printing.printSortingSearchingTime(0, endSorting - startSorting);
        Printing.printSortingSearchingTime(1, endSearchingTime - searchingTime);
        System.out.println();
    }

    public static void hashMapSearch(String filename, PhoneBook findFile) {
        System.out.println("Start searching (hash table)...");
        long startTime = System.currentTimeMillis();
        long startCreationTime = System.currentTimeMillis();
        HashMapPhoneBook hashMapPhoneBook = new HashMapPhoneBook(filename);
        long endCreationTime = System.currentTimeMillis();

        long searchingTime = System.currentTimeMillis();
        int found = Searching.hashSearch(hashMapPhoneBook.names, findFile.getPhoneBook());
        long endSearchingTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();

        Printing.printTimeTaken(endTime - startTime, 500, found);
        Printing.printSortingSearchingTime(2, endCreationTime - startCreationTime);
        Printing.printSortingSearchingTime(1, endSearchingTime - searchingTime);

        System.out.println();
    }


    public static void main(String[] args) {
        PhoneBook mainFile = new PhoneBook(pathFile, 1);
        PhoneBook findFile = new PhoneBook(pathFind, 0);

        long linearTime = linearSearch(mainFile, findFile);
        bubbleSortJumpSearch(mainFile, findFile, linearTime);
        quickSortBinarySearch(mainFile, findFile);
        hashMapSearch(pathFile, findFile);
    }
}