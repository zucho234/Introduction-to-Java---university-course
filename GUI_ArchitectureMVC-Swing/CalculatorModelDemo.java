package calc;

public class CalculatorModelDemo {
	  public static void main(String[] args) {
	    testTrace(
	        "12+3=",
	        "1", "12", "12", "3", "15"
	    );

	    testTrace(
	        "12+3+4=",
	        "1", "12", "12", "3", "15", "4", "19"
	    );

	    testTrace(
	        "12=+3=+4=-5+7==",
	        "1", "12", "12", "12", "3", "15", "15", "4", "19",
	        "19", "5", "14", "7", "21", "28"
	    );

	    testTrace(
	        "--2+3==",
	        "0", "0", "2", "-2", "3", "1", "4"
	    );

	    testTrace(
	        "--2*==",
	        "0", "0", "2", "-2", "4", "-8"
	    );

	    testTrace(
	        "12/0=",
	        "1", "12", "12", "0", "ERROR: Division by zero"
	    );

	    testTrace(
	        "C==",
	        "0", "0", "0"
	    );

	    testTrace(
	        "12/0=5+",
	        "1", "12", "12", "0", "ERROR: Division by zero", "5", "5"
	    );

	    testTrace(
	        "12/0=-5=",
	        "1", "12", "12", "0", "ERROR: Division by zero", "0", "5", "-5"
	    );
	  }

	  private static void testTrace(String sequence, String... expectedDisplays) {
	    CalculatorModel model = new CalculatorModel();

	    if (sequence.length() != expectedDisplays.length) {
	      System.out.println("Invalid test: sequence and expected trace lengths differ");
	      return;
	    }

	    boolean ok = true;

	    for (int i = 0; i < sequence.length(); i++) {
	      String token = String.valueOf(sequence.charAt(i));

	      model.press(token);

	      String actual = model.getDisplayText();
	      String expected = expectedDisplays[i];

	      if (!actual.equals(expected)) {
	        ok = false;

	        System.out.printf(
	            "FAIL %-24s step %2d pressed %-2s expected %-24s got %-24s%n",
	            sequence,
	            i + 1,
	            token,
	            expected,
	            actual
	        );
	      }
	    }

	    if (ok) {
	      System.out.println("OK   " + sequence);
	    }
	  }
	}