package i2jp.oop;

import java.util.Collections;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Group {
	private static final Logger log = LogManager.getLogger(Group.class);
	
	private final String name;
	private final String description;
	private final Set<Student> members = new HashSet<>();
	
	public Group (String name, String description) {
		this.name = name;
		this.description = description;
		log.info("Group created: name='{}', description='{}'", name, description);
	}
	
	public String getName() {
	    return name;
	}

	public String getDescription() {
	    return description;
	}
	
	public boolean addStudent(Student s) {
		if (GroupRegistry.isAssigned(s.getId())) {
			String assigned = GroupRegistry.assignedGroup(s.getId());
		      log.warn("Cannot add student id={} to group='{}' — already in group='{}'",
		               s.getId(), name, assigned);
			return false;
		}
		
		boolean ok = members.add(s);
	    if (ok) {
	    	GroupRegistry.assign(s.getId(), name);
	    	log.info("Student index={} added to group='{}'", s.getIndexNumber(), name);
	    } else {
	      log.debug("Student index={} already in group='{}'", s.getIndexNumber(), name);
	    }
	    return ok;
	}
	
	public boolean removeStudent(Student s) {
		
		boolean ok = members.remove(s);
		if (ok) {
			GroupRegistry.unassign(s.getId());
			log.info("Student index={} removed from group='{}'", s.getIndexNumber(), name);
	    } else {
	      log.warn("Attempt to remove not-member index={} from group='{}'",
	               s.getIndexNumber(), name);
	    }
		return ok;
	}
	
	public Set<Student> getMembers() {
		return Collections.unmodifiableSet(members);
	}
	
	@Override
	public String toString() {
		return "Group " + name + " (" + description + ") size=" + members.size();
	}
	
	private static final DateTimeFormatter DMY =
		    DateTimeFormatter.ofPattern("dd.MM.yyyy");
	
	public void exportToCsv(Path file) throws IOException {
	    exportToCsv(file, ";");
	}

	public void exportToCsv(Path file, String delimiter) throws IOException {
		log.info("Exporting {} students from group='{}' to file={}",
			      members.size(), name, file);
		
		List<String> lines = members.stream()
	        .map(s -> String.join(delimiter,
	            s.getId(),
	            s.getIndexNumber(),
	            s.getFirstName(),
	            s.getLastName(),
	            s.getBirthDay().format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy")),
	            formatGrades(s)
	        ))
	        .collect(Collectors.toList());

		try {
		    Files.write(file, lines);
		    log.info("Export successful: {} lines written to {}", lines.size(), file);
		  } catch (IOException e) {
		    log.error("Export failed for file={}: {}", file, e.getMessage());
		    throw e;
		  }
	}

	private String formatGrades(Student s) {
	    return s.getGrades().stream()
	        .map(g -> String.format(java.util.Locale.US, "%.1f", g))
	        .collect(Collectors.joining(",", "[", "]"));
	}
	
	public void importFromCsv(Path file) throws IOException,  CsvFormatException {
	    importFromCsv(file, ";");
	}

	public void importFromCsv(Path file, String delimiter) throws IOException,  CsvFormatException {
		log.info("Importing students into group='{}' from file={}", name, file);
		
		List<String> lines = Files.readAllLines(file);
		int imported =0;

	    for (String line : lines) {
	    	log.debug("Raw CSV line: {}", line);
	    	
	    	if (line.isBlank()) {
	            continue;
	        }

	        String[] parts = line.split(Pattern.quote(delimiter), 6);

	        if (parts.length != 6) {
	        	String msg = "Malformed CSV line (expected 6 fields): " + line;
	            log.error(msg);
	            throw new CsvFormatException(msg);
	        }

	        Student s = new Student(
	            parts[2],
	            parts[3],
	            parts[4],
	            Gender.OTHER,
	            parts[1]
	        );

	        parseGrades(parts[5]).forEach(s::addGrade);
	        
	        if (addStudent(s)) {
	        	imported++;
	        }
	    }
	    log.info("Import finished: {} students imported into group='{}'", imported, name);
	}

	private List<Double> parseGrades(String text) {
	    String cleaned = text.trim();

	    if (!cleaned.startsWith("[") || !cleaned.endsWith("]")) {
	        throw new IllegalArgumentException("Invalid grades list: " + text);
	    }

	    cleaned = cleaned.substring(1, cleaned.length() - 1).trim();

	    if (cleaned.isEmpty()) {
	        return List.of();
	    }

	    return java.util.Arrays.stream(cleaned.split(","))
	        .map(String::trim)
	        .map(Double::parseDouble)
	        .collect(Collectors.toList());
	}
	
}
