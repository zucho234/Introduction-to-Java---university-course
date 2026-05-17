import java.time.LocalDate;
import java.time.DayOfWeek;

public class Weekend {

	public static void main(String[] args) {

		LocalDate today = LocalDate.now();
		DayOfWeek todayD = today.getDayOfWeek();
		
		if (todayD == DayOfWeek.SATURDAY || todayD == DayOfWeek.SUNDAY)
		{
			System.out.println("Weekend!");
		}
		else
		{
			int DayUSaturday = DayOfWeek.SATURDAY.getValue() - todayD.getValue();
			System.out.println(DayUSaturday + "day/s left until Saturday");
		}
		
	}

}
