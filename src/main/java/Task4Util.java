import java.util.Map;

@FunctionalInterface
public interface Task4Util {

    void add(Integer day, Map<Integer, Map<String, Integer>> map);

    default void print(Map<Integer, Map<String, Integer>> map) {
        map.forEach((key, value) -> {
            System.out.println("Day " + key + ": ");
            value.forEach((string, nr) -> {
                System.out.println("Activity " + string + " occurred " + nr + " times!");
            });
        });
    }
}
