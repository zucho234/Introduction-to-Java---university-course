package Calc;

import javafx.application.Application;
import javafx.stage.Stage;

public class CalculatorFxApp extends Application {
    @Override
    public void start(Stage stage) {
        CalculatorModel model = new CalculatorModel();
        CalculatorView view = new JavaFxCalculatorView(stage);

        new CalculatorController(model, view);

        view.showView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}