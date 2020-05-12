package Logic;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        DataOperations dataOperations = new DataOperations("activities.txt");
        dataOperations.printData();
        dataOperations.printDistinctDays();
        dataOperations.printDistinctActivities();
        dataOperations.printActivitiesPerDay();
        dataOperations.printDurations();
        dataOperations.printFilteredActivities();
    }
}
