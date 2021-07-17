package lesson1.fabric;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Cycle extends Thread {
    private Map<Stage, Integer> stages = new HashMap<>();

    private boolean isActive = true;

    private Stage stage;

    private Worker worker;

    private Product product;

    public Cycle (Worker worker, Product product) {
        this.worker = worker;
        this.product = product;
    }

    public boolean addStage(Stage stage, int seconds) {
        if (stages.containsKey(stage)) {
            return false;
        } else {
            stages.put(stage, seconds);
            return true;
        }
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": \"" + this.worker.getName() + "\" started work on product \"" + this.product.getName() + "\".");
        stages.entrySet().forEach(x -> {
            stage = x.getKey();

            try {
                Thread.sleep(x.getValue() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        isActive = false;
        System.out.println(Thread.currentThread().getName() + ": product \"" + this.product.getName() + "\" is done by worker \"" + this.worker.getName() + "\".");
    }
}
