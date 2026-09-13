import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentDAO dao = new StudentDAO();
            StudentView view = new StudentView();
            new StudentController(dao, view);
            view.setVisible(true);
        });
    }
}