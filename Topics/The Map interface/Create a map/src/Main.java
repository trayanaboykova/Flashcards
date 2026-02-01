import java.util.*;

class Main {
    private static Map<String, Integer> createStatuses() {
        Map<String, Integer> map = new HashMap<>();
        map.put("SUCCESS", 0);
        map.put("FAIL", 1);
        map.put("WARN", 2);
        return map;
    }

    // do not change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> map = createStatuses();
        String status = scanner.next();
        int result = map.getOrDefault(status, -1);
        System.out.println(result);
    }
}