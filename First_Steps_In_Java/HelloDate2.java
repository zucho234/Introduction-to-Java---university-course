import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class HelloDate2 {
  public static void main(String[] args) {
    LocalDateTime now = LocalDateTime.now();
    DayOfWeek today = LocalDateTime.now().getDayOfWeek();
    LocalDate start = LocalDate.of(2025, 1, 9);
    LocalDateTime nowPweek = now.plusDays(7);
    LocalDateTime nowPhoundred = now.plusDays(100);
    LocalDate dzis = LocalDate.now();
    LocalDate NewYear = LocalDate.of(2026, 12, 31);
    
    long days = ChronoUnit.DAYS.between(start, now);
    long daysToNYE = ChronoUnit.DAYS.between(dzis, NewYear);
    
    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
    System.out.println("Hello! Today is: "+ today +" " + now.format(fmt));
    System.out.println("Since october 1st have passed: " + days);
    System.out.println("Date in 7 days will be: " + nowPweek.format(fmt) + " and in 100 days will be: " + nowPhoundred.format(fmt));
    System.out.println("Days left until New Year's Eve: " + daysToNYE);
  }
}