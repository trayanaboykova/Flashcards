package flashcards;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the number of cards:");
        int n = Integer.parseInt(scanner.nextLine());

        Map<String, String> termToDef = new LinkedHashMap<>(); // keeps insertion order for quiz
        Map<String, String> defToTerm = new HashMap<>();

        // create cards
        for (int i = 1; i <= n; i++) {

            // read unique term
            System.out.println("Card #" + i + ":");
            String term = scanner.nextLine();
            while (termToDef.containsKey(term)) {
                System.out.println("The term \"" + term + "\" already exists. Try again:");
                term = scanner.nextLine();
            }

            // read unique definition
            System.out.println("The definition for card #" + i + ":");
            String def = scanner.nextLine();
            while (defToTerm.containsKey(def)) {
                System.out.println("The definition \"" + def + "\" already exists. Try again:");
                def = scanner.nextLine();
            }

            termToDef.put(term, def);
            defToTerm.put(def, term);
        }

        // quiz user (in the order added)
        for (Map.Entry<String, String> entry : termToDef.entrySet()) {
            String term = entry.getKey();
            String correctDef = entry.getValue();

            System.out.println("Print the definition of \"" + term + "\":");
            String answer = scanner.nextLine();

            if (answer.equals(correctDef)) {
                System.out.println("Correct!");
            } else if (defToTerm.containsKey(answer)) {
                String otherTerm = defToTerm.get(answer);
                System.out.println("Wrong. The right answer is \"" + correctDef
                        + "\", but your definition is correct for \"" + otherTerm + "\".");
            } else {
                System.out.println("Wrong. The right answer is \"" + correctDef + "\".");
            }
        }
    }
}
