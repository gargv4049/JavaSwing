import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/jdbc_demo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "your_password"; // Update password here

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM student";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age")
                ));
            }
        }
        return students;
    }

    public boolean saveStudent(Student student) throws SQLException {
        String query = "INSERT INTO student (id, name, email, age) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getEmail());
            pstmt.setInt(4, student.getAge());

            return pstmt.executeUpdate() > 0;
        }
    }

    public Student getStudentById(int id) throws SQLException {
        String query = "SELECT * FROM student WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("age")
                );
            }
        }
        return null;
    }

    public boolean updateStudent(Student student) throws SQLException {
        String query = "UPDATE student SET name = ?, email = ?, age = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, student.getAge());
            pstmt.setInt(4, student.getId());

            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteStudent(int id) throws SQLException {
        String query = "DELETE FROM student WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }
}