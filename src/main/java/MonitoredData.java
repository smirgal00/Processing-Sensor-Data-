import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
        String out;

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        out = dateFormat.format(startTime) + " " + dateFormat.format(endTime) + " " + activityLabel;

        return out;
    }

    public Integer getStartDay() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String[] date = dateFormat.format(startTime).split(" ");
        String[] dayMonth = date[0].split("-");

        return 31 * Integer.parseInt(dayMonth[0]) + Integer.parseInt(dayMonth[1]);
    }

    public Integer getEndDay() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String[] date = dateFormat.format(endTime).split(" ");
        String[] dayMonth = date[0].split("-");

        return 31 * Integer.parseInt(dayMonth[0]) + Integer.parseInt(dayMonth[1]);
    }

    public String getActivityLabel() {
        return activityLabel;
    }

    public Integer getDay() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String[] date = dateFormat.format(startTime).split(" ");
        String[] dayMonth = date[0].split("-");

        return Integer.parseInt(dayMonth[0]);
    }
}
