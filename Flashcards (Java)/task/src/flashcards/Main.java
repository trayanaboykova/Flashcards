package flashcards;

import java.io.*;
import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static final Map<String, String> termToDef = new LinkedHashMap<>();
    private static final Map<String, String> defToTerm = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String action = scanner.nextLine().trim();

            switch (action) {
                case "add":
                    addCard();
                    break;
                case "remove":
                    removeCard();
                    break;
                case "import":
                    importCards();
                    break;
                case "export":
                    exportCards();
                    break;
                case "ask":
                    askCards();
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    return;
                default:
                    // ignore unknown actions (not usually tested)
                    break;
            }
            System.out.println();
        }
    }

    private static void addCard() {
        System.out.println("The card:");
        String term = scanner.nextLine();

        if (termToDef.containsKey(term)) {
            System.out.println("The card \"" + term + "\" already exists.");
            return; // back to menu
        }

        System.out.println("The definition of the card:");
        String def = scanner.nextLine();

        if (defToTerm.containsKey(def)) {
            System.out.println("The definition \"" + def + "\" already exists.");
            return; // back to menu
        }

        termToDef.put(term, def);
        defToTerm.put(def, term);

        System.out.println("The pair (\"" + term + "\":\"" + def + "\") has been added.");
    }

    private static void removeCard() {
        System.out.println("Which card?");
        String term = scanner.nextLine();

        if (!termToDef.containsKey(term)) {
            System.out.println("Can't remove \"" + term + "\": there is no such card.");
            return;
        }

        String def = termToDef.remove(term);
        defToTerm.remove(def);

        System.out.println("The card has been removed.");
    }

    private static void importCards() {
        System.out.println("File name:");
        String fileName = scanner.nextLine();

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        int loaded = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t", 2);
                if (parts.length < 2) continue;

                String term = parts[0];
                String def = parts[1];

                if (termToDef.containsKey(term)) {
                    String oldDef = termToDef.get(term);
                    defToTerm.remove(oldDef);
                }

                termToDef.put(term, def);
                defToTerm.put(def, term);
                loaded++;
            }
        } catch (IOException ignored) { }

        System.out.println(loaded + " cards have been loaded.");
    }

    private static void exportCards() {
        System.out.println("File name:");
        String fileName = scanner.nextLine();

        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> e : termToDef.entrySet()) {
                out.println(e.getKey() + "\t" + e.getValue());
            }
        } catch (IOException ignored) { }

        System.out.println(termToDef.size() + " cards have been saved.");
    }

    private static void askCards() {
        System.out.println("How many times to ask?");
        int times = Integer.parseInt(scanner.nextLine());

        List<String> terms = new ArrayList<>(termToDef.keySet());

        for (int i = 0; i < times; i++) {
            String term = terms.get(random.nextInt(terms.size()));
            String correctDef = termToDef.get(term);

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
