import java.time.Duration;
import java.util.Map;

public interface Task5Util {

    void add(Map<String, Duration> map, MonitoredData data);

    default StringBuilder printDurations(Map<String, Duration> map) {
        StringBuilder sb = new StringBuilder();

        map.forEach((key, value) -> {
            sb.append("Activity ").append(key).append(" has a total duration of ").
                    append(parseDate(value.toString())).append("\n");
        });

        return sb;
    }

    default String parseDate(String string) {
        string = string.replace("PT", "");
        string = string.replace("H", "H:");
        string = string.replace("M", "M:");

        return string;
    }
}
