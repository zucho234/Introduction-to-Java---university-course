public class DayNumber {

	public static void main(String[] args) {
		
		if(args.length == 0){
			System.out.println("Podaj liczbe z zakresu 1-7!!");
			return;
		}
		
		int number = Integer.parseInt(args[0]);
		switch (number) {
			case 1: System.out.println("It is Monday"); break;
			case 2: System.out.println("It is Tuesdey"); break;
			case 3: System.out.println("It is Wednesday"); break;
			case 4: System.out.println("It is Thursday"); break;
			case 5: System.out.println("It is Friday"); break;
			case 6: System.out.println("It is Saturday"); break;
			case 7: System.out.println("It is Sunday"); break;
			default: System.out.println("Wrong number");
		}
	
	}
}
