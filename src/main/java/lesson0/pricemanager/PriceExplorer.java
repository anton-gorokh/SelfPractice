package lesson0.pricemanager;

import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PriceExplorer {
    @Getter
    private Map<String, Map<Integer, Integer>> prices;

    public PriceExplorer(Map<String, List<Integer>> prices) {
        Map<String, Map<Integer, Integer>> p = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entry : prices.entrySet()) {
            Map<Integer, Integer> monthPriceMap = new HashMap<>();
            for(int i = 0; i < entry.getValue().size(); i++) {
                monthPriceMap.put(i, entry.getValue().get(i));
            }
            p.put(entry.getKey(), monthPriceMap);
        }
        this.prices = p;
    }

    public int getPrice(String month, String productKey) throws ParseException {
        Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int m = calendar.get(Calendar.MONTH);

        return prices.get(productKey).get(m);
    }
}
