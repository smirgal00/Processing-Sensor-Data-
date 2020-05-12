package Lambdas;

import java.util.Map;

@FunctionalInterface
public interface Task4Util {

    void add(Integer day, Map<Integer, Map<String, Integer>> map);

    default StringBuilder print(Map<Integer, Map<String, Integer>> map) {
        StringBuilder sb = new StringBuilder();
        map.forEach((key, value) -> {
            sb.append("Day ").append(key).append(": ").append("\n");
            value.forEach((string, nr) -> {
                sb.append("Activity ").append(string).append(" occurred ").append(nr).append(" times!").append("\n");
            });
        });

        return sb;
    }
}
