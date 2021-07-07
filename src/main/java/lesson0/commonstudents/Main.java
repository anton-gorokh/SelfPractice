package lesson0.commonstudents;

// Create the commonStudents() method to return a HashSet of common elements of two student lists

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student petr = new Student(1, "Petr");
        Student ivan = new Student(2, "Ivan");
        Student vasya = new Student(1, "Vasya");
        Student nikita = new Student(1, "Nikita");
        Student dima = new Student(1, "Dima");

        List<Student> students = Arrays.asList(petr, ivan, nikita);
        List<Student> students1 = Arrays.asList(ivan, vasya, nikita, dima);

        HashSet<Student> common = commonStudents(students, students1);
        common.forEach(x -> System.out.println(x));
    }

    public static HashSet<Student> commonStudents(List<Student> students, List<Student> students1) {
        students.retainAll(students1);
        return new HashSet<Student>(students);
    }
}
