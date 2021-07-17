package lesson0.softservetasks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* Create a Map<String, Stream<String>> phoneNumbers(List<Stream<String>> list) method of the MyUtils class to build a Map of all phone numbers.
        The key of Map is code of network and value contains sorted list of phones.
        Remove all spaces, brakets and dashes from phone numbers.
        For example, for a given
        [["093 987 65 43", "(050)1234567", "12-345"], ["067-21-436-57", "050-2345678", "0939182736", "224-19-28"], ["(093)-11-22-334", "044 435-62-18", "721-73-45"]]
        you should get
        {"050"=["1234567", "2345678"], "067"=["2143657"], "093"=["1122334", "9182736", "9876543"], "044"=["4356218"], "loc"=["2241928", "7217345"], "err"=["12345"]} */

public class PhoneManager {
    public static void main(String[] args) {
        PhoneManager phoneManager = new PhoneManager();
        Map<String, Stream<String>> phoneNumbers = phoneManager.phoneNumbers(List.of( //stream has already been operated upon or closed
                Stream.of("093 987 65 43", "(050)1234567", "12-345"),
                Stream.of("067-21-436-57", "050-2345678", "0939182736", "224-19-28"),
            Stream.of("(093)-11-22-334", "044 435-62-18", "721-73-45")));

        System.out.println(phoneNumbers);
    }

    public Map<String, Stream<String>> phoneNumbers(List<Stream<String>> list) {
        if (list.stream().flatMap(Function.identity()).count() == 0) {
            throw new NullPointerException();
        }

        Map<String, List<String>> tmp = list.stream()
                .flatMap(Function.identity())
                .peek(x -> x.replaceAll("[^0-9]", ""))
                .collect(Collectors.groupingBy(x -> {
                    if (x.length() == 10) {
                        return x.substring(0, 2);
                    } else if (x.length() == 7) {
                        return "loc";
                    } else {
                        return "err";
                    }
                }, Collectors.toList()));

        Map<String, Stream<String>> phoneNumbers = new HashMap<>();
        tmp.entrySet().stream().forEach(x -> phoneNumbers.put(x.getKey(), x.getValue().stream()));
        return phoneNumbers;
    }
}
