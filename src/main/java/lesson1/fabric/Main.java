package lesson1.fabric;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Stage, Integer> manufactureDuration = Map.of(Stage.PROCESSING, 1, Stage.MANUFACTURING, 2, Stage.DELIVERING, 1);

        Fabric fabric = new Fabric(manufactureDuration);

        fabric.addWorker(new Worker("John Lennon"));

        fabric.addOrder(new Product("1"));
        fabric.addOrder(new Product("2"));
        fabric.addOrder(new Product("3"));
        fabric.addOrder(new Product("4"));
        fabric.addOrder(new Product("5"));

        fabric.start();

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fabric.addWorker(new Worker("Elvis Presley"));

        for (int i = 0; i < 25; i++) {
            try {
                Thread.sleep(4 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            fabric.addOrder(new Product(i + "i"));
        }
    }
}
