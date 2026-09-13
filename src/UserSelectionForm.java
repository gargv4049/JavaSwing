import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserSelectionForm extends JFrame {

    private JCheckBox cricketCheck, footballCheck, gamingCheck;
    private JRadioButton maleRadio, femaleRadio, otherRadio;
    private ButtonGroup genderGroup;
    private JComboBox<String> countryCombo;
    private JButton submitButton;

    public UserSelectionForm() {
        // Window Configuration
        setTitle("User Selection Form");
        setSize(550, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main Panel layout
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Header Title Label
        JLabel titleLabel = new JLabel("Student Registration Form By Vivek Garg 24EARCS186");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 13));
        titleLabel.setBounds(40, 20, 480, 25);
        panel.add(titleLabel);

        Font labelFont = new Font("Arial", Font.BOLD, 12);

        // 1. Hobbies Selection (Checkboxes)
        JLabel hobbiesLabel = new JLabel("Select Hobbies:");
        hobbiesLabel.setFont(labelFont);
        hobbiesLabel.setBounds(40, 65, 120, 25);
        panel.add(hobbiesLabel);

        cricketCheck = new JCheckBox("Cricket");
        cricketCheck.setBounds(170, 65, 80, 25);
        panel.add(cricketCheck);

        footballCheck = new JCheckBox("Football");
        footballCheck.setBounds(255, 65, 85, 25);
        panel.add(footballCheck);

        gamingCheck = new JCheckBox("Gaming");
        gamingCheck.setBounds(350, 65, 80, 25);
        panel.add(gamingCheck);

        // 2. Gender Selection (Radio Buttons)
        JLabel genderLabel = new JLabel("Select Gender:");
        genderLabel.setFont(labelFont);
        genderLabel.setBounds(40, 115, 120, 25);
        panel.add(genderLabel);

        maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(170, 115, 75, 25);
        femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(250, 115, 80, 25);
        otherRadio = new JRadioButton("Other");
        otherRadio.setBounds(340, 115, 75, 25);

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);

        panel.add(maleRadio);
        panel.add(femaleRadio);
        panel.add(otherRadio);

        // 3. Country Selection (ComboBox)
        JLabel countryLabel = new JLabel("Select Country:");
        countryLabel.setFont(labelFont);
        countryLabel.setBounds(40, 165, 120, 25);
        panel.add(countryLabel);

        String[] countries = {"India", "USA", "UK", "Australia", "Canada"};
        countryCombo = new JComboBox<>(countries);
        countryCombo.setBounds(170, 165, 250, 25);
        panel.add(countryCombo);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(210, 225, 120, 30);
        submitButton.setFocusable(false);
        panel.add(submitButton);

        // Event Handling for Submit Button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect checked hobbies
                ArrayList<String> hobbiesList = new ArrayList<>();
                if (cricketCheck.isSelected()) hobbiesList.add("Cricket");
                if (footballCheck.isSelected()) hobbiesList.add("Football");
                if (gamingCheck.isSelected()) hobbiesList.add("Gaming");
                String hobbiesStr = hobbiesList.isEmpty() ? "None" : String.join(", ", hobbiesList);

                // Collect selected gender
                String genderStr = "Not Selected";
                if (maleRadio.isSelected()) {
                    genderStr = "Male";
                } else if (femaleRadio.isSelected()) {
                    genderStr = "Female";
                } else if (otherRadio.isSelected()) {
                    genderStr = "Other";
                }

                // Collect selected country
                String countryStr = (String) countryCombo.getSelectedItem();

                // Format display message
                String message = "Hobbies : " + hobbiesStr + "\n" +
                        "Gender : " + genderStr + "\n" +
                        "Country : " + countryStr;

                // Display dialog popup
                JOptionPane.showMessageDialog(
                        UserSelectionForm.this,
                        message,
                        "Registration Details",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserSelectionForm().setVisible(true);
        });
    }
}