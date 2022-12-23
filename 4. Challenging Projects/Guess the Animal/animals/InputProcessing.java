package animals;

public class InputProcessing {
    private static final String[] YES = {"y", "yes", "yeah", "yep", "sure", "right", "affirmative", "correct", "indeed",
            "you bet", "exactly", "you said it"};
    private static final String[] NO = {"n", "no", "no way", "nah", "nope", "negative", "i don't think so", "yeah no"};


    public static String modifyString(String sourceString) {
        String[] ls = sourceString.split(" ");
        if (ls.length > 1 && matchesAOrAn(ls[0])) {
            return sourceString.toLowerCase();
        }

        String animal = mergeListIntoString(ls);
        StringBuilder sb = new StringBuilder();
        char firstLetterOfAnimal = animal.charAt(0);
        if (firstLetterOfAnimal == 'a' || firstLetterOfAnimal == 'e' ||
                firstLetterOfAnimal == 'i' || firstLetterOfAnimal == 'o' || firstLetterOfAnimal == 'u')
            sb.append("an ");
        else
            sb.append("a ");
        sb.append(animal.toLowerCase());
        return sb.toString();
    }

    public static boolean isYes(String answer) {
        answer = removeTrailingExclamationOrPeriod(answer);
        for (String s : YES) {
            if (s.equalsIgnoreCase(answer)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNo(String answer) {
        answer = removeTrailingExclamationOrPeriod(answer);
        for (String s : NO) {
            if (s.equalsIgnoreCase(answer)) {
                return true;
            }
        }
        return false;
    }

    private static boolean matchesAOrAn(String s) {
        //upper or lower case
        return s.equals("a") || s.equals("A") || s.equals("an") || s.equals("An");
    }

    private static String removeTrailingExclamationOrPeriod(String s) {
        if (s.endsWith(".") || s.endsWith("!")) {
            return s.substring(0, s.length() - 1);
        }
        return s;
    }

    private static String mergeListIntoString(String[] ls) {
        StringBuilder sb = new StringBuilder();
        for (String s : ls) {
            if (!s.equalsIgnoreCase("the")) {
                sb.append(s);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}
