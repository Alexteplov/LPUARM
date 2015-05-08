package appl;

import javax.swing.JFrame;

import form.LoginWindow;

public class indexForm {
	public indexForm() {
		JFrame myWindow = new LoginWindow();
		myWindow.validate();
		myWindow.setVisible(true);
		//System.out.println("Hello");
	}
	
}
