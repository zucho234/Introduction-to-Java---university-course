package calc;

import javax.swing.SwingUtilities;

public class CalculatorApp {
	  public static void main(String[] args) {
	    SwingUtilities.invokeLater(new Runnable() {
	      @Override
	      public void run() {
	        start();
	      }
	    });
	  }

	  private static void start() {
	    CalculatorModel model = new CalculatorModel();
	    CalculatorView view = new SwingCalculatorView();

	    new CalculatorController(model, view);

	    view.showView();
	  }
	}
