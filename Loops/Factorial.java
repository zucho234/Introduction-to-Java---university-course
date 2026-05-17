public class Factorial {

	public static void main(String[] args) {
		
		if (args.length == 0) {
		      System.out.println("Usage: java Factorial N");
		      return;
		}
		
		int N = Integer.parseInt(args[0]);
		int sum = 1;
		
		if(N==0) {
			System.out.println("1");
		}
		else {
			for (int i = 1; i<=N; i++) {
				sum = sum * i;
			}
		}
		
		System.out.println(N + "! = " + sum);
	}
}
