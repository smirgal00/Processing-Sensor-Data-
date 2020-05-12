package Lambdas;

import java.util.Map;

@FunctionalInterface
public interface Task3Util {

    void addOrUpdate(Map<String, Integer> map, String string);

    default StringBuilder createString(Map<String, Integer> map) {
        StringBuilder sb = new StringBuilder();
        map.keySet().forEach(line -> sb.append(line).append(" ").append(map.get(line)).append("\n"));

        return sb;
    }
}
