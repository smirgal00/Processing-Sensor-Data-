import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonitoredData {

    private Date startTime;
    private Date endTime;
    private String activityLabel;

    public MonitoredData(String[] parsed) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

        try {
            startTime = formatter.parse(parsed[0]);
            endTime = formatter.parse(parsed[1]);
            activityLabel = parsed[2];
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        String out = "";

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        out = dateFormat.format(startTime) + " " + dateFormat.format(endTime) + " " + activityLabel;

        return out;
    }

    public static void main(String[] args) {

        List<MonitoredData> monitoredData = new ArrayList<>();

        Reading pula = new Reading("activities.txt");
        try {
            pula.readFile(monitoredData);
        } catch (IOException e) {
            e.printStackTrace();
        }

        monitoredData.forEach(data -> System.out.println(data.toString()));
    }
}
