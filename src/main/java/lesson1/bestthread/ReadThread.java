package lesson1.bestthread;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadThread extends Thread {
    private File file;
    public String word;

    public ReadThread(String threadName, File file, String word) {
        super(threadName);
        this.file = file;
        this.word = word.trim();
    }

    public void run() {
        System.out.println("Thread " + currentThread().getName() + ": started");
        String text = "";
        Path path = Paths.get("temp", "Story.txt");

        /*try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                text += line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
//            List<String> lines = Files.readAllLines(path);

            byte[] bytes = Files.readAllBytes(path);

            text = new String(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile(String.format(" %s ", word));
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        System.out.println("Thread " + currentThread().getName() + ": " + count);
        System.out.println("Thread " + currentThread().getName() + ": ended");
    }
}

/*
* String str = Hello;
* i = 0: Hello + 0 -> str = Hello0, Hello
* i = 1: Hello0 + 1 -> str = Hello01, Hello0
*
* */
