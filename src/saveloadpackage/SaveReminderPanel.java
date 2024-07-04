package saveloadpackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mainpackage.*;

/**
 * SaveReminderPanel provides a panel that reminds to save if the user tries to load
 *  without saving.
 */
public class SaveReminderPanel extends JPanel {
	private JButton yesButton, noButton;
	
	
	public SaveReminderPanel() {
		JLabel label = new JLabel("The current file isn't saved. Would you like to save it?");
		yesButton = new JButton("Yes");
		noButton = new JButton("No");
		
		add(label);
		add(yesButton);
		add(noButton);
		setPreferredSize(new Dimension(350,70));
		
		ButtonListener listener = new ButtonListener();
		
		yesButton.addActionListener(listener);
		noButton.addActionListener(listener);	
	}
	
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JComponent component = (JComponent) e.getSource();
			Window window = SwingUtilities.getWindowAncestor(component);
			
			// Closes the SaveReminderPanel's window.
			window.dispose();
			
			// Checks user decision to save or not.
			if (e.getSource() == yesButton) {
				MainClass mainClassObj = new MainClass();
				GraphicsSystem currentSystem = mainClassObj.getCurrentSystem();
				
				// Saves the file.
				currentSystem.save();
			} else {
				// Runs loading sequence without saving.
				LoadHandler loader = new LoadHandler();
				loader.runLoadSequence();
			}
		}
	}
}
