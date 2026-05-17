import java.time.LocalDate;
import java.time.DayOfWeek;


public class Day {

	public static void main(String[] args) {
		
	DayOfWeek today = LocalDate.now().getDayOfWeek();
	
	
	System.out.println("Today is: " + today);
	}
}
