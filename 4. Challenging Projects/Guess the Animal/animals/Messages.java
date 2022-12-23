package animals;

import java.time.LocalTime;

public class Messages {

    private static final LocalTime FIVE_AM = LocalTime.of(5, 0);
    private static final LocalTime NOON = LocalTime.of(12, 0);
    private static final LocalTime SIX_PM = LocalTime.of(18, 0);

    private static final String[] NOT_UNDERSTOOD = {"I'm not sure I caught you: was it yes or no?",
            "Funny, I still don't understand, is it yes or no?",
            "Oh, it's too complicated for me: just tell me yes or no.",
            "Could you please simply say yes or no?",
            "Oh, no, don't try to confuse me: say yes or no."};
    private static final String[] GOODBYE = {"Bye!", "See you!", "Goodbye!", "Bye-bye!", "See you later!", "Goodbye, have a nice day!"};


    public static void greetingMessage() {
        LocalTime time = LocalTime.now();
        if (time.isAfter(FIVE_AM) && time.isBefore(NOON))
            System.out.println("Good morning!\n");
        else if (time.isAfter(NOON) && time.isBefore(SIX_PM))
            System.out.println("Good afternoon!\n");
        else
            System.out.println("Good evening!\n");
    }

    public static void notUnderstoodMessage() {
        int index = (int) (Math.random() * NOT_UNDERSTOOD.length);
        System.out.println(NOT_UNDERSTOOD[index]);
    }

    public static void goodbyeMessage() {
        int index = (int) (Math.random() * GOODBYE.length);
        System.out.println(GOODBYE[index]);
    }
}
