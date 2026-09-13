import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentController {

    private final StudentDAO dao;
    private final StudentView view;

    public StudentController(StudentDAO dao, StudentView view) {
        this.dao = dao;
        this.view = view;

        // Register action listeners
        this.view.addSaveListener(e -> handleSave());
        this.view.addSearchListener(e -> handleSearch());
        this.view.addUpdateListener(e -> handleUpdate());
        this.view.addDeleteListener(e -> handleDelete());

        // Load initial data
        loadAllStudents();
    }

    private void loadAllStudents() {
        try {
            List<Student> students = dao.getAllStudents();
            view.setTableData(students);
            view.setStatus(students.size() + " students loaded.");
        } catch (SQLException ex) {
            view.setStatus("Database connection error!");
        }
    }

    private void handleSave() {
        try {
            int id = Integer.parseInt(view.getIdInput());
            String name = view.getNameInput();
            String email = view.getEmailInput();
            int age = Integer.parseInt(view.getAgeInput());

            Student student = new Student(id, name, email, age);
            if (dao.saveStudent(student)) {
                loadAllStudents();
                view.clearFields();
                view.setStatus("Student record saved successfully.");
            } else {
                view.setStatus("Error saving record.");
            }
        } catch (Exception ex) {
            view.setStatus("Invalid input or duplicate ID.");
        }
    }

    private void handleSearch() {
        String searchIdText = view.getSearchIdInput();
        if (searchIdText.isEmpty()) {
            loadAllStudents();
            return;
        }

        try {
            int id = Integer.parseInt(searchIdText);
            Student student = dao.getStudentById(id);

            if (student != null) {
                List<Student> singleList = new ArrayList<>();
                singleList.add(student);
                view.setTableData(singleList);
                view.setFormFields(student);
                view.setStatus("Student found.");
            } else {
                view.setTableData(new ArrayList<>());
                view.setStatus("Student not found.");
            }
        } catch (Exception ex) {
            view.setStatus("Student not found.");
        }
    }

    private void handleUpdate() {
        try {
            int id = Integer.parseInt(view.getIdInput());
            String name = view.getNameInput();
            String email = view.getEmailInput();
            int age = Integer.parseInt(view.getAgeInput());

            Student student = new Student(id, name, email, age);
            if (dao.updateStudent(student)) {
                loadAllStudents();
                view.clearFields();
                view.setStatus("Student record updated.");
            } else {
                view.setStatus("Student not found for update.");
            }
        } catch (Exception ex) {
            view.setStatus("Error updating record.");
        }
    }

    private void handleDelete() {
        String searchIdText = view.getSearchIdInput();
        if (searchIdText.isEmpty()) {
            searchIdText = view.getIdInput();
        }

        if (searchIdText.isEmpty()) {
            view.setStatus("Please enter Search ID to delete.");
            return;
        }

        try {
            int id = Integer.parseInt(searchIdText);
            if (dao.deleteStudent(id)) {
                loadAllStudents();
                view.clearFields();
                view.setStatus("Student deleted successfully.");
            } else {
                view.setStatus("Student not found.");
            }
        } catch (Exception ex) {
            view.setStatus("Error deleting record.");
        }
    }
}