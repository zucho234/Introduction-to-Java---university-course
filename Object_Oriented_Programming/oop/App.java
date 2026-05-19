package i2jp.oop;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class App {
	private static final Logger log = LogManager.getLogger(App.class);
	
    private static final Scanner sc = new Scanner(System.in);
    private static final Map<String, Student> students = new LinkedHashMap<>();
    private static final Map<String, Group> groups = new LinkedHashMap<>();

    private static String delimiter;
    private static Path studentsFile;
    private static Path groupsFile;
    

    public static void main(String[] args) {
    	log.info("Application starting...");
    	
    	loadConfig();

        boolean running = true;

        while (running) {
            printMenu();
            String option = sc.nextLine();
            log.debug("User selected menu option '{}'", option);

            try {
                switch (option) {
                    case "1" -> loadStudents();
                    case "2" -> loadGroups();
                    case "3" -> saveStudents();
                    case "4" -> saveGroups();
                    case "5" -> addStudent();
                    case "6" -> addGroup();
                    case "7" -> assignStudent();
                    case "8" -> removeStudent();
                    case "9" -> showStudents();
                    case "10" -> showGroups();
                    case "11" -> showGroupDetails();
                    case "0" -> {
                    	log.info("User selected exit. Application shutting down...");
                    	running = false;
                    }
                    default -> {
                    	System.out.println("Invalid option");
                    	log.warn("Unknown menu option: '{}'", option);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                log.error("Unhandled exception while processing menu option '{}'", option, e);
            }
        } 
    }
    
    private static void printMenu() {
        System.out.println();
        System.out.println("=== Student & Group Manager ===");
        System.out.println("1) Load students from CSV");
        System.out.println("2) Load groups from CSV");
        System.out.println("3) Save students to CSV");
        System.out.println("4) Save groups to CSV");
        System.out.println("5) Add new student");
        System.out.println("6) Add new group");
        System.out.println("7) Assign student to group");
        System.out.println("8) Remove student from group");
        System.out.println("9) Show all students");
        System.out.println("10) Show all groups");
        System.out.println("11) Show group details");
        System.out.println("0) Exit");
        System.out.print("Select option: ");
    }
    
    private static void loadConfig() {
        Path file = Path.of("console.properties");
        Properties props = new Properties();

        try {
            if (!Files.exists(file)) {
                props.setProperty("delimiter", ";");
                props.setProperty("students", "students.csv");
                props.setProperty("groups", "groups.csv");
                props.store(Files.newOutputStream(file), "Console configuration");
                log.info("Created default configuration file: {}", file);
                //System.out.println("[INFO] Created default configuration: console.properties");
            }

            props.load(Files.newInputStream(file));

            delimiter = props.getProperty("delimiter", ";");
            studentsFile = Path.of(props.getProperty("students", "students.csv"));
            groupsFile = Path.of(props.getProperty("groups", "groups.csv"));
            
            log.info("Configuration loaded: delimiter='{}', studentsFile='{}', groupsFile='{}'",
                    delimiter, studentsFile, groupsFile);

        } catch (IOException e) {
            delimiter = ";";
            studentsFile = Path.of("students.csv");
            groupsFile = Path.of("groups.csv");
            
            log.warn("Could not load configuration file. Using default settings.", e);
        }
    }
    
    private static void addStudent() {
        System.out.print("First name: ");
        String firstName = sc.nextLine();

        System.out.print("Last name: ");
        String lastName = sc.nextLine();

        System.out.print("Birth date DD.MM.YYYY: ");
        String birthDate = sc.nextLine();

        System.out.print("Gender MALE/FEMALE/OTHER: ");
        Gender gender = Gender.valueOf(sc.nextLine().toUpperCase());

        System.out.print("Index number: ");
        String index = sc.nextLine();

        Student s = new Student(firstName, lastName, birthDate, gender, index);
        students.put(s.getId(), s);
        
        log.info("Added student: id='{}', index='{}', name='{} {}'",
                s.getId(), s.getIndexNumber(), s.getFirstName(), s.getLastName());

        System.out.println("Added student: " + s);
    }
    
    private static void addGroup() {
        System.out.print("Group name: ");
        String name = sc.nextLine();

        System.out.print("Description: ");
        String description = sc.nextLine();

        Group group = new Group(name, description);
        groups.put(name, group);

        log.info("Added group: name='{}'", name);
        System.out.println("Added group: " + name);
    }
    
    private static void assignStudent() {
        System.out.print("Student id: ");
        String studentId = sc.nextLine();

        System.out.print("Group name: ");
        String groupName = sc.nextLine();

        Student s = students.get(studentId);
        Group g = groups.get(groupName);

        if (s == null || g == null) {
            System.out.println("Student or group not found");
            log.warn("Cannot assign student. Student id='{}', group='{}'", studentId, groupName);
            return;
        }

        if (g.addStudent(s)) {
            System.out.println("Assigned student to group");
            log.info("Assigned student id='{}' to group='{}'", studentId, groupName);
        } else {
            System.out.println("Student already assigned to group " + GroupRegistry.assignedGroup(studentId));
            log.warn("Student id='{}' already assigned to group='{}'",
            		studentId, GroupRegistry.assignedGroup(studentId));
        }
    }
    
    private static void removeStudent() {
        System.out.print("Student id: ");
        String studentId = sc.nextLine();

        System.out.print("Group name: ");
        String groupName = sc.nextLine();

        Student s = students.get(studentId);
        Group g = groups.get(groupName);

        if (s == null || g == null) {
            System.out.println("Student or group not found");
            log.warn("Cannot remove student. Student id='{}', group='{}'", studentId, groupName);
            return;
        }

        if (g.removeStudent(s)) {
            System.out.println("Removed student from group");
            log.info("Removed student id='{}' from group='{}'", studentId, groupName);
        } else {
            System.out.println("Student was not in this group");
            log.warn("Student id='{}' was not in group='{}'", studentId, groupName);
        }
    }
    
    private static void showStudents() {
        students.values().forEach(s ->
            System.out.println(s.getId() + " | " + s.getFirstName() + " " + s.getLastName()
                + " | index=" + s.getIndexNumber())
        );
    }

    private static void showGroups() {
        groups.values().forEach(System.out::println);
    }
    
    private static void showGroupDetails() {
        System.out.print("Group name: ");
        String groupName = sc.nextLine();

        Group g = groups.get(groupName);

        if (g == null) {
            System.out.println("Group not found");
            return;
        }

        System.out.println(g.getName() + " - " + g.getDescription());
        System.out.println("Members:");

        for (Student s : g.getMembers()) {
            System.out.println("- " + s.getFirstName() + " " + s.getLastName() + " (" + s.getIndexNumber() + ")");
        }
    }
    
    private static void saveStudents() throws IOException {
        Group all = new Group("ALL", "All students");

        for (Student s : students.values()) {
            all.addStudent(s);
        }

        all.exportToCsv(studentsFile, delimiter);
        log.info("Saved {} students to file '{}'", students.size(), studentsFile);
        System.out.println("Saved students to " + studentsFile);
    }
    
    private static void loadStudents() throws IOException, CsvFormatException {
        Group temp = new Group("TEMP", "Imported students");
        temp.importFromCsv(studentsFile, delimiter);

        for (Student s : temp.getMembers()) {
            students.put(s.getId(), s);
        }
        
        log.info("Loaded students from file '{}'. Current students count: {}",
                studentsFile, students.size());
        System.out.println("Loaded students from " + studentsFile);
    }
    
    private static void saveGroups() throws IOException {
        List<String> lines = new ArrayList<>();

        for (Group g : groups.values()) {
            String ids = g.getMembers().stream()
                .map(Student::getId)
                .collect(java.util.stream.Collectors.joining(",", "[", "]"));

            lines.add(String.join(delimiter, g.getName(), g.getDescription(), ids));
        }

        Files.write(groupsFile, lines);
        log.info("Saved {} groups to file '{}'", groups.size(), groupsFile);
        System.out.println("Saved groups to " + groupsFile);
    }
    
    private static void loadGroups() throws IOException {
        List<String> lines = Files.readAllLines(groupsFile);

        for (String line : lines) {
            String[] parts = line.split(java.util.regex.Pattern.quote(delimiter), 3);

            if (parts.length != 3) {
                System.out.println("Invalid group line: " + line);
                log.warn("Invalid group line: '{}'", line);
                continue;
            }

            Group g = new Group(parts[0], parts[1]);

            String ids = parts[2].trim();
            ids = ids.substring(1, ids.length() - 1);

            if (!ids.isBlank()) {
                for (String id : ids.split(",")) {
                    Student s = students.get(id.trim());

                    if (s != null) {
                        g.addStudent(s);
                    } else {
                    	log.warn("Student id '{}' from groups file was not found in students map", id);
                        System.out.println("Student id not found: " + id);
                    }
                }
            }

            groups.put(g.getName(), g);
        }

        log.info("Loaded groups from file '{}'. Current groups count: {}",
                groupsFile, groups.size());
        System.out.println("Loaded groups from " + groupsFile);
    }
}
