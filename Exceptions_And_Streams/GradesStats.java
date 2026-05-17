import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.DoubleStream;

public class GradesStats {

	public static void main(String[] args) {
		
		if (args.length != 1) {
		      System.out.println("Usage: java GradesStats Filename");
		      return;
		}
		
		String fileName = args[0];
		
		try {
            double[] grades = Files.lines(Paths.get(fileName))
            		.skip(1)
                    .map(line -> line.split(",")[1].trim())
                    .mapToDouble(Double::parseDouble)
                    .toArray();

            double sum = DoubleStream.of(grades).sum();
            double avg = sum / grades.length;
            double min = DoubleStream.of(grades).min().getAsDouble();
            double max = DoubleStream.of(grades).max().getAsDouble();

            System.out.printf("Average: %.2f%n", avg);
            System.out.println("Highest: " + max);
            System.out.println("Lowest: " + min);
            System.out.println("Count: " + grades.length);

        } catch (IOException e) {
            System.out.println("Błąd pliku: " + e.getMessage());
        }

	}
}
