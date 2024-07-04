package reportpackage;

import javax.swing.*;

/**
 * WarningReporter provides dialog boxes to warn the users.
 */
public class WarningReporter {
	/**
	 * Warns user while exiting.
	 * 
	 * @param mainFrame The frame user is exiting.
	 */
	public void warnExit(JFrame mainFrame) {
		int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit this file?", 
												   "Confirm Quit", 0);
		if (choice == JOptionPane.NO_OPTION)
			mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		else
			mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);;
	}
	
	
	/**
	 * Warns user while closing all.
	 */
	public void warnExitAll() {
		int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit all?", 
												   "Confirm Quit", 0);
		if (choice == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	
	/**
	 * Warns user when the turtle is out of frame.
	 */
	public void warnOutOfFrame() {
		JOptionPane.showMessageDialog(null, "The turtle is out of the frame. You can use position command to get back.",
									  "Turtle Out of Frame", JOptionPane.WARNING_MESSAGE, null);
	}
	
	
	/**
	 * Warns user of erasure of the current progress.
	 */
	public void warnErasure() {
		JOptionPane.showMessageDialog(null, "Any progress made till now will be removed if the background is changed.",
				  					  "Possible Erasure", JOptionPane.WARNING_MESSAGE, null);
	}
}
