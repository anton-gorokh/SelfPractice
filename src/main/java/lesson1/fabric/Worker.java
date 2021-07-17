package lesson1.fabric;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Worker {
    private String name;

    @Setter
    private boolean isAvailable = true;

    public Worker (String name) {
        this.name = name;
    }
}
