import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lab7JFrame {
	private JTextField display;
	
	private long left = 0L;
	private String operator = "";
	private String lastOperator = "";
	private long lastRight = 0L;
	
	private boolean startNewNumber = true;
	private boolean afterEquals = true;
	private boolean error = false;
	
	public void createAndShowGUI() {
		JFrame frame = new JFrame("My calculator - v.0.1");
		frame.setLayout(new BorderLayout(8,8));
		frame.setSize(520, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		display = new JTextField("0");
		display.setEditable(false);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setFont(new Font("SansSefif", Font.BOLD, 28));
		display.setForeground(Color.BLACK);
		
		JPanel buttons = new JPanel(new GridLayout(4,4,10,10));
		
		String[] labels = {
			"1", "2", "3", "+",
			"4", "5", "6", "-",
			"7", "8", "9", "*",
			"0", "=", "C", "/"
		};
		
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				
				switch (command) {
				
					case "C":
						clear();
						break;
					
					case "0", "1", "2", "3", "4",
						 "5", "6", "7", "8", "9":
						handleDigit(command);
						break;
						
					case "=":
						handleEquals();
						break;
						
					default:
						handleOperator(command);
				}
			}
		};
		
		for (String label : labels) {
			JButton button = new JButton(label);

			button.addActionListener(listener);
			buttons.add(button);
		}
		
		frame.add(display, BorderLayout.NORTH);
		frame.add(buttons, BorderLayout.CENTER);
		
		
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
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
		
		if (startNewNumber || display.getText().equals("0")) {
			display.setText(digit);
			startNewNumber = false;
		} else {
			display.setText(display.getText()+digit);
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
		
		Long current = Long.parseLong(display.getText());
		
		if (operator.equals("")) {
			left = current;
		} else {
			left = compute(left, operator, current);
			if (error) {
				return;
			}
			display.setText(String.valueOf(left));
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
			
			display.setText(String.valueOf(left));
			startNewNumber = true;
			return;
		}
		
		if (operator.equals("")) {
			left = Long.parseLong(display.getText());
		    afterEquals = true;
		    startNewNumber = true;
			return;
		}
		
		long currentRight;
		
		if (startNewNumber) {
			currentRight = left;
		} else {
			currentRight = Long.parseLong(display.getText());
		}
		
		left = compute(left, operator, currentRight);
		if (error) {
			return;
		}
		lastOperator = operator;
		lastRight = currentRight;
		display.setText(String.valueOf(left));
		afterEquals = true;
		startNewNumber = true;
		operator = "";
	}
	
	private void clear() {
		display.setText("0");
		left = 0;
		operator = "";
		lastOperator = "";
		lastRight = 0;
		startNewNumber = true;
		afterEquals = true;
		error = false;
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
				showError("ERROR: Division by 0");
				return 0;
			} else {
				return a / b;
			}	
		}
		return a;
	}
	
	private void showError(String message) {
		display.setText(message);
		
		left = 0;
	    operator = "";
	    lastOperator = "";
	    lastRight = 0;
	    startNewNumber = true;
	    afterEquals = true;
	    error = true;
	}
	

	public static void main(String[] args) {
		Lab7JFrame app = new Lab7JFrame();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				app.createAndShowGUI();
			}
		});
	}

}
