package FileIO;

import javax.management.monitor.MonitorSettingException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import Logic.*;

public class FileOperation {
    private final String path;

    public FileOperation(String path) {
        this.path = path;
    }


    public void readFile(List<MonitoredData> monitoredData) throws IOException {
        File file = new File(this.path);
        Stream<String> fileText = Files.lines(Paths.get(file.getPath()));

        fileText.map(line -> line.split("\t\t"))
                .forEach(parsed -> monitoredData.add(new MonitoredData(parsed)));

    }

    public void writeToFile(String toWrite, String fileName) throws IOException {
        FileWriter file = new FileWriter(fileName);
        BufferedWriter wr = new BufferedWriter(file);

        wr.write(toWrite);
        wr.close();
    }
}
