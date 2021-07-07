package lesson0.countwords;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;

// Подсчитать количество слов в предложении с учетом повторений.

public class CountWords {
    public static String text = "Мама моет раму, раму мыла мама.";

    public static void main(String[] args) {
        int wordsRepeat;
        int wordsNoRepeat;

        String[] splitText = text.split(" ");
        for (int i = 0; i < splitText.length; i++) {
            splitText[i] = splitText[i].replaceAll("[^a-zA-ZА-яЁё]+", "").toUpperCase(Locale.ROOT);
        }
        wordsRepeat = splitText.length;

        HashSet<String> splitTextSet = new HashSet<>(Arrays.asList(splitText));
        splitText = splitTextSet.toArray(new String[0]);
        wordsNoRepeat = splitText.length;

        System.out.println(wordsRepeat);
        System.out.println(wordsNoRepeat);
    }
}
