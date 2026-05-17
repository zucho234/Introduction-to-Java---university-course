public class MultiplicationTable {

	public static void main(String[] args) {

		if (args.length == 0) {
		      System.out.println("Usage: java MultiplicationTable N");
		      return;
		}
		
		int N = Integer.parseInt(args[0]);
		
		for (int i = 1 ; i<=N ; i++) {
			for (int j = 1 ; j<=N ; j++)
			{
				int x = i * j;
				System.out.print(x + " ");
			}
			System.out.print("\n");
		}
		
	}
}
