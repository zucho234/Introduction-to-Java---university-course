import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class LayoutExperiment {
  public static void createAndShowGUI() {
	  JFrame frame = new JFrame("My First Frame");
		frame.setSize(320, 180);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		JPanel panel = new JPanel(new GridLayout(2, 3, 8, 8));

		  panel.add(new JButton("1"));
		  panel.add(new JButton("2"));
		  panel.add(new JButton("3"));
		  panel.add(new JButton("4"));
		  panel.add(new JButton("5"));
		  panel.add(new JButton("6"));

		  frame.add(panel);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        createAndShowGUI();
      }
    });
  }
}