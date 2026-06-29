package Calc;

public class CalculatorController {
	private final CalculatorModel model;
    private final CalculatorView view;
    
    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        
        view.setOnTokenPressed(new CalculatorView.TokenListener() {
            @Override
            public void onTokenPressed(String token) {
              handleToken(token);
            }
          });

          refreshView();
    }
    
    private void handleToken(String token) {
        model.press(token);
        refreshView();
      }
    
    private void refreshView() {
        view.setDisplayText(model.getDisplayText());
        view.setErrorMode(model.isError());
    }
    
    
}
