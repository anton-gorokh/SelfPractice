package lesson0.maptask;

import java.util.*;
import java.util.stream.Collectors;
/* Сущность Man(имя, фамилия, возраст, количество детей, Адрес), Адрес(страна, город, улица, номер дома)

Использовать коллекцию LinkedList. Действия для класса Адрес делать из класса Man.

 - Вывести информацию о всех людях.

 - Вывести информацию о всех адресах.

 - Вывести firstName, lastName, countOfChildren, когда возраст более или равно 20 и отсортировать по firstName.

 - Изменить firstName = 'John', lastName = 'Kennedi', countOfChildren = 3, когда country == 'US' (or another country).

 - Вывести firstName, lastName, nameOfStreet, когда country == 'Canada' AND numberOfHome == 3 OR age >= 25";

 - Сгруппировать людей по количеству детей.

 - Сгруппировать людей по количеству детей и возрасту.

 - Сгруппировать людей по городу и названию улицы.

 - Сгруппировать людей по городу и названию улицы и вывести количество адресов, где количество людей больше 4. */

public class Main {
    public static void main(String[] args) {
        List<Man> humanity = new LinkedList<>();
        humanity.add(new Man("Santa", "Barbara", 5, 100, new Address("US", "New York", "SweetStreet", 3)));
        humanity.add(new Man("Boris", "Britva", 3, 47, new Address("Canada", "NoImagination", "GoodStreet", 3)));
        humanity.add(new Man("Anton", "Gorokh", 2, 17, new Address("Ukraine", "Odessa", "Lollypop", 88)));
        humanity.add(new Man("Max", "Shtirlis", 0, 37, new Address("USSR", "SunCity", "GoodStreet", 37)));
        humanity.add(new Man("Petr", "Petrovi4", 0, 37, new Address("USA", "SunCity", "GoodStreet", 13)));
        humanity.add(new Man("Vasya", "Vasilievi4", 0, 37, new Address("UK", "SunCity", "GoodStreet", 27)));
        humanity.add(new Man("Noname", "Nonamovi4", 0, 37, new Address("USSR", "SunCity", "GoodStreet", 19)));
        humanity.add(new Man("Random", "Person", 0, 37, new Address("UK", "SunCity", "GoodStreet", 27)));
        humanity.add(new Man("John", "Cena", 3, 20, new Address("United States of America", "Chicago", "Sunny", 15)));
        humanity.add(new Man("Noname", "Nonamovi4", 0, 37, new Address("USSR", "NoWeatherCity", "GoodStreet", 19)));
        humanity.add(new Man("Noname", "Nonamovi4", 0, 37, new Address("USSR", "NoWeatherCity", "GoodStreet", 19)));
        humanity.add(new Man("Noname", "Nonamovi4", 0, 37, new Address("USSR", "NoWeatherCity", "GoodStreet", 19)));
        humanity.add(new Man("Noname", "Nonamovi4", 0, 37, new Address("USSR", "NoWeatherCity", "GoodStreet", 19)));
        humanity.add(new Man("Noname", "Nonamovi4", 0, 37, new Address("USSR", "NoWeatherCity", "GoodStreet", 19)));

        humanity.stream().filter(x -> {
            System.out.println(x);
            return x.getAge() >= 20;
        }).sorted(new Comparator<Man>() {
            @Override
            public int compare(Man o1, Man o2) {
                return o1.getSurname().compareTo(o2.getSurname());
            }
        }).forEach(x -> System.out.printf("%s %s %d \n", x.getSurname(), x.getName(), x.getChildren()));
        humanity.stream().filter(x -> x.getAddress().getCountry().equals("US")).forEach(x -> {
            x.setName("John");
            x.setSurname("Kennedi");
            x.setChildren(3);
        });

        System.out.println();
        humanity.stream().filter(x -> x.getAddress().getCountry().equals("Canada") && (x.getAge() > 25 || x.getAddress().getNumberOfHome() == 3))
                .forEach(x -> System.out.printf("%s %s %s \n", x.getName(), x.getSurname(), x.getAddress().getStreet()));
        System.out.println();

        Map<Integer, List<Man>> groupByKids = humanity.stream().collect(Collectors.groupingBy(Man::getChildren));

        Map<Integer, Map<Integer, List<Man>>> groupByKidsAndAge = humanity.stream()
                .collect(Collectors.groupingBy(Man::getChildren, Collectors.groupingBy(Man::getAge)));

        Map<String, Map<String, List<Man>>> groupByCityAndStreet = humanity.stream()
                .collect(Collectors.groupingBy(x -> x.getAddress().getCity(), Collectors.groupingBy(x -> x.getAddress().getStreet())));

        System.out.println(groupByCityAndStreet.entrySet().stream().mapToInt(x -> (int) x.getValue().entrySet().stream().filter(j -> j.getValue().size() > 4).count()).sum());
    }
}
