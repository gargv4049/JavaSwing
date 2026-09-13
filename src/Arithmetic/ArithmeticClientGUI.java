package Arithmetic;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.DecimalFormat;
import javax.swing.*;

public class ArithmeticClientGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextField firstField, secondField, resultField;
    private JLabel statusLabel;
    private DefaultListModel<String> historyModel;
    private Arithmetic stub;
    private final DecimalFormat df = new DecimalFormat("#,##0.FFFFFF");

    // Modern Color Palette
    private final Color darkBg = new Color(15, 23, 42);          // Slate 900
    private final Color cardBg = new Color(30, 41, 59);          // Slate 800
    private final Color inputBg = new Color(51, 65, 85);         // Slate 700
    private final Color textColor = new Color(241, 245, 249);     // Slate 100
    private final Color accentColor = new Color(99, 102, 241);    // Indigo Accent

    public ArithmeticClientGUI() {
        // ================= RMI CONNECTION =================
        try {
            Registry r = LocateRegistry.getRegistry("localhost", 1099);
            stub = (Arithmetic) r.lookup("ArithmeticService");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Cannot connect to RMI Server.\nPlease execute Server.java first.",
                    "RMI Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        // ================= FRAME CONFIG =================
        setTitle("RMI Calculator Pro — Vivek Garg (24EARCS186)");
        setSize(820, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(darkBg);

        JPanel root = new JPanel(new BorderLayout(15, 15));
        root.setBackground(darkBg);
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // ================= HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JLabel title = new JLabel("RMI CALCULATOR PRO");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(textColor);

        JLabel subtitle = new JLabel("Vivek Garg (24EARCS186)  |  Distributed Computing");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(new Color(148, 163, 184));

        header.add(title, BorderLayout.NORTH);
        header.add(subtitle, BorderLayout.SOUTH);
        root.add(header, BorderLayout.NORTH);

        // ================= CENTER (CALCULATOR + HISTORY) =================
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createCalcPanel(), createHistoryPanel());
        splitPane.setDividerLocation(480);
        splitPane.setOpaque(false);
        splitPane.setBorder(null);
        splitPane.setDividerSize(6);

        root.add(splitPane, BorderLayout.CENTER);

        // ================= STATUS BAR =================
        statusLabel = new JLabel("● RMI Server Connected  |  Port: 1099  |  Host: localhost");
        statusLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
        statusLabel.setForeground(new Color(52, 211, 153)); // Emerald green badge
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 0));
        root.add(statusLabel, BorderLayout.SOUTH);

        add(root);
    }

    private JPanel createCalcPanel() {
        JPanel calcPanel = new JPanel(new BorderLayout(12, 12));
        calcPanel.setBackground(cardBg);
        calcPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Inputs Layout
        JPanel inputGrid = new JPanel(new GridLayout(3, 2, 10, 10));
        inputGrid.setOpaque(false);

        firstField = createStyledTextField();
        secondField = createStyledTextField();
        resultField = createStyledTextField();
        resultField.setEditable(false);
        resultField.setForeground(new Color(251, 191, 36)); // Gold text

        inputGrid.add(createLabel("First Value (A):"));
        inputGrid.add(firstField);
        inputGrid.add(createLabel("Second Value (B):"));
        inputGrid.add(secondField);
        inputGrid.add(createLabel("Calculated Result:"));
        inputGrid.add(resultField);

        calcPanel.add(inputGrid, BorderLayout.NORTH);

        // Keypad Grid
        JPanel btnGrid = new JPanel(new GridLayout(3, 3, 8, 8));
        btnGrid.setOpaque(false);

        btnGrid.add(createButton("+ ADD", new Color(14, 165, 233), e -> calculate("add")));
        btnGrid.add(createButton("- SUB", new Color(225, 29, 72), e -> calculate("sub")));
        btnGrid.add(createButton("× MUL", new Color(124, 58, 237), e -> calculate("mul")));
        btnGrid.add(createButton("÷ DIV", new Color(217, 119, 6), e -> calculate("div")));
        btnGrid.add(createButton("A^B POW", new Color(236, 72, 153), e -> calculate("pow")));
        btnGrid.add(createButton("A%B MOD", new Color(20, 184, 166), e -> calculate("mod")));
        btnGrid.add(createButton("√A SQRT", new Color(168, 85, 247), e -> calculate("sqrt")));
        btnGrid.add(createButton("CLEAR", new Color(100, 116, 139), e -> clearFields()));

        calcPanel.add(btnGrid, BorderLayout.CENTER);
        return calcPanel;
    }

    private JPanel createHistoryPanel() {
        JPanel historyPanel = new JPanel(new BorderLayout(8, 8));
        historyPanel.setBackground(cardBg);
        historyPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel hTitle = new JLabel("RMI Execution Stream");
        hTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        hTitle.setForeground(textColor);

        historyModel = new DefaultListModel<>();
        JList<String> historyList = new JList<>(historyModel);
        historyList.setBackground(inputBg);
        historyList.setForeground(new Color(226, 232, 240));
        historyList.setFont(new Font("Consolas", Font.PLAIN, 12));
        historyList.setSelectionBackground(accentColor);

        JScrollPane scroll = new JScrollPane(historyList);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(71, 85, 105)));

        JButton clearHistory = createButton("Clear Stream", new Color(71, 85, 105), e -> historyModel.clear());
        clearHistory.setPreferredSize(new Dimension(100, 32));

        historyPanel.add(hTitle, BorderLayout.NORTH);
        historyPanel.add(scroll, BorderLayout.CENTER);
        historyPanel.add(clearHistory, BorderLayout.SOUTH);

        return historyPanel;
    }

    private JLabel createLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Segoe UI", Font.BOLD, 13));
        l.setForeground(textColor);
        return l;
    }

    private JTextField createStyledTextField() {
        JTextField tf = new JTextField();
        tf.setFont(new Font("Consolas", Font.BOLD, 15));
        tf.setBackground(inputBg);
        tf.setForeground(textColor);
        tf.setCaretColor(textColor);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(71, 85, 105)),
                BorderFactory.createEmptyBorder(6, 10, 6, 10)
        ));
        return tf;
    }

    private JButton createButton(String text, Color baseColor, java.awt.event.ActionListener listener) {
        JButton btn = new JButton(text) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isPressed()) {
                    g2.setColor(baseColor.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(baseColor.brighter());
                } else {
                    g2.setColor(baseColor);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2.dispose();
                super.paintComponent(g);
            }
        };

        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(listener);
        return btn;
    }

    private void calculate(String op) {
        try {
            double a = Double.parseDouble(firstField.getText().trim());
            double b = 0;
            if (!op.equals("sqrt")) {
                b = Double.parseDouble(secondField.getText().trim());
            }

            long startTime = System.nanoTime();
            double ans = 0;
            String symbol = "";

            switch (op) {
                case "add": ans = stub.add(a, b); symbol = "+"; break;
                case "sub": ans = stub.subtract(a, b); symbol = "-"; break;
                case "mul": ans = stub.multiply(a, b); symbol = "×"; break;
                case "div": ans = stub.divide(a, b); symbol = "÷"; break;
                case "pow": ans = stub.power(a, b); symbol = "^"; break;
                case "mod": ans = stub.modulo(a, b); symbol = "%"; break;
                case "sqrt": ans = stub.squareRoot(a); symbol = "√"; break;
            }

            long elapsedNanos = System.nanoTime() - startTime;
            double elapsedMs = elapsedNanos / 1_000_000.0;

            String formattedAns = df.format(ans);
            resultField.setText(formattedAns);

            // Log entry
            String logEntry = op.equals("sqrt")
                    ? String.format("√(%s) = %s", df.format(a), formattedAns)
                    : String.format("%s %s %s = %s", df.format(a), symbol, df.format(b), formattedAns);

            historyModel.addElement(logEntry);
            statusLabel.setText(String.format("● RMI OK  |  Latency: %.2f ms  |  Executed: %s", elapsedMs, op.toUpperCase()));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please input valid numbers!", "Input Error", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Server Exception", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        firstField.setText("");
        secondField.setText("");
        resultField.setText("");
        firstField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ArithmeticClientGUI().setVisible(true));
    }
}