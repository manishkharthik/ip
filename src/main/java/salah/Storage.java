package salah;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final Path DATA_DIR = Paths.get("data");
    private static final Path DATA_FILE = DATA_DIR.resolve("duke.txt");

    // Save tasks upon close
    public static void save(List<Task> tasks) {
        try {
            Files.createDirectories(DATA_DIR); // ensure ./data exists
            try (FileWriter fw = new FileWriter(DATA_FILE.toFile());
                 BufferedWriter bw = new BufferedWriter(fw)) {
                for (Task t : tasks) {
                    bw.write(t.serialize());  // each Task must know how to serialize itself
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Loads task when application starts
    public static ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(DATA_FILE)) {
            return tasks; // first run, no file yet
        }
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE.toFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                Task t = Task.deserialize(line);
                if (t != null) tasks.add(t);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }
}