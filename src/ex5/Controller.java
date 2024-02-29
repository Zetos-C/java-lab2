package ex5;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

public class Controller implements ActionListener {
	View view;
	String text = "";
	
	public Controller(View view) {
		super();
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==view.openButton) {
			int show = view.fileChoose.showOpenDialog(view);
			if (show==JFileChooser.APPROVE_OPTION) {
				view.textArea.setText("");
				text = "";
				try {
					FileInputStream fileIn = new FileInputStream(view.fileChoose.getSelectedFile());
					int content;
					while ((content = fileIn.read()) != -1) {
						 text += (char)content;
					}
					view.textArea.setText(text);
					fileIn.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
