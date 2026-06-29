package Calc;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class JavaFxCalculatorView implements CalculatorView {
    private static final String NORMAL_STYLE =
            "-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: black;";
    private static final String ERROR_STYLE =
            "-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: red;";

    private final Stage stage;
    private final TextField display = new TextField("0");
    private final GridPane buttons = new GridPane();

    public JavaFxCalculatorView(Stage stage) {
        this.stage = stage;

        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setStyle(NORMAL_STYLE);

        buttons.setHgap(10);
        buttons.setVgap(10);
        buttons.setPadding(new Insets(10));

        String[] labels = {
                "1", "2", "3", "+",
                "4", "5", "6", "-",
                "7", "8", "9", "*",
                "0", "=", "C", "/"
        };

        for (int i = 0; i < labels.length; i++) {
            Button button = new Button(labels[i]);
            button.setPrefSize(80, 60);

            int row = i / 4;
            int col = i % 4;

            buttons.add(button, col, row);
        }

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(display);
        root.setCenter(buttons);

        Scene scene = new Scene(root, 380, 330);

        stage.setTitle("My First Calculator - JavaFX MVC");
        stage.setScene(scene);
    }

    @Override
    public void setDisplayText(String text) {
        display.setText(text);
    }

    @Override
    public void setErrorMode(boolean error) {
        display.setStyle(error ? ERROR_STYLE : NORMAL_STYLE);
    }

    @Override
    public void setOnTokenPressed(TokenListener listener) {
        for (Node node : buttons.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnAction(event -> listener.onTokenPressed(button.getText()));
            }
        }
    }

    @Override
    public void showView() {
        stage.show();
    }
}
