import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CalculatorView extends JFrame {

    private final JTextField displayField;
    private final JButton equalBtn;
    private final JButton[][] gridButtons;

    private final String[][] buttonLabels = {
            {"AC", "DEL", "%", "HISTORY"},
            {"7", "8", "9", "/"},
            {"4", "5", "6", "*"},
            {"1", "2", "3", "-"},
            {"0", ".", ":", "+"}
    };

    public CalculatorView() {
        setTitle("Java Calculator (MVC Pattern)");
        setSize(460, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(30, 32, 34));
        mainPanel.setLayout(null);

        JLabel headerLabel = new JLabel("CALCULATOR");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds(25, 15, 200, 30);
        mainPanel.add(headerLabel);

        displayField = new JTextField();
        displayField.setBounds(25, 55, 395, 45);
        displayField.setFont(new Font("Arial", Font.BOLD, 20));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        displayField.setBackground(new Color(240, 240, 240));
        mainPanel.add(displayField);

        int startX = 25;
        int startY = 115;
        int btnWidth = 86;
        int btnHeight = 48;
        int gap = 17;

        Color acColor = new Color(175, 200, 120);
        Color topOpColor = new Color(90, 140, 125);
        Color numColor = new Color(205, 215, 195);
        Color darkEqualColor = new Color(40, 110, 85);

        gridButtons = new JButton[5][4];

        for (int row = 0; row < buttonLabels.length; row++) {
            for (int col = 0; col < buttonLabels[row].length; col++) {
                String text = buttonLabels[row][col];
                JButton btn = new JButton(text);

                int x = startX + col * (btnWidth + gap);
                int y = startY + row * (btnHeight + gap);
                btn.setBounds(x, y, btnWidth, btnHeight);
                btn.setFocusable(false);
                btn.setFont(new Font("Arial", Font.BOLD, 13));

                if (text.equals("AC")) {
                    btn.setBackground(acColor);
                    btn.setForeground(Color.BLACK);
                } else if (row == 0 || col == 3) {
                    btn.setBackground(topOpColor);
                    btn.setForeground(Color.WHITE);
                } else {
                    btn.setBackground(numColor);
                    btn.setForeground(Color.BLACK);
                }

                gridButtons[row][col] = btn;
                mainPanel.add(btn);
            }
        }

        equalBtn = new JButton("=");
        int equalX = startX + 2 * (btnWidth + gap);
        int equalY = startY + 5 * (btnHeight + gap);
        equalBtn.setBounds(equalX, equalY, btnWidth, btnHeight);
        equalBtn.setBackground(darkEqualColor);
        equalBtn.setForeground(Color.WHITE);
        equalBtn.setFont(new Font("Arial", Font.BOLD, 15));
        equalBtn.setFocusable(false);
        mainPanel.add(equalBtn);

        add(mainPanel);
    }

    public String getDisplayText() {
        return displayField.getText();
    }

    public void setDisplayText(String text) {
        displayField.setText(text);
    }

    public void addCalculatorListener(ActionListener listener) {
        for (int row = 0; row < gridButtons.length; row++) {
            for (int col = 0; col < gridButtons[row].length; col++) {
                gridButtons[row][col].addActionListener(listener);
            }
        }
        equalBtn.addActionListener(listener);
    }

    public void showHistoryDialog(List<String> historyList) {
        if (historyList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No history available.", "History", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder historyText = new StringBuilder();
        for (int i = 0; i < historyList.size(); i++) {
            historyText.append(i + 1).append(". ").append(historyList.get(i)).append("\n");
        }

        JTextArea textArea = new JTextArea(historyText.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JOptionPane.showMessageDialog(this, scrollPane, "Calculation History", JOptionPane.INFORMATION_MESSAGE);
    }
}