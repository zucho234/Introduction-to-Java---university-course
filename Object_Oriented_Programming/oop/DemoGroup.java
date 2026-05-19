package i2jp.oop;

import java.io.IOException;
import java.nio.file.Path;

public class DemoGroup {
	public static void main(String[] args) throws CsvFormatException {
		Group g1 = new Group("G1", "Java Monday");
	    Student a = new Student("Anna", "Kowalska", "15.03.2003", Gender.FEMALE, "S12345");
	    Student b = new Student("Piotr", "Nowak", "02.09.2002", Gender.MALE, "S99999");

	    System.out.println(g1.addStudent(a) ? "Added Anna to G1" : "Failed Anna");
	    System.out.println(g1.addStudent(b) ? "Added Piotr to G1" : "Failed Piotr");

	    Group g2 = new Group("G2", "Java Friday");
	    System.out.println(g2.addStudent(b) ? "Moved Piotr to G2"
	      : "Move failed: Piotr already in group " + GroupRegistry.assignedGroup(b.getId()));
	    
	    try {
	        g1.exportToCsv(Path.of("group.csv"));
	        System.out.println("Exported " + g1.getMembers().size() + " students to group.csv");
	    } catch (IOException e) {
	        System.out.println("Export failed: " + e.getMessage());
	    }
	    
	    Group imported = new Group("G3", "Imported group");

	    try {
	        imported.importFromCsv(Path.of("group.csv"));
	        System.out.println("Imported " + imported.getMembers().size() + " students from group.csv");
	    } catch (IOException e) {
	        System.out.println("Import failed: " + e.getMessage());
	    }

	}
}
