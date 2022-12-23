package animals;

import java.util.Scanner;

public class Game {
    public static void play() {
        Messages.greetingMessage();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an animal:");
        String animal = scanner.nextLine();
        String modifiedAnimal = InputProcessing.modifyString(animal);
        System.out.println("Is it " + modifiedAnimal + "?");

        String userAnswer;
        while (true) {
            String answer = scanner.nextLine().trim();
            if (InputProcessing.isYes(answer)) {
                userAnswer = "Yes";
                break;
            }
            if (InputProcessing.isNo(answer)) {
                userAnswer = "No";
                break;
            }
            Messages.notUnderstoodMessage();
        }
        System.out.printf("You answered: %s%n", userAnswer);
        System.out.println();
        Messages.goodbyeMessage();
    }
}
