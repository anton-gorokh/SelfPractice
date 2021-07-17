package lesson1.bestthread;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        File file = new File("E:\\IntelliJ IDEA\\Projects\\1337\\src\\main\\java\\lesson1\\fabric\\Story.txt");

        for (int i = 0; i < 5; i++) {
            Thread thread = new ReadThread("Thread " + i, file, "time");
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
