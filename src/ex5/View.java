package ex5;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Controller controller = new Controller(this);
	JFileChooser fileChoose = new JFileChooser();
	JButton openButton = new JButton("Open..");
	TextArea textArea = new TextArea("");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		textArea.setEditable(false);
		textArea.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		contentPane.add(textArea);
		openButton.addActionListener(controller);
		contentPane.add(openButton, BorderLayout.SOUTH);
	}
}
