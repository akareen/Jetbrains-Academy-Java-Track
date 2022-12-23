import java.util.List;
import java.util.Map;

public class Searching {
    public static int linearSearch(List<String> source, List<String> find) {
        int count = 0;
        for (String str : find) {
            if (searchAlgorithm(str, source)) {
                count++;
            }
        }
        return count;
    }

    public static int jumpSearch(List<String> source, List<String> find) {
        int count = 0;
        for (String str : find) {
            if (searchAlgorithm(str, source)) {
                count++;
            }
        }
        return count;
    }

    public static boolean searchAlgorithm(String name, List<String> list) {
        for (String s : list) {
            if (s.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static int binarySearch(List<String> source, List<String> find) {
        int count = 0;
        for (String str : find) {
            if (_binarySearch(str, source)) {
                count++;
            }
        }
        return count;
    }

    public static boolean _binarySearch(String item, List<String> source) {
        int low = 0;
        int high = source.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int res = item.compareTo(source.get(mid));
            if (res == 0) // if same
                return true;
            else if (res > 0) // if higher
                low = mid + 1;
            else // if lower
                high = mid - 1;
        }
        return false;
    }

    public static int hashSearch(Map<String, Integer> source, List<String> find) {
        int count = 0;
        for (String str : find) {
            if (source.containsKey(str)) {
                count++;
            }
        }
        return count;
    }
}