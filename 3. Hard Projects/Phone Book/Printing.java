public class Printing {
    public static void printTimeTaken(long timeDifference, int size, int found) {
        long minutes = timeDifference / 60000;
        long seconds = (timeDifference % 60000) / 1000;
        long milliseconds = timeDifference % 1000;

        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n",
                found, size, minutes, seconds, milliseconds);
    }

    public static void printSortingSearchingTime(int mode, long timeDifference) {
        long minutes = timeDifference / 60000;
        long seconds = (timeDifference % 60000) / 1000;
        long milliseconds = timeDifference % 1000;
        if (mode == 0) {
            System.out.printf("Sorting time: %d min. %d sec. %d ms.\n",
                    minutes, seconds, milliseconds);
        }
        else if (mode == 1) {
            System.out.printf("Searching time: %d min. %d sec. %d ms.\n",
                    minutes, seconds, milliseconds);
        }
        else if (mode == 2) {
            System.out.printf("Creating time: %d min. %d sec. %d ms.\n",
                    minutes, seconds, milliseconds);
        }
    }
}