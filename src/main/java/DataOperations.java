import java.io.IOException;
import java.time.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DataOperations {
    private final List<MonitoredData> monitoredData;
    private final FileOperation fileOperation;
    Task3Util addLambda;
    Task4Util mapLambda;
    Task5Util timeLambda;
    Task6Util percentageLambda;

    public DataOperations(String path) {
        monitoredData = new ArrayList<>();
        fileOperation = new FileOperation(path);
        addLambda = (Map<String, Integer> map, String string) -> {
            if (map.containsKey(string)) {
                map.put(string, map.get(string) + 1);
            } else {
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
        timeLambda = (Map<String, Duration> map, MonitoredData data) -> {
            LocalDateTime start = LocalDateTime.ofInstant(data.getStartTime().toInstant(), ZoneId.systemDefault());
            LocalDateTime end = LocalDateTime.ofInstant(data.getEndTime().toInstant(), ZoneId.systemDefault());

            Duration duration = Duration.between(start, end);

            if (map.containsKey(data.getActivityLabel())) {
                map.put(data.getActivityLabel(), map.get(data.getActivityLabel()).plus(duration));
            } else {
                map.put(data.getActivityLabel(), duration);
            }
        };
        percentageLambda = (String elem) -> {
            AtomicInteger count = new AtomicInteger();
            monitoredData.forEach(data -> {
                if (data.getActivityLabel().equals(elem)) {
                    LocalDateTime start = LocalDateTime.ofInstant(data.getStartTime().toInstant(), ZoneId.systemDefault());
                    LocalDateTime end = LocalDateTime.ofInstant(data.getEndTime().toInstant(), ZoneId.systemDefault());

                    Duration duration = Duration.between(start, end);

                    if (duration.toSeconds() < 300) {
                        count.addAndGet(1);
                    }
                }
            });

            return count;

        };

        try {
            fileOperation.readFile(monitoredData);
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

    private Map<String, Integer> countDistinctActivities() {
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

    private Map<Integer, Map<String, Integer>> countActivityPerDay() {
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

    private Map<String, Duration> computeDuration() {
        Map<String, Duration> map = new HashMap<>();

        monitoredData.forEach(line -> {
            timeLambda.add(map, line);
        });

        return map;
    }

    public void printDurations() {
        timeLambda.printDurations(computeDuration());
    }

    private List<String> filterActivities() {
        List<String> names = new ArrayList<>();
        Map<String, Integer> map = countDistinctActivities();

        map.forEach((elem, value) -> {
            int cnt = percentageLambda.process(elem).get();

            double percent = value * 0.9;

            if (cnt > percent) {
                names.add(elem);
            }
        });

        return names;
    }

    public void printFilteredActivities() {
        percentageLambda.printActivities(filterActivities());
    }
}
