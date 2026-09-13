import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorController {

    private final CalculatorModel model;
    private final CalculatorView view;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        this.view.addCalculatorListener(new ButtonClickListener());
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = ((JButton) e.getSource()).getText();

            if (command.equals("AC")) {
                view.setDisplayText("");
            } else if (command.equals("DEL")) {
                String currentText = view.getDisplayText();
                if (!currentText.isEmpty() && !currentText.equals("Error")) {
                    view.setDisplayText(currentText.substring(0, currentText.length() - 1));
                }
            } else if (command.equals("HISTORY")) {
                view.showHistoryDialog(model.getHistory());
            } else if (command.equals("=")) {
                handleEquals();
            } else {
                if (view.getDisplayText().equals("Error")) {
                    view.setDisplayText("");
                }
                view.setDisplayText(view.getDisplayText() + command);
            }
        }
    }

    private void handleEquals() {
        String expression = view.getDisplayText().trim();
        if (expression.isEmpty()) return;

        try {
            double result = model.evaluateExpression(expression);
            String resultStr = (result == (long) result) ? String.valueOf((long) result) : String.valueOf(result);
            model.addHistory(expression + " = " + resultStr);
            view.setDisplayText(resultStr);
        } catch (Exception ex) {
            view.setDisplayText("Error");
        }
    }
}