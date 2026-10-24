import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentMarksAnalyser extends JFrame implements ActionListener {
    JTextField nameField, rollField, m1Field, m2Field, m3Field;
    JButton calculateButton;
    DefaultTableModel model;

    StudentMarksAnalyser() {
        setTitle("Student Marks Analyser");
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 4, 8, 8));

        nameField = new JTextField();
        rollField = new JTextField();
        m1Field = new JTextField();
        m2Field = new JTextField();
        m3Field = new JTextField();

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Roll No:"));
        formPanel.add(rollField);

        formPanel.add(new JLabel("Mark 1:"));
        formPanel.add(m1Field);
        formPanel.add(new JLabel("Mark 2:"));
        formPanel.add(m2Field);

        formPanel.add(new JLabel("Mark 3:"));
        formPanel.add(m3Field);
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));

        calculateButton = new JButton("Calculate");
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(calculateButton);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        String[] columns = {
            "Name", "Roll No", "Mark 1", "Mark 2",
            "Mark 3", "Total", "Average", "Grade"
        };

        model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        calculateButton.addActionListener(this);

        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim();
        String roll = rollField.getText().trim();

        if (name.isEmpty() || roll.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter student details.");
            return;
        }

        try {
            int m1 = Integer.parseInt(m1Field.getText());
            int m2 = Integer.parseInt(m2Field.getText());
            int m3 = Integer.parseInt(m3Field.getText());

            if (m1 < 0 || m1 > 100 || m2 < 0 || m2 > 100
                    || m3 < 0 || m3 > 100) {
                JOptionPane.showMessageDialog(this,
                        "Marks must be between 0 and 100.");
                return;
            }

            int total = m1 + m2 + m3;
            double average = total / 3.0;
            String grade;

            if (average >= 90)
                grade = "A";
            else if (average >= 80)
                grade = "B";
            else if (average >= 70)
                grade = "C";
            else if (average >= 60)
                grade = "D";
            else if (average >= 50)
                grade = "E";
            else
                grade = "F";

            model.addRow(new Object[]{
                name, roll, m1, m2, m3, total,
                String.format("%.2f", average), grade
            });

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid marks.");
        }
    }

    public static void main(String[] args) {
        new StudentMarksAnalyser();
    }
}