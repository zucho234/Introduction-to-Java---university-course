import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Birthday {
	public static void main(String[] args) {
		LocalDate birthday = LocalDate.of(2004,5,6);
		LocalDate today = LocalDate.now();
		LocalDate birthdayThisYear = LocalDate.of(today.getYear(),5,6);
		

		long daysSBirth = ChronoUnit.DAYS.between(birthday, today);
		long daysTBirth = ChronoUnit.DAYS.between(today, birthdayThisYear);
		
		DateTimeFormatter FoT = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		System.out.println("Today is: " + today.format(FoT) + " - since the birthday on " + birthday.format(FoT) + ", " + daysSBirth + " days have passed.");
		System.out.println("Days to birthday this year: " + daysTBirth);
	}

}
