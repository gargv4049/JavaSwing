import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentView extends JFrame {

    private JTextField idField, nameField, emailField, ageField, searchField;
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private JButton saveButton, searchButton, updateButton, deleteButton;

    public StudentView() {
        setTitle("Student Management System");
        setSize(780, 460);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);

        Color headerBg = new Color(38, 43, 48);
        Color buttonBg = new Color(25, 85, 155);
        Color panelBg = new Color(235, 237, 240);

        getContentPane().setBackground(panelBg);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 780, 40);
        headerPanel.setBackground(headerBg);
        headerPanel.setLayout(null);

        JLabel titleLabel = new JLabel("STUDENT MANAGEMENT");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(20, 8, 250, 24);
        headerPanel.add(titleLabel);

        JLabel subTitleLabel = new JLabel("JDBC CRUD APPLICATION");
        subTitleLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        subTitleLabel.setForeground(new Color(180, 190, 200));
        subTitleLabel.setBounds(630, 12, 140, 20);
        headerPanel.add(subTitleLabel);

        add(headerPanel);

        // Left Form Inputs
        Font labelFont = new Font("Arial", Font.BOLD, 11);

        JLabel idLabel = new JLabel("Student ID");
        idLabel.setFont(labelFont);
        idLabel.setBounds(20, 60, 80, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 60, 120, 25);
        add(idField);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(labelFont);
        nameLabel.setBounds(20, 105, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 105, 120, 25);
        add(nameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(labelFont);
        emailLabel.setBounds(20, 150, 80, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 150, 120, 25);
        add(emailField);

        JLabel ageLabel = new JLabel("Age");
        ageLabel.setFont(labelFont);
        ageLabel.setBounds(20, 195, 80, 25);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(100, 195, 120, 25);
        add(ageField);

        // Buttons
        saveButton = createButton("SAVE", buttonBg, 20, 245, 90, 30);
        add(saveButton);

        // Right Table
        String[] columnNames = {"ID", "NAME", "EMAIL", "AGE"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        studentTable.getTableHeader().setBackground(new Color(50, 55, 60));
        studentTable.getTableHeader().setForeground(Color.WHITE);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));
        studentTable.setRowHeight(22);

        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(245, 60, 500, 220);
        add(scrollPane);

        // Bottom Bar
        JLabel searchLabel = new JLabel("Search ID");
        searchLabel.setFont(labelFont);
        searchLabel.setBounds(245, 300, 70, 25);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(315, 300, 120, 25);
        add(searchField);

        searchButton = createButton("SEARCH", buttonBg, 445, 300, 90, 25);
        add(searchButton);

        updateButton = createButton("UPDATE", buttonBg, 545, 300, 90, 25);
        add(updateButton);

        deleteButton = createButton("DELETE", buttonBg, 645, 300, 90, 25);
        add(deleteButton);

        // Status Label
        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 11));
        statusLabel.setBounds(20, 375, 300, 20);
        add(statusLabel);
    }

    private JButton createButton(String text, Color bg, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 10));
        button.setFocusable(false);
        return button;
    }

    // Input Getter Methods
    public String getIdInput() { return idField.getText().trim(); }
    public String getNameInput() { return nameField.getText().trim(); }
    public String getEmailInput() { return emailField.getText().trim(); }
    public String getAgeInput() { return ageField.getText().trim(); }
    public String getSearchIdInput() { return searchField.getText().trim(); }

    // UI Field Updaters
    public void setFormFields(Student student) {
        if (student != null) {
            idField.setText(String.valueOf(student.getId()));
            nameField.setText(student.getName());
            emailField.setText(student.getEmail());
            ageField.setText(String.valueOf(student.getAge()));
        }
    }

    public void clearFields() {
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        ageField.setText("");
        searchField.setText("");
    }

    public void setTableData(List<Student> students) {
        tableModel.setRowCount(0);
        for (Student s : students) {
            tableModel.addRow(new Object[]{s.getId(), s.getName(), s.getEmail(), s.getAge()});
        }
    }

    public void setStatus(String message) {
        statusLabel.setText(message);
    }

    // Event Listener Binding Methods
    public void addSaveListener(ActionListener listener) { saveButton.addActionListener(listener); }
    public void addSearchListener(ActionListener listener) { searchButton.addActionListener(listener); }
    public void addUpdateListener(ActionListener listener) { updateButton.addActionListener(listener); }
    public void addDeleteListener(ActionListener listener) { deleteButton.addActionListener(listener); }
}