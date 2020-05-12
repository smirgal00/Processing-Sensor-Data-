import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface Task6Util {

    AtomicInteger process(String elem);

    default void printActivities(List<String> list) {
        System.out.println(list.toString());
    }
}
