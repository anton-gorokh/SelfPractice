package lesson0.pricemanager;

import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        PriceExplorerBuilder priceExplorerBuilder = new PriceExplorerBuilder();
        List<Integer> price = Arrays.asList(3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25);
        Map<String, List<Integer>> prices = new HashMap<>();
        prices.put("prod1", price);
        prices.put("prod2", price);
        prices.put("prod3", price);
        PriceExplorer priceExplorer = priceExplorerBuilder.getPriceExplorer(prices);
        try {
            System.out.println(priceExplorer.getPrice("Feb", "prod1"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
