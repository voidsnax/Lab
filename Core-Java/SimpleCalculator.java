import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends Frame implements ActionListener {
    TextField t1, t2, result;
    Button add, sub, mul, div;

    SimpleCalculator() {
        setTitle("Simple Calculator");
        setLayout(new GridLayout(5, 2, 5, 5));

        t1 = new TextField();
        t2 = new TextField();
        result = new TextField();
        result.setEditable(false);

        add = new Button("+");
        sub = new Button("-");
        mul = new Button("*");
        div = new Button("/");

        add(new Label("First Number:"));
        add(t1);
        add(new Label("Second Number:"));
        add(t2);
        add(new Label("Result:"));
        add(result);
        add(add);
        add(sub);
        add(mul);
        add(div);

        add.addActionListener(this);
        sub.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);

        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double a = Double.parseDouble(t1.getText());
            double b = Double.parseDouble(t2.getText());
            double ans = 0;

            switch (e.getActionCommand()) {
                case "+":
                    ans = a + b;
                    break;
                case "-":
                    ans = a - b;
                    break;
                case "*":
                    ans = a * b;
                    break;
                case "/":
                    if (b == 0) {
                        result.setText("Cannot divide by zero");
                        return;
                    }
                    ans = a / b;
                    break;
            }

            result.setText(String.valueOf(ans));

        } catch (NumberFormatException ex) {
            result.setText("Enter valid numbers");
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}