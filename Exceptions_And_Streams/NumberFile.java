import java.io.*;

public class NumberFile {

	public static void main(String[] args) {
		
		if (args.length == 0) {
		      System.out.println("Usage: java NumberFile N");
		      return;
		}
		
		int N = Integer.parseInt(args[0]);
		
		try (PrintWriter out = new PrintWriter("numbers.txt")) {
			for (int i = 1; i <= N; i++) out.println(i);
		} catch (IOException e) {
			System.out.println("Error writing file: " + e.getMessage());
		}

		int sum = 0;
		
		try (BufferedReader br = new BufferedReader(new FileReader("numbers.txt"))) {
			String line;
		    while ((line = br.readLine()) != null) sum += Integer.parseInt(line);
		    System.out.println("File numbers.txt created.");
		    System.out.println("Sum = " + sum);
		} catch (IOException e) {
			System.out.println("Error reading file.");
		}

	}
}