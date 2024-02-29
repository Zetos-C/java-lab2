package ex9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputPanel extends JPanel {
    private JTextField nameField, idField, gpaField;
    JButton addButton;

    public InputPanel() {
        setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(10);
        add(nameLabel);
        add(nameField);

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField(5);
        add(idLabel);
        add(idField);

        JLabel gpaLabel = new JLabel("GPA:");
        gpaField = new JTextField(5);
        add(gpaLabel);
        add(gpaField);

        addButton = new JButton("Add Student");
        add(addButton);
    }

    public String getNameField() {
        return nameField.getText();
    }

    public int getIDField() {
        return Integer.parseInt(idField.getText());
    }

    public double getGPAField() {
        return Double.parseDouble(gpaField.getText());
    }

    public void addAddButtonListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }
}
