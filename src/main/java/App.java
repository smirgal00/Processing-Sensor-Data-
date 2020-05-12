public class App {

    public static void main(String[] args) {
        DataOperations dataOperations = new DataOperations("activities.txt");
        dataOperations.printData();
        System.out.println(dataOperations.countDistinctDays());
        dataOperations.printDistinctActivities();
        dataOperations.printActivitiesPerDay();
        dataOperations.printDurations();
    }
}
