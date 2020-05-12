import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface Task6Util {

    AtomicInteger process(String elem);

    default StringBuilder printActivities(List<String> list) {
        StringBuilder sb = new StringBuilder();

        list.forEach(string -> sb.append(string).append(" ").append("\n"));

        return sb;
    }
}
