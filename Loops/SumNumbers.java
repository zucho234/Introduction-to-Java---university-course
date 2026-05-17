public class SumNumbers {

	public static void main(String[] args) {
		
		if (args.length == 0) {
		      System.out.println("Usage: java SumNumbers N");
		      return;
		}
		
		int N = Integer.parseInt(args[0]);
		int sum = 0;
		int i = 0;
		
		while(i < N)
		{
			sum += ++i;
		}
		
		System.out.println("Sum = " + sum);
	}
}
