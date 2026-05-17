import java.io.*;

public class WordCounter {

	public static void main(String[] args) {
		
		if (args.length == 0) {
		      System.out.println("Usage: java WordCounter filename");
		      return;
		}
		
		int words = 0;
		int characters =0;
		
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	characters += line.length();
		        words += line.split("\\s+").length;
		    }
		    System.out.println("Words: " + words);
		    System.out.println("Characters: " + characters);
		} catch (IOException e) {
		    System.out.println("Error reading file: " + e.getMessage());
		}

	}
}