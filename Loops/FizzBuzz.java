public class FizzBuzz {

	public static void main(String[] args) {
		
		if (args.length == 0) {
		      System.out.println("Usage: java FizzBuzz N");
		      return;
		}
		
		int N = Integer.parseInt(args[0]);
		
		for (int i = 1 ; i<=N ; i++) {
			
			if (i%3 == 0 && i%5 ==0) {
				System.out.print("FizzBuzz ");
			}
			else if ( i%5 == 0) {
				System.out.print("Buzz ");
			}
			else if ( i%3 == 0) {
				System.out.print("Fizz ");
			}
			else {
				System.out.print(i + " ");
			}
			
		}

	}
}
