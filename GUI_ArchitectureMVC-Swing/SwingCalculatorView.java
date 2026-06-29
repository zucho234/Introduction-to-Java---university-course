package calc;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingCalculatorView extends JFrame implements CalculatorView {
	private final JTextField display = new JTextField("0");
	private final JPanel buttons = new JPanel(new GridLayout(4, 4, 10, 10));
	
	public SwingCalculatorView() {
        super("My First Calculator - MVC");
     
        setLayout(new BorderLayout(8,8));
		setSize(560, 420);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		display.setEditable(false);
		display.setHorizontalAlignment(JTextField.RIGHT);
		display.setFont(new Font("SansSefif", Font.BOLD, 28));
		display.setForeground(Color.BLACK);
		
		String[] labels = {
			"1", "2", "3", "+",
			"4", "5", "6", "-",
			"7", "8", "9", "*",
			"0", "=", "C", "/"
		};
		
		for (String label : labels) {
            JButton button = new JButton(label);
            buttons.add(button);
        }

        add(display, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
	}
	
	@Override
    public void setDisplayText(String text) {
        display.setText(text);
    }

    @Override
    public void setErrorMode(boolean error) {
        display.setForeground(error ? Color.RED : Color.BLACK);
    }
		
    @Override
    public void setOnTokenPressed(TokenListener listener) {
        for (Component component : buttons.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        listener.onTokenPressed(e.getActionCommand());
                    }
                });
            }
        }
    }

    @Override
    public void showView() {
        setVisible(true);
    }
	
}
