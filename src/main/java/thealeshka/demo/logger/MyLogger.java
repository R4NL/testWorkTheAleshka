package thealeshka.demo.logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyLogger {
    private File file;

    public MyLogger() {
        file = new File("logs.txt");
        check();
    }

    private void check() {
        if (file.isDirectory()) {
            throw new IllegalArgumentException("File is Directory");
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (file.exists()) {
            try {
                file.delete();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void addLog(String data) {
        try {
            String str = new String(Files.readAllBytes(Paths.get(file.getPath())));
            str += System.lineSeparator() + data;
            Files.write(Paths.get(file.getPath()), str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
