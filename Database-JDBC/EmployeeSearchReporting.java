import java.sql.*;
import java.util.Scanner;

public class EmployeeSearchReporting {
    static final String URL = "jdbc:mysql://localhost:3306/jdbc_demo";
    static final String USER = "test_user";
    static final String PASSWORD = "password123";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner sc = new Scanner(System.in)) {

            int choice;

            do {
                System.out.println("\n1. Search by ID");
                System.out.println("2. Search by Department");
                System.out.println("3. Salary Report");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        searchById(con, sc);
                        break;

                    case 2:
                        searchByDepartment(con, sc);
                        break;

                    case 3:
                        salaryReport(con, sc);
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 4);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static void searchById(Connection con, Scanner sc)
            throws SQLException {
        System.out.print("Enter employee ID: ");
        int id = Integer.parseInt(sc.nextLine());

        String sql = "SELECT * FROM employees WHERE id = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                displayEmployees(rs);
            }
        }
    }

    static void searchByDepartment(Connection con, Scanner sc)
            throws SQLException {
        System.out.print("Enter department: ");
        String department = sc.nextLine();

        String sql = "SELECT * FROM employees WHERE department = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, department);

            try (ResultSet rs = ps.executeQuery()) {
                displayEmployees(rs);
            }
        }
    }

    static void salaryReport(Connection con, Scanner sc)
            throws SQLException {
        System.out.print("Enter minimum salary: ");
        double salary = Double.parseDouble(sc.nextLine());

        String sql = "SELECT * FROM employees WHERE salary > ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, salary);

            try (ResultSet rs = ps.executeQuery()) {
                displayEmployees(rs);
            }
        }
    }

    static void displayEmployees(ResultSet rs) throws SQLException {
        boolean found = false;

        while (rs.next()) {
            found = true;

            System.out.println(
                rs.getInt("id") + " | "
                + rs.getString("name") + " | "
                + rs.getString("department") + " | "
                + rs.getDouble("salary")
            );
        }

        if (!found)
            System.out.println("No employees found.");
    }
}