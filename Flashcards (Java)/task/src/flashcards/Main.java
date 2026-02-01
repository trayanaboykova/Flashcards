package flashcards;

import java.io.*;
import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    private static final List<String> log = new ArrayList<>();

    private static class Card {
        String term;
        String definition;
        int errors;

        Card(String term, String definition, int errors) {
            this.term = term;
            this.definition = definition;
            this.errors = errors;
        }
    }

    // term -> Card (keep insertion order for nicer behavior)
    private static final Map<String, Card> cards = new LinkedHashMap<>();
    // definition -> term (for “your definition is correct for …”)
    private static final Map<String, String> defToTerm = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            out("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            String action = in();

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
                case "log":
                    saveLog();
                    break;
                case "hardest card":
                    hardestCard();
                    break;
                case "reset stats":
                    resetStats();
                    break;
                case "exit":
                    out("Bye bye!");
                    return;
                default:
                    // ignore unknown actions
                    break;
            }
            out(""); // blank line between actions
        }
    }

    private static void addCard() {
        out("The card:");
        String term = in();

        if (cards.containsKey(term)) {
            out("The card \"" + term + "\" already exists.");
            return;
        }

        out("The definition of the card:");
        String def = in();

        if (defToTerm.containsKey(def)) {
            out("The definition \"" + def + "\" already exists.");
            return;
        }

        Card c = new Card(term, def, 0);
        cards.put(term, c);
        defToTerm.put(def, term);

        out("The pair (\"" + term + "\":\"" + def + "\") has been added.");
    }

    private static void removeCard() {
        out("Which card?");
        String term = in();

        Card removed = cards.remove(term);
        if (removed == null) {
            out("Can't remove \"" + term + "\": there is no such card.");
            return;
        }

        defToTerm.remove(removed.definition);
        out("The card has been removed.");
    }

    private static void importCards() {
        out("File name:");
        String fileName = in();

        File file = new File(fileName);
        if (!file.exists()) {
            out("File not found.");
            return;
        }

        int loaded = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                // format: term \t definition \t errors
                String[] parts = line.split("\t");
                if (parts.length < 2) continue;

                String term = parts[0];
                String def = parts[1];
                int errors = 0;

                if (parts.length >= 3) {
                    try {
                        errors = Integer.parseInt(parts[2]);
                    } catch (NumberFormatException ignored) {
                        errors = 0;
                    }
                }

                // overwrite existing card if present
                if (cards.containsKey(term)) {
                    Card old = cards.get(term);
                    defToTerm.remove(old.definition);
                }

                Card c = new Card(term, def, errors);
                cards.put(term, c);
                defToTerm.put(def, term);

                loaded++;
            }
        } catch (IOException ignored) {
        }

        out(loaded + " cards have been loaded.");
    }

    private static void exportCards() {
        out("File name:");
        String fileName = in();

        try (PrintWriter outFile = new PrintWriter(new FileWriter(fileName))) {
            for (Card c : cards.values()) {
                outFile.println(c.term + "\t" + c.definition + "\t" + c.errors);
            }
        } catch (IOException ignored) {
        }

        out(cards.size() + " cards have been saved.");
    }

    private static void askCards() {
        out("How many times to ask?");
        int times = Integer.parseInt(in());

        List<String> terms = new ArrayList<>(cards.keySet());

        for (int i = 0; i < times; i++) {
            String term = terms.get(random.nextInt(terms.size()));
            Card c = cards.get(term);

            out("Print the definition of \"" + term + "\":");
            String answer = in();

            if (answer.equals(c.definition)) {
                out("Correct!");
            } else {
                c.errors++;

                if (defToTerm.containsKey(answer)) {
                    String otherTerm = defToTerm.get(answer);
                    out("Wrong. The right answer is \"" + c.definition
                            + "\", but your definition is correct for \"" + otherTerm + "\" card.");
                } else {
                    out("Wrong. The right answer is \"" + c.definition + "\".");
                }
            }
        }
    }

    private static void hardestCard() {
        int max = 0;
        for (Card c : cards.values()) {
            if (c.errors > max) max = c.errors;
        }

        if (max == 0) {
            out("There are no cards with errors.");
            return;
        }

        List<String> hardest = new ArrayList<>();
        for (Card c : cards.values()) {
            if (c.errors == max) hardest.add(c.term);
        }

        if (hardest.size() == 1) {
            out("The hardest card is \"" + hardest.get(0) + "\". You have " + max + " errors answering it.");
        } else {
            String joined = "\"" + String.join("\", \"", hardest) + "\"";
            out("The hardest cards are " + joined + ". You have " + max + " errors answering them.");
        }
    }

    private static void resetStats() {
        for (Card c : cards.values()) {
            c.errors = 0;
        }
        out("Card statistics have been reset.");
    }

    private static void saveLog() {
        out("File name:");
        String fileName = in();

        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (String line : log) {
                pw.println(line);
            }
        } catch (IOException ignored) {
        }

        out("The log has been saved.");
    }

    // --- logging helpers (must log BOTH output and user input) ---
    private static void out(String s) {
        System.out.println(s);
        log.add(s);
    }

    private static String in() {
        String s = scanner.nextLine();
        log.add(s);
        return s;
    }
}
