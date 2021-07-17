package lesson1.fabric;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Fabric extends Thread {
    public Map<Stage, Integer> manufactureDuration;

    private List<Worker> workers = new ArrayList<>();
    private List<Product> orders = new ArrayList<>();
    private List<Cycle> cycles = new ArrayList<>();

    public Fabric(Map<Stage, Integer> manufactureDuration) {
        this.manufactureDuration = manufactureDuration;
    }

    @Override
    public void run() {
        Thread busyness = new Thread(() -> {
            while (true) {
                synchronized (cycles) {
                    Iterator<Cycle> iterator = cycles.iterator();

                    while (iterator.hasNext()) {
                        Cycle cycle = iterator.next();

                        synchronized (workers) {
                            if (!cycle.isActive()) { //if cycle ended work -> make worker available, remove cycle
                                cycle.getWorker().setAvailable(true);
                                iterator.remove();
                                synchronized (this) {
                                    this.notifyAll();
                                }
                            }
                        }
                    }
                }
            }
        });
        busyness.start();

        while (true) {
            if (getAvailableWorkers().isEmpty()) {
                try {
                    synchronized (this) {
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            getAvailableWorkers().stream().limit(orders.size()).forEach(x -> {  //limit available workers by "orders" size; add cycle
                Cycle cycle = new Cycle(x, orders.remove(0));
                this.manufactureDuration.entrySet().forEach(i -> cycle.addStage(i.getKey(), i.getValue()));
                synchronized (cycles) {
                    cycles.add(cycle);
                }
                x.setAvailable(false);
                cycle.start();
            });
        }
    }

    public Stage getCurrentStage(Product product) {
        if (cycles.stream().anyMatch(x -> x.getProduct().equals(product))) {
            return cycles.stream().filter(x -> x.getProduct().equals(product)).findAny().get().getStage();
        } else { //if "cycles" doesn't contain this product -> this product is already made
            return Stage.READY;
        }
    }

    public Worker getCurrentWorker(Product product) {
        if (cycles.stream().anyMatch(x -> x.getProduct().equals(product))) {
            return cycles.stream().filter(x -> x.getProduct().equals(product)).findAny().get().getWorker();
        } else {
            return null; //can be changed by adding additional map with all made products
        }
    }

    public void addOrder(Product product) {
        orders.add(product);
    }

    public void addWorker(Worker worker) {
        synchronized (workers) {
            workers.add(worker);
            synchronized (this) {
                this.notifyAll();
            }
        }
    }

    private List<Worker> getAvailableWorkers() {
        synchronized (workers) {
            return this.workers.stream().filter(x -> x.isAvailable()).toList();
        }
    }
}
