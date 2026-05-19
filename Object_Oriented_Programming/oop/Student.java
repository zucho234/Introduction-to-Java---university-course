package i2jp.oop;

import java.util.*;
import java.util.stream.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Student extends Person {
	private static final Logger log = LogManager.getLogger(Student.class);
	
	private final String indexNumber;
	private final List<Double> grades = new ArrayList<>();
	private static final Set<Double> ALLOWED = Set.of(2.0, 3.0, 3.5, 4.0, 4.5, 5.0);
	
	public Student(String firstName, String lastName, String birthDate, Gender gender, String indexNumber) {
		super(firstName, lastName, birthDate, gender);
		this.indexNumber = indexNumber;
		
		log.info("New Student created: index={} personId={}", indexNumber, getId());
	}
	
	public String getIndexNumber() {
		return indexNumber;
	}
	
	public void addGrade(double g) {
	    if (!ALLOWED.contains(g)) {
	      throw new IllegalArgumentException("Grade not allowed: " + g);
	    }
	    grades.add(g);
	    
	    log.debug("Added grade={} to student index={} (now {} grades)",
	            g, indexNumber, grades.size());
	}
	
	public OptionalDouble average() {
		OptionalDouble avg = grades.stream().mapToDouble(Double::doubleValue).average();
		log.trace("Computed average for index={}: {}", indexNumber,
		        avg.isPresent() ? avg.getAsDouble() : "no grades");
	    return avg;
	}
	
	public List<Double> getGrades() {
	    return Collections.unmodifiableList(grades);
	  }

	@Override
	public String toString() {
		return super.toString() + " [index=" + indexNumber + "]";
	}
}
