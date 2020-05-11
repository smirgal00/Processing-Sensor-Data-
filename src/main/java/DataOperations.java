import java.io.IOException;
import java.util.*;

public class DataOperations {
    private final List<MonitoredData> monitoredData;
    private final Reading reading;
    Task3Util addLambda;
    Task4Util mapLambda;

    public DataOperations(String path) {
        monitoredData = new ArrayList<>();
        reading = new Reading(path);
        addLambda = (Map<String, Integer> map, String string) -> {
            if (map.containsKey(string)) {
                map.put(string, map.get(string) + 1);
            }
            else {
                map.put(string, 1);
            }
        };
        mapLambda = (Integer day, Map<Integer, Map<String, Integer>> map) -> {
            Map<String, Integer> subMap = new HashMap<>();

            monitoredData.forEach(line -> {
                if (line.getDay().equals(day)) {
                    addLambda.addOrUpdate(subMap, line.getActivityLabel());
                }
            });

            map.put(day, subMap);
        };

        try {
            reading.readFile(monitoredData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printData() {
        monitoredData.forEach(System.out::println);
    }

    public Integer countDistinctDays() {
        HashSet<Integer> days = new HashSet<>();

        monitoredData.forEach(line -> {
            days.add(line.getStartDay());
            days.add(line.getEndDay());
        });

        return days.size();
    }

    public Map<String, Integer> countDistinctActivities() {
        Map<String, Integer> activities = new HashMap<>();

        monitoredData.forEach(line -> {
            addLambda.addOrUpdate(activities, line.getActivityLabel());
        });

        return activities;
    }

    public void printDistinctActivities() {
        Map<String, Integer> map = countDistinctActivities();
        map.keySet().forEach(line -> System.out.println(line + " " + map.get(line)));
    }

    public Map<Integer, Map<String, Integer>> countActivityPerDay() {
        Map<Integer, Map<String, Integer>> map = new HashMap<>();

        monitoredData.forEach(line -> {
            mapLambda.add(line.getDay(), map);
        });

        return map;
    }

    public void printActivitiesPerDay() {
        Map<Integer, Map<String, Integer>> map = countActivityPerDay();

        mapLambda.print(map);
    }
}
