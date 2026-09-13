import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class studentinfo extends JFrame {

    private JTextField nameField;
    private JTextField fatherNameField;
    private JTextField rollNoField;
    private JTextField branchField;
    private JTextField yearField;
    private JButton submitButton;

    public studentinfo() {
        // Frame Settings
        setTitle("Student Information Form");
        setSize(600, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel with background color
        JPanel panel = new JPanel();
        panel.setBackground(new Color(88, 132, 169)); // Blue-gray shade matching the UI
        panel.setLayout(null); // Absolute positioning layout

        // Main Title Header
        JLabel headerLabel = new JLabel("Student Registration Form By Vivek Garg - 24EARCS186");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 15));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBounds(60, 25, 480, 25);
        panel.add(headerLabel);

        // Field Labels Font & Color
        Font labelFont = new Font("Arial", Font.BOLD, 13);

        // 1. Student Name
        JLabel nameLabel = new JLabel("Name of Student:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(60, 80, 140, 25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(210, 80, 310, 25);
        panel.add(nameField);

        // 2. Father's Name
        JLabel fatherNameLabel = new JLabel("Father's Name:");
        fatherNameLabel.setFont(labelFont);
        fatherNameLabel.setForeground(Color.WHITE);
        fatherNameLabel.setBounds(60, 120, 140, 25);
        panel.add(fatherNameLabel);

        fatherNameField = new JTextField();
        fatherNameField.setBounds(210, 120, 310, 25);
        panel.add(fatherNameField);

        // 3. Roll Number
        JLabel rollNoLabel = new JLabel("Roll Number:");
        rollNoLabel.setFont(labelFont);
        rollNoLabel.setForeground(Color.WHITE);
        rollNoLabel.setBounds(60, 160, 140, 25);
        panel.add(rollNoLabel);

        rollNoField = new JTextField();
        rollNoField.setBounds(210, 160, 310, 25);
        panel.add(rollNoField);

        // 4. Branch
        JLabel branchLabel = new JLabel("Branch:");
        branchLabel.setFont(labelFont);
        branchLabel.setForeground(Color.WHITE);
        branchLabel.setBounds(60, 200, 140, 25);
        panel.add(branchLabel);

        branchField = new JTextField();
        branchField.setBounds(210, 200, 310, 25);
        panel.add(branchField);

        // 5. Year
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(labelFont);
        yearLabel.setForeground(Color.WHITE);
        yearLabel.setBounds(60, 240, 140, 25);
        panel.add(yearLabel);

        yearField = new JTextField();
        yearField.setBounds(210, 240, 310, 25);
        panel.add(yearField);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(210, 300, 110, 28);
        submitButton.setFocusable(false);
        panel.add(submitButton);

        // Action Listener for Submit Button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String fatherName = fatherNameField.getText();
                String rollNo = rollNoField.getText();
                String branch = branchField.getText();
                String year = yearField.getText();

                // Build output message string
                String message = "Submitted Information:\n\n" +
                        "Student Name: " + name + "\n" +
                        "Father's Name: " + fatherName + "\n" +
                        "Roll Number: " + rollNo + "\n" +
                        "Branch: " + branch + "\n" +
                        "Year: " + year;

                // Show dialog box
                JOptionPane.showMessageDialog(
                        studentinfo.this,
                        message,
                        "Submission Details",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new studentinfo().setVisible(true);
        });
    }
}