import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class TextEditor extends JFrame implements ActionListener {
    JTextArea textArea;
    File currentFile;

    TextEditor() {
        setTitle("Simple Text Editor");
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        String[] items = { "New", "Open", "Save", "Exit" };

        for (String item : items) {
            JMenuItem menuItem = new JMenuItem(item);
            menuItem.addActionListener(this);
            fileMenu.add(menuItem);
        }

        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            switch (command) {
                case "New":
                    textArea.setText("");
                    currentFile = null;
                    break;

                case "Open":
                    JFileChooser openChooser = new JFileChooser();
                    if (openChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                        File file = openChooser.getSelectedFile();

                        try (FileReader reader = new FileReader(file)) {
                            textArea.read(reader, null);
                            currentFile = file;
                        }
                    }
                    break;

                case "Save":
                    if (currentFile == null) {
                        JFileChooser saveChooser = new JFileChooser();

                        if (saveChooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
                            break;
                        }

                        currentFile = saveChooser.getSelectedFile();
                    }

                    try (FileWriter writer = new FileWriter(currentFile)) {
                        textArea.write(writer);
                    }

                    JOptionPane.showMessageDialog(this, "File saved successfully!");
                    break;

                case "Exit":
                    System.exit(0);
                    break;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new TextEditor();
    }
}