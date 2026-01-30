package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String term = scanner.nextLine();        // read term
        String definition = scanner.nextLine();  // read correct definition
        String answer = scanner.nextLine();      // read user's answer

        if (answer.equals(definition)) {
            System.out.println("Your answer is right!");
        } else {
            System.out.println("Your answer is wrong...");
        }
    }
}
