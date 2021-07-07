package lesson0.humanity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Address {
    @Getter
    private String country;
    @Getter
    private String city;
    @Getter
    private String street;
    @Getter
    private int numberOfHome;
}
