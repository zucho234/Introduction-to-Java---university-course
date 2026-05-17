import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.DayOfWeek;
import java.time.Period;

public class BirthdayInfo {

	public static void main(String[] args) {
		
		if(args.length <3){
			System.out.println("Podałeś błędna datę");
			return;
		}
		
		int Year = Integer.parseInt(args[0]);
		int Month = Integer.parseInt(args[1]);
		int Day = Integer.parseInt(args[2]);
		
		LocalDate birthday = LocalDate.of(Year,Month,Day);
		DateTimeFormatter FoT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		System.out.println("Birthday:" + birthday.format(FoT));
		
		DayOfWeek Dbirthday = birthday.getDayOfWeek();
		System.out.println("Day of the week: " + Dbirthday);
		
		boolean isLeap = (Year % 4 == 0 && Year % 100 != 0) || (Year % 400 == 0);
		if(isLeap == true) {
			System.out.println(Year + " is a leap year!");
		}
		
		switch (Month) {
	    case 1:
	        if (Day < 20) {
	            System.out.println("Western zodiac: Capricorn");
	        } else {
	        	System.out.println("Western zodiac: Aquarius");
	        }
	        break;
	    case 2:
	        if (Day < 19) {
	        	System.out.println("Western zodiac: Aquarius");
	        } else {
	        	System.out.println("Western zodiac: Pisces");
	        }
	        break;
	    case 3:
	        if (Day < 21) {
	        	System.out.println("Western zodiac: Pisces");
	        } else {
	        	System.out.println("Western zodiac: Aries");
	        }
	        break;
	    case 4:
	        if (Day < 20) {
	        	System.out.println("Western zodiac: Aries");
	        } else {
	        	System.out.println("Western zodiac: Taurus");
	        }
	        break;
	    case 5:
	        if (Day < 21) {
	        	System.out.println("Western zodiac: Taurus");
	        } else {
	        	System.out.println("Western zodiac: Gemini");
	        }
	        break;
	    case 6:
	        if (Day < 21) {
	        	System.out.println("Western zodiac: Gemini");
	        } else {
	        	System.out.println("Western zodiac: Cancer");
	        }
	        break;
	    case 7:
	        if (Day < 23) {
	        	System.out.println("Western zodiac: Cancer");
	        } else {
	        	System.out.println("Western zodiac: Leo");
	        }
	        break;
	    case 8:
	        if (Day < 23) {
	        	System.out.println("Western zodiac: Leo");
	        } else {
	        	System.out.println("Western zodiac: Virgo");
	        }
	        break;
	    case 9:
	        if (Day < 23) {
	        	System.out.println("Western zodiac: Virgo");
	        } else {
	        	System.out.println("Western zodiac: Libra");
	        }
	        break;
	    case 10:
	        if (Day < 23) {
	        	System.out.println("Western zodiac: Libra");
	        } else {
	        	System.out.println("Western zodiac: Scorpio");
	        }
	        break;
	    case 11:
	        if (Day < 22) {
	        	System.out.println("Western zodiac: Scorpio");
	        } else {
	        	System.out.println("Western zodiac: Sagittarius");
	        }
	        break;
	    case 12:
	        if (Day < 22) {
	        	System.out.println("Western zodiac: Sagittarius");
	        } else {
	        	System.out.println("Western zodiac: Capricorn");
	        }
	        break;
	        
		}
		
		
		int ChineseYear = Year;
		String[] animals = {
				"Rat","Ox","Tiger","Rabbit","Dragon","Snake",
				"Horse","Goat","Monkey","Rooster","Dog","Pig"
		};
		
		if(Month < 2 || (Month == 2 && Day < 4)) {
			ChineseYear --;
		}
		
		int ChineseZodiacIndex = ((Year - 1900) % 12);
		String zodiac = animals[ChineseZodiacIndex];
		
		System.out.println("Chinese zodiac: " + zodiac);
		
		LocalDate today = LocalDate.now();
		long daysSBirth = ChronoUnit.DAYS.between(birthday, today);
		System.out.println("\nToday is: " + today.format(FoT) + " - since the birthday on " + birthday.format(FoT) + ", " + daysSBirth + " days have passed.");
		
		Period Age = Period.between(birthday, today);
		System.out.println("Age today: " + Age.getYears() + " years, " + Age.getMonths() + " months, " + Age.getDays() + " days.");
		
		LocalDate birthdayThisYear = LocalDate.of(today.getYear(),5,6);
		long daysTBirth = ChronoUnit.DAYS.between(today, birthdayThisYear);
		System.out.println("Birthday is in " + daysTBirth + " days (this year).");
	}
}
