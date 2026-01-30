package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Card:");
        String term = scanner.nextLine();
        System.out.println(term);

        System.out.println("Definition:");
        String definition = scanner.nextLine();
        System.out.println(definition);
    }
}
