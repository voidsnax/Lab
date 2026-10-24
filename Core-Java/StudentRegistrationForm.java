import java.awt.*;
import java.awt.event.*;

public class StudentRegistrationForm extends Frame implements ActionListener {
    TextField nameField, emailField;
    CheckboxGroup genderGroup;
    Checkbox agreeCheck;
    Choice courseChoice;
    TextArea addressArea, resultArea;
    Button submitButton, clearButton;

    StudentRegistrationForm() {
        setTitle("Student Registration Form");
        setLayout(new GridLayout(8, 2, 8, 8));

        nameField = new TextField();
        emailField = new TextField();

        genderGroup = new CheckboxGroup();
        Panel genderPanel = new Panel(new GridLayout(1, 3, 5, 0));

        genderPanel.add(new Checkbox("Male", genderGroup, false));
        genderPanel.add(new Checkbox("Female", genderGroup, false));
        genderPanel.add(new Checkbox("Other", genderGroup, false));

        courseChoice = new Choice();
        courseChoice.add("Select Course");
        courseChoice.add("BCA");
        courseChoice.add("B.Sc. Computer Science");
        courseChoice.add("M.Sc. Computer Science");

        addressArea = new TextArea("", 3, 20);
        agreeCheck = new Checkbox("I agree to the terms");

        submitButton = new Button("Submit");
        clearButton = new Button("Clear");

        resultArea = new TextArea("", 4, 20);
        resultArea.setEditable(false);

        add(new Label("Name:"));
        add(nameField);

        add(new Label("Email:"));
        add(emailField);

        add(new Label("Gender:"));
        add(genderPanel);

        add(new Label("Course:"));
        add(courseChoice);

        add(new Label("Address:"));
        add(addressArea);

        add(new Label("Agreement:"));
        add(agreeCheck);

        add(submitButton);
        add(clearButton);

        add(new Label("Submitted Details:"));
        add(resultArea);

        submitButton.addActionListener(this);
        clearButton.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(550, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String address = addressArea.getText().trim();
            Checkbox gender = genderGroup.getSelectedCheckbox();

            if (name.isEmpty() || email.isEmpty() || address.isEmpty()
                    || gender == null || courseChoice.getSelectedIndex() == 0) {
                resultArea.setText("Please fill in all fields.");
                return;
            }

            if (!email.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) {
                resultArea.setText("Enter a valid email address.");
                return;
            }

            if (!agreeCheck.getState()) {
                resultArea.setText("Please agree to the terms.");
                return;
            }

            resultArea.setText(
                "Registration Successful!\n"
                + "Name: " + name + "\n"
                + "Email: " + email + "\n"
                + "Gender: " + gender.getLabel() + "\n"
                + "Course: " + courseChoice.getSelectedItem() + "\n"
                + "Address: " + address
            );
            resultArea.setCaretPosition(0);

        } else if (e.getSource() == clearButton) {
            nameField.setText("");
            emailField.setText("");
            addressArea.setText("");
            genderGroup.setSelectedCheckbox(null);
            courseChoice.select(0);
            agreeCheck.setState(false);
            resultArea.setText("");
        }
    }

    public static void main(String[] args) {
        new StudentRegistrationForm();
    }
}