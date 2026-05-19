package i2jp.oop;

import i2jp.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



public class DemoSortStats {
  public static void main(String[] args) {
    Group g = new Group("G1", "Java Monday");

    Student a = new Student("Anna", "Kowalska", "15.03.2003", Gender.FEMALE, "S1");
    a.addGrade(5.0); a.addGrade(4.5); a.addGrade(4.5);

    Student p = new Student("Piotr", "Nowak", "02.09.2002", Gender.MALE, "S2");
    p.addGrade(4.0); p.addGrade(4.0); p.addGrade(4.0);

    Student j = new Student("Julia", "Mazur", "01.01.2004", Gender.FEMALE, "S3");
    j.addGrade(3.5); j.addGrade(3.5);

    g.addStudent(a); g.addStudent(p); g.addStudent(j);

    // Comparators
    Comparator<Student> byName = Comparator
      .comparing(Student::getLastName)
      .thenComparing(Student::getFirstName);

    Comparator<Student> byAvgDesc = Comparator
      .comparingDouble((Student s) -> s.average().orElse(0.0))
      .reversed();

    Comparator<Student>byAgeAsc = Comparator
      .comparingInt(Student::getAgeYears);

    // Top-3 students by average
    System.out.println("TOP-3 by average:");
    g.getMembers().stream()
      .sorted(byAvgDesc)
      .limit(3)
      .forEachOrdered(s ->
        System.out.printf("%d) %s %s avg=%.2f%n",
          ordinal(s, g.getMembers(), byAvgDesc),
          s.getFirstName(), s.getLastName(),
          s.average().orElse(0.0))
      );

    // Group average and median
    List<Double> avgs = g.getMembers().stream()
      .map(s -> s.average().orElse(0.0))
      .sorted()
      .collect(Collectors.toList());

    double groupAvg = avgs.stream()
      .mapToDouble(Double::doubleValue)
      .average()
      .orElse(Double.NaN);

    double median = avgs.isEmpty() ? Double.NaN :
      (avgs.size() % 2 == 1
        ? avgs.get(avgs.size() / 2)
        : (avgs.get(avgs.size() / 2 - 1) + avgs.get(avgs.size() / 2)) / 2.0);

    System.out.printf("Group average=%.2f, median=%.2f%n", groupAvg, median);
  }

  /** Computes the ordinal rank of a student within the collection based on a given comparator. */
  private static int ordinal(Student target, Collection<Student> all, Comparator<Student> cmp) {
    return 1 + new ArrayList<Student>(all).stream()
      .sorted(cmp)
      .collect(Collectors.toList())
      //.toList()
      .indexOf(target);
  }
}

