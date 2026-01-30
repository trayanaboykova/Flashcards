package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the number of cards:");
        int n = Integer.parseInt(scanner.nextLine());

        String[] terms = new String[n];
        String[] definitions = new String[n];

        // create cards
        for (int i = 0; i < n; i++) {
            System.out.println("Card #" + (i + 1) + ":");
            terms[i] = scanner.nextLine();

            System.out.println("The definition for card #" + (i + 1) + ":");
            definitions[i] = scanner.nextLine();
        }

        // quiz user
        for (int i = 0; i < n; i++) {
            System.out.println("Print the definition of \"" + terms[i] + "\":");
            String answer = scanner.nextLine();

            if (answer.equals(definitions[i])) {
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong. The right answer is \"" + definitions[i] + "\".");
            }
        }
    }
}
