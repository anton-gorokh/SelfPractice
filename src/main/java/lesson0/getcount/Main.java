package lesson0.getcount;

/* Implement a static method getCount(...) that takes an array of integers as the first parameter.
The second parameter is a functional interface that works with integers and defines a some condition.
The method should return the count of elements that satisfy the condition, defined by the second argument. */

import java.util.Arrays;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println(getCount(array, x -> x > 5));
    }

    public static long getCount(int[] array, Predicate<Integer> predicate) {
        return Arrays.stream(array).filter(x -> predicate.test(x)).count();
    }
}
