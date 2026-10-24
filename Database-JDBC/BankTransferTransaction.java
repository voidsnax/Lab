import java.sql.*;
import java.util.Scanner;

public class BankTransferTransaction {
    static final String URL = "jdbc:mysql://localhost:3306/jdbc_demo";
    static final String USER = "test_user";
    static final String PASSWORD = "password123";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter sender account ID: ");
            int fromId = sc.nextInt();

            System.out.print("Enter receiver account ID: ");
            int toId = sc.nextInt();

            System.out.print("Enter transfer amount: ");
            double amount = sc.nextDouble();

            if (amount <= 0 || fromId == toId) {
                System.out.println("Invalid transfer details.");
                return;
            }

            transfer(con, fromId, toId, amount);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    static void transfer(Connection con, int fromId, int toId, double amount)
            throws SQLException {

        con.setAutoCommit(false);

        try {
            String debit = "UPDATE accounts SET balance = balance - ? "
                         + "WHERE id = ? AND balance >= ?";

            try (PreparedStatement ps = con.prepareStatement(debit)) {
                ps.setDouble(1, amount);
                ps.setInt(2, fromId);
                ps.setDouble(3, amount);

                if (ps.executeUpdate() == 0)
                    throw new SQLException(
                        "Sender not found or insufficient balance.");
            }

            String credit = "UPDATE accounts SET balance = balance + ? "
                          + "WHERE id = ?";

            try (PreparedStatement ps = con.prepareStatement(credit)) {
                ps.setDouble(1, amount);
                ps.setInt(2, toId);

                if (ps.executeUpdate() == 0)
                    throw new SQLException("Receiver account not found.");
            }

            con.commit();
            System.out.println("Transfer successful.");

        } catch (SQLException e) {
            con.rollback();
            System.out.println("Transfer failed: " + e.getMessage());
            System.out.println("Transaction rolled back.");

        }
    }
}