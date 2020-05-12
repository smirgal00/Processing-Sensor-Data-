import java.time.Duration;
import java.util.Map;

public interface Task5Util {

    void add(Map<String, Duration> map, MonitoredData data);

    default void printDurations(Map<String, Duration> map) {
        map.forEach((key, value) -> {
            System.out.println("Activity " + key + " has a total duration of " + parseDate(value.toString()));
        });
    }

    default String parseDate(String string) {
        string = string.replace("PT", "");
        string = string.replace("H", "H:");
        string = string.replace("M", "M:");

        return string;
    }
}
