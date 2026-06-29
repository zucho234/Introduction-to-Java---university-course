package Calc;

public class CalculatorModel {
	private static final String DIVISION_BY_ZERO = "ERROR: Division by zero";
	
	private String displayText = "0";
	
	private long left = 0L;
	private String operator = "";
	private String lastOperator = "";
	private long lastRight = 0L;
	
	private boolean startNewNumber = true;
	private boolean afterEquals = true;
	private boolean error = false;
	
	public void press(String token) {
			switch (token) {	
				case "C":
					clear();
					break;
					
				case "0", "1", "2", "3", "4",
					 "5", "6", "7", "8", "9":
					handleDigit(token);
					break;
						
				case "=":
					handleEquals();
					break;
						
				default:
					handleOperator(token);
				}
	}
	
	public String getDisplayText() {
		return displayText;
	}
	
	public boolean isError() {
		return error;
	}
	
	private void handleDigit(String digit) {
		if (error) {
			clear();
		}
		
		if (afterEquals) {
	        left = 0;
	        operator = "";
	        lastOperator = "";
	        lastRight = 0;
	        startNewNumber = true;
	        afterEquals = false;
	    }
		
		if (startNewNumber || displayText.equals("0")) {
			displayText =digit;
			startNewNumber = false;
		} else {
			displayText = displayText + digit;
		}
		
		error = false;
	}
	
	private void handleOperator(String op) {
		if (error) {
			clear();
		}
		
		if (startNewNumber && !operator.equals("")) {
	        operator = op;
	        return;
	    }
		
		Long current = Long.parseLong(displayText);
		
		if (operator.equals("")) {
			left = current;
		} else {
			left = compute(left, operator, current);
			if (error) {
				return;
			}
			displayText = String.valueOf(left);
		}
		
		operator = op;
		startNewNumber = true;
		afterEquals = false;
		
	}
	
	private void handleEquals() {
		if (error) {
			clear();
		}
		
		if (afterEquals) {
			if (lastOperator.equals("")) {
				return;
			}
			
			left = compute(left, lastOperator, lastRight);
			
			if (error) {
			    return;
			}
			
			displayText = String.valueOf(left);
			startNewNumber = true;
			return;
		}
		
		if (operator.equals("")) {
			left = Long.parseLong(displayText);
		    afterEquals = true;
		    startNewNumber = true;
			return;
		}
		
		long currentRight;
		
		if (startNewNumber) {
			currentRight = left;
		} else {
			currentRight = Long.parseLong(displayText);
		}
		
		left = compute(left, operator, currentRight);
		if (error) {
			return;
		}
		lastOperator = operator;
		lastRight = currentRight;
		displayText = String.valueOf(left);
		afterEquals = true;
		startNewNumber = true;
		operator = "";
	}
	
	private long compute(long a, String op, long b) {
		if (op.equals("+")) {
			return a + b;
		} else if (op.equals("-")) {
			return a - b;
		} else if (op.equals("*")) {
			return a * b;
		} else if (op.equals("/")) {
			if (b == 0) {
				showError(DIVISION_BY_ZERO);
				return 0;
			} else {
				return a / b;
			}	
		}
		return a;
	}
	
	private void showError(String message) {
		displayText = message;
		
		left = 0;
	    operator = "";
	    lastOperator = "";
	    lastRight = 0;
	    startNewNumber = true;
	    afterEquals = true;
	    error = true;
	}
	
	private void clear() {
		displayText = "0";
		left = 0;
		operator = "";
		lastOperator = "";
		lastRight = 0;
		startNewNumber = true;
		afterEquals = true;
		error = false;
	}
}