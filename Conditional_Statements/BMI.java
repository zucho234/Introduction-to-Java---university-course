
public class BMI {

	public static void main(String[] args) {
		
		if(args.length <2){
			System.out.println("Podaj obydwa argumenty (weight,height)");
			return;
		}

		Float weight = Float.parseFloat(args[0]);
		Float height = Float.parseFloat(args[1]);
		
		Float BMI = weight / ( height * height );
		System.out.println("Your BMI is: " + BMI);
		
		if (BMI < 18.5) {
	         System.out.println("Underweight");
	      } else if (BMI < 25) {
	         System.out.println("Normal weight");
	      } else if (BMI < 30) {
	         System.out.println("Overweight");
	      } else {
	         System.out.println("Obese");
	      }
	}

}
