public class PrimeCheck {

	public static boolean CzyPierwsza(int N) {
		
		if( N<2) {
			return false;
		}
		
		for(int i=2 ; i*i <= N ; i++) {
			if(N%i==0) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
		if (args.length == 0) {
		      System.out.println("Usage: java PrimeCheck N");
		      return;
		}
		
		int N = Integer.parseInt(args[0]);
		
		
		boolean prime = CzyPierwsza(N);
		if(prime == true) {
			System.out.println(N + " is prime");
		}
		else {
			System.out.println(N + " is not prime");
			for(int i=2; i<N; i++) {
				if(N%i==0) {
					System.out.println("Smallest divisior: " + i);
					break;
				}
			}
		}
	
	}
}