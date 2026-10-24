import java.sql.*;
import java.util.Scanner;

public class StudentRecordManagement {
    static final String URL = "jdbc:mysql://localhost:3306/jdbc_demo";
    static final String USER = "test_user";
    static final String PASSWORD = "password123";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                Scanner sc = new Scanner(System.in)) {

            int choice;

            do {
                System.out.println("\n1. Insert Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        insertStudent(con, sc);
                        break;
                    case 2:
                        viewStudents(con);
                        break;
                    case 3:
                        updateStudent(con, sc);
                        break;
                    case 4:
                        deleteStudent(con, sc);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 5);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static void insertStudent(Connection con, Scanner sc)
            throws SQLException {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter age: ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Enter course: ");
        String course = sc.nextLine();

        String sql = "INSERT INTO students VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, course);

            ps.executeUpdate();
            System.out.println("Student inserted successfully.");
        }
    }

    static void viewStudents(Connection con) throws SQLException {
        String sql = "SELECT * FROM students";

        try (PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | "
                                + rs.getString("name") + " | "
                                + rs.getInt("age") + " | "
                                + rs.getString("course"));
            }
        }
    }

    static void updateStudent(Connection con, Scanner sc)
            throws SQLException {
        System.out.print("Enter ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter new name: ");
        String name = sc.nextLine();

        System.out.print("Enter new age: ");
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Enter new course: ");
        String course = sc.nextLine();

        String sql = "UPDATE students SET name=?, age=?, course=? WHERE id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setInt(4, id);

            if (ps.executeUpdate() > 0)
                System.out.println("Student updated successfully.");
            else
                System.out.println("Student not found.");
        }
    }

    static void deleteStudent(Connection con, Scanner sc)
            throws SQLException {
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        String sql = "DELETE FROM students WHERE id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0)
                System.out.println("Student deleted successfully.");
            else
                System.out.println("Student not found.");
        }
    }
}