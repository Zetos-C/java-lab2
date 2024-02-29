package ex9;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        String name = view.getStudentName();
        int id = view.getStudentID();
        double gpa = view.getStudentGPA();
        Student newStudent = new Student(name, id, gpa);
        students.add(newStudent);
        view.addStudent(name, id, gpa);
        saveStudents(); // Save students to file after adding a new student
    }

    // Load students from file
    private void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filename)))) {
            students = (List<Student>) ois.readObject();
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

