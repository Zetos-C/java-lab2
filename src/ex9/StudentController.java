package ex9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class StudentController implements ActionListener {
	private StudentView view;
	private List<Student> students;
	private String filename = "student.dat"; // File name to store student data

	public StudentController(StudentView view) {
		this.view = view;
		this.students = new ArrayList<>();
		File file = new File(filename);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		loadStudents(); // Load students from file when controller is created
		for (Student student : students) {
			this.view.addStudent(student.getName(), student.getId(), student.getGpa());
		}
		view.setDisplayData(students);
	}

	private boolean isStudentExists(int id) {
		for (Student student : students) {
			if (student.getId() == id) {
				return true;
			}
		}
		return false;
	}

	public List<Student> findStudentsByName(String name) {
		List<Student> foundStudents = new ArrayList<>();
		for (Student student : students) {
			if (student.getName().equalsIgnoreCase(name)) {
				foundStudents.add(student);
			}
		}
		return foundStudents;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// When add button is clicked, add a new student to the list and update the view
		if (e.getSource() == view.inputPanel.addButton) {
			String name = view.getStudentName();
		    int id = view.getStudentID();
		    double gpa = view.getStudentGPA();
		    
		    if (!isStudentExists(id)) {
		        Student newStudent = new Student(name, id, gpa);
		        students.add(newStudent);
		        view.addStudent(name, id, gpa);
		        saveStudents();
		    } else {
		        JOptionPane.showMessageDialog(view, "Sinh viên đã tồn tại trong danh sách.");
		    }
		}
		if (e.getSource() == view.displayPanel.sortByGPARadioButton) {
			view.displayPanel.setData(students);
		}
	}

	// Load students from file
	private void loadStudents() {
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
	        List<Student> loadedStudents = (List<Student>) ois.readObject();
	        for (Student student : loadedStudents) {
	            if (!isStudentExists(student.getId())) {
	                students.add(student);
	            }
	        }
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	// Save students to file
	private void saveStudents() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filename)))) {
			oos.writeObject(students);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Get student data to initialize the view
	private List<Student> getStudentData() {
		return students;
	}

	public static void main(String[] args) {
		StudentView view = new StudentView();
		new StudentController(view);
		view.setVisible(true);
	}
}
