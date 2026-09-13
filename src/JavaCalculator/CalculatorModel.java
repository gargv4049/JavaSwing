import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalculatorModel {

    private final List<String> historyList = new ArrayList<>();

    public double evaluateExpression(String expression) throws Exception {
        expression = expression.replace(':', '/');
        char[] tokens = expression.toCharArray();

        Stack<Double> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') continue;

            if ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.') {
                StringBuilder sbuf = new StringBuilder();
                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
                    sbuf.append(tokens[i++]);
                }
                i--;
                values.push(Double.parseDouble(sbuf.toString()));
            } else if (isOperator(tokens[i])) {
                if (tokens[i] == '-' && (i == 0 || isOperator(tokens[i - 1]))) {
                    StringBuilder sbuf = new StringBuilder("-");
                    i++;
                    while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.')) {
                        sbuf.append(tokens[i++]);
                    }
                    i--;
                    values.push(Double.parseDouble(sbuf.toString()));
                    continue;
                }

                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek())) {
                    values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                }
                ops.push(tokens[i]);
            }
        }

        while (!ops.empty()) {
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    private boolean hasPrecedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/' || op1 == '%') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private double applyOp(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b;
            case '%': return a % b;
        }
        return 0;
    }

    public void addHistory(String entry) {
        historyList.add(entry);
    }

    public List<String> getHistory() {
        return historyList;
    }
}