import java.io.*;

public class FileReaderExample {

	public static void main(String[] args) {
		
		if (args.length == 0) {
		      System.out.println("Usage: java FileReaderExample filename");
		      return;
		}
		
		int lines = 0;
		
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
		   while (br.readLine() != null) lines++;
		   System.out.println("Lines: " + lines);
		} catch (FileNotFoundException e) {
		   System.out.println("File not found: " + args[0]);
		} catch (IOException e) {
		   System.out.println("Error reading file.");
		}

	}
}
