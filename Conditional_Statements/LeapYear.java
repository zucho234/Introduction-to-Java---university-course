
public class LeapYear {

	public static void main(String[] args) {
		
		if(args.length  == 0){
			System.out.println("Nie podałeś roku");
			return;
		}
		
		
		int Year = Integer.parseInt(args[0]);
		boolean isLeap = (Year % 4 == 0 && Year % 100 != 0) || (Year % 400 == 0);
		
		if(isLeap == true) {
			System.out.println("This is a leap year");
		}
		else {
			System.out.println("This is not a leap year");
		}
	}
}
