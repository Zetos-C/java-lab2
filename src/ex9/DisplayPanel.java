package ex9;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DisplayPanel extends JPanel {
    private static final long serialVersionUID = 1L;
	private JTable table;
    private DefaultTableModel model;
    private JRadioButton defaultRadioButton;
    private JRadioButton sortByGPARadioButton;

    public DisplayPanel() {
        setLayout(new BorderLayout());

        defaultRadioButton = new JRadioButton("Default");
        sortByGPARadioButton = new JRadioButton("Sort by GPA");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(defaultRadioButton);
        radioButtonGroup.add(sortByGPARadioButton);
        defaultRadioButton.setSelected(true);

        JPanel radioPanel = new JPanel();
        radioPanel.add(defaultRadioButton);
        radioPanel.add(sortByGPARadioButton);
        add(radioPanel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        model.addColumn("Name");
        model.addColumn("ID");
        model.addColumn("GPA");
    }

    public void addStudent(String name, int id, double gpa) {
        model.addRow(new Object[]{name, id, gpa});
    }

    public void setData(List<Student> students) {
        model.setRowCount(0);
        if (sortByGPARadioButton.isSelected()) {
            Collections.sort(students, Comparator.comparing(Student::getGpa));
        }
        for (Student student : students) {
            model.addRow(new Object[]{student.getName(), student.getId(), student.getGpa()});
        }
    }

}
