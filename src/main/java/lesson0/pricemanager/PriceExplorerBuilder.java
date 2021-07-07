package lesson0.pricemanager;

import java.util.List;
import java.util.Map;

public class PriceExplorerBuilder {
    public PriceExplorer getPriceExplorer (Map<String, List<Integer>> prices) {
        return new PriceExplorer(prices);
    }
}
