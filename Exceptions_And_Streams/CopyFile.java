import java.io.*;
import java.net.*;

public class CopyFile {

	public static void main(String[] args) {
		
		if (args.length != 2) {
		      System.out.println("Usage: java CopyFile Filename Filename");
		      return;
		}
		
		String source = args[0];
		String destination = args[1];
		
		try ( InputStream in = source.startsWith("http://") || source.startsWith("https://") 
				? new URL(source).openStream() : new FileInputStream(source);
	            OutputStream out = new FileOutputStream(destination)) {
	            
			byte[] buffer = new byte[1024];
	        int bytesRead;

	        while ((bytesRead = in.read(buffer)) != -1) {
	        	out.write(buffer, 0, bytesRead);
	        }

	        if (source.startsWith("http://") || source.startsWith("https://")) {
	        	System.out.println("Pobrano i zapisano do pliku " + destination);
	        } else {
	        	System.out.println("Plik skopiowany pomyślnie.");
	        }

	    } catch (IOException e) {
	    	System.out.println("Error reading file: " + e.getMessage());
	    } //catch (ArrayIndexOutOfBoundsException e) {
	    //	System.out.println("Usage: java CopyFile Filename Filename");
	    //}
				
	}
}