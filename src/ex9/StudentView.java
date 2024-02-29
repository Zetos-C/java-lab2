package ex9;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentView extends JFrame {
	private static final long serialVersionUID = 1L;
	DisplayPanel displayPanel;
	InputPanel inputPanel;
	StudentController controller;

	public StudentView() {
        setTitle("Student Management System");
        displayPanel = new DisplayPanel();
    	inputPanel = new InputPanel();
    	controller = new StudentController(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(displayPanel, BorderLayout.CENTER);

        inputPanel.addButton.addActionListener(controller);
        getContentPane().add(inputPanel, BorderLayout.NORTH);

        setSize(548, 300);
        setLocationRelativeTo(null);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu mnAction = new JMenu("Action");
        menuBar.add(mnAction);
        
        JMenuItem mntmFindByName = new JMenuItem("Find by Name");
        mntmFindByName.addActionListener(e -> findByName());
        mnAction.add(mntmFindByName);
    }

	private void findByName() {
		String nameToFind = JOptionPane.showInputDialog(this, "Enter name to find:");
		List<Student> foundStudents = controller.findStudentsByName(nameToFind);
		if (foundStudents.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No students found with the given name.");
		} else {
			displayPanel.setData(foundStudents);
		}
	}

	public void setDisplayData(List<Student> students) {
		displayPanel.setData(students);
	}

	public String getStudentName() {
		return inputPanel.getNameField();
	}

	public int getStudentID() {
		return inputPanel.getIDField();
	}

	public double getStudentGPA() {
		return inputPanel.getGPAField();
	}

	public void addStudent(String name, int id, double gpa) {
		displayPanel.addStudent(name, id, gpa);
	}
}
