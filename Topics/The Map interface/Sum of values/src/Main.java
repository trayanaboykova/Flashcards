import java.util.*;

class Main {
    private static int sum(Map<String, Integer> map) {
        int total = 0;
        for (int value : map.values()) {
            total += value;
        }
        return total;
    }

    // do not change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> m = new HashMap<>();
        int size = scanner.nextInt();
        for (int i = 0; i < size; ++i) {
            String key = scanner.next();
            int value = scanner.nextInt();
            m.put(key, value);
        }
        int result = sum(Map.copyOf(m));
        System.out.println(result);
    }
}