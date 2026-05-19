package i2jp.oop;

public class CsvFormatException extends Exception {
	public CsvFormatException(String message) {
	    super(message);
	  }

	  public CsvFormatException(String message, Throwable cause) {
	    super(message, cause);
	  }
}
