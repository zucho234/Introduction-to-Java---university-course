
public class SafeDivision {

	public static void main(String[] args) {

		try {
			int a = Integer.parseInt(args[0]);
			int b = Integer.parseInt(args[1]);
			int result = a/b;
			System.out.println("Resoult: " + result);
		} catch (ArithmeticException e) {
			System.out.println("Cannot divide by zero!");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage: java SafeDivision a b");
	    } catch (NumberFormatException e) {
	      System.out.println("Arguments must be numbers.");
	    }
		
	}
}
