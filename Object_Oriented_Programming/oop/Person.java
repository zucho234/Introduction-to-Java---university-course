package i2jp.oop;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Person {
	private static final Logger log = LogManager.getLogger(Person.class);
	
	private static final int ID_LEN = 7;
	private static final AtomicLong COUNTER = new AtomicLong(1L);
	  private static final DateTimeFormatter DMY = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private Gender gender;
	private final String id;
	
	public Person (String firstName, String lastName, String birthDate, Gender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = LocalDate.parse(birthDate, DMY);
		this.gender = gender;
		this.id = toBase36Padded(COUNTER.getAndIncrement(), ID_LEN);
		
		log.debug("Created Person id={} name={} {} birth={} gender={}",
		        id, firstName, lastName, birthDate, gender);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public LocalDate getBirthDay() {
		return birthDate;
	}
	public Gender getGender() {
		return gender;
	}
	public String getId() {
		return id;
	}
	
	public void setFirstName(String firstName) {
		if (firstName != null) {
			this.firstName = firstName;
		}
	}
	public void setLastName(String lastName) {
		if (lastName != null) {
			this.lastName = lastName;
		}
	}
	public void setBirthDate(String birthDate) {
		if (birthDate != null) {
			this.birthDate = LocalDate.parse(birthDate, DMY);
		}
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
	public int getAgeYears() {
		int age = Period.between(birthDate, LocalDate.now()).getYears();
		log.trace("Computed age for {} {}: {} years", firstName, lastName, age);
		return age;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName + " (" + birthDate.format(DMY) + ", " + gender + ")";
	}
	
	
	private static String toBase36Padded(long n, int len) {
	    String s = Long.toString(n, 36).toUpperCase();
	    int pad = len - s.length();
	    String result = (pad > 0 ? "0".repeat(pad) : "") + s;
	    log.trace("Generated base36 id={} from n={}", result, n);
	    return result;
	  }
}
