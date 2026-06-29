package Calc;

public interface CalculatorView {
	void setDisplayText(String text);
    void setErrorMode(boolean error);
    void setOnTokenPressed(TokenListener listener);
    void showView();

    interface TokenListener {
        void onTokenPressed(String token);
    }
}
