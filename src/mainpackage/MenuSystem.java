package mainpackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import menuwindowspackage.*;
import reportpackage.WarningReporter;

/**
 * MenuSystem provides a menu bar for the main tool with File, Project and Help as options.
 * 
 * File (shortcut Alt+F) contains 'New' to open a new tool window, 'Exit this File' to quit
 * the selected tool window and 'Exit All' to close all the windows.
 * 
 * Project (shortcut Alt+P) contains 'Set Background' to change background colour and 
 * 'Show Interface' to enable or disable the visibility of the GUI elements.
 * 
 * Help (shortcut Alt+H) contains 'Commands' and 'More Commands' for details about the 
 * commands.
 */
public class MenuSystem {
	JMenuBar menuBar;
	JMenuItem newFile, exit, exitAll, setBackground, commands, moreCommands;
	JCheckBoxMenuItem showInterface;
	
	
	public MenuSystem() {
		menuBar = new JMenuBar();
		JMenu file, project, help;
		
		// For File menu
		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		newFile = new JMenuItem("New", new ImageIcon("resources/newIcon.png"));
		exit = new JMenuItem("Exit This File", new ImageIcon("resources/exitIcon.png"));
		exitAll = new JMenuItem("Exit All", new ImageIcon("resources/exitAllIcon.png"));
		file.add(newFile);
		file.addSeparator();
		file.add(exit);
		file.add(exitAll);
		
		// For Project menu
		project = new JMenu("Project");
		project.setMnemonic(KeyEvent.VK_P);
		setBackground = new JMenuItem("Set Background");
		showInterface = new JCheckBoxMenuItem("Show Interface");
		showInterface.setSelected(true);
		project.add(setBackground);
		project.addSeparator();
		project.add(showInterface);
		
		// For Help menu
		help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		commands = new JMenuItem("Commands", new ImageIcon("resources/helpIcon.png"));
		moreCommands = new JMenuItem("More Commands", new ImageIcon("resources/helpPlusIcon.png"));
		help.add(commands);
		help.add(moreCommands);
		
		menuBar.add(file);
		menuBar.add(project);
		menuBar.add(help);
		
		OptionListener listener1 = new OptionListener();
		newFile.addActionListener(listener1);
		exit.addActionListener(listener1);
		exitAll.addActionListener(listener1);
		setBackground.addActionListener(listener1);
		commands.addActionListener(listener1);
		moreCommands.addActionListener(listener1);
		
		CheckBoxListener listener2 = new CheckBoxListener();
		showInterface.addItemListener(listener2);
	}
	
	
	private class OptionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			DocumentOpener opener = new DocumentOpener();
			WarningReporter warningReporter = new WarningReporter();
			
			// Checks the menu that user chose.
			if (newFile == e.getSource()) {
				// Runs a new tool window.
				MainClass.main(null);
			} else if (exit == e.getSource()) {
				// Finds the currently active frame.
				Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
		        Window window = SwingUtilities.getWindowAncestor(focusOwner);
		        JFrame frame = (JFrame) window;
		        
		        // Closes the active frame.
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			} else if (exitAll == e.getSource()) {
				// Triggers warningReporter to warn closing all.
				warningReporter.warnExitAll();
			} else if (setBackground == e.getSource()){
				// Triggers warningReporter to warn current progress's erasure.
				warningReporter.warnErasure();
				
				// Opens a window to choose colour.
				JFrame frame = new JFrame("Choose Colour");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.add(new SetBackgroundPanel());
				frame.pack();
			    frame.setVisible(true);
			} else if (commands == e.getSource()) {
				// Opens doc file of commands.
				opener.open("commands");
			} else if (moreCommands == e.getSource()) {
				// Opens doc file of more commands.
				opener.open("moreCommands");
			}
		}
	}
	
	
	private class CheckBoxListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			MainClass mainClassObj = new MainClass();
			GraphicsSystem currentSystem = mainClassObj.getCurrentSystem();
			
			// Checks if 'Show Interface' is checked or not
			if(showInterface.isSelected())
				// Shows the GUI elements.
				currentSystem.setGUIVisible(true);
			else
				// Hides the GUI elements.
				currentSystem.setGUIVisible(false);
		}
	}
	
	
	/**
	 * Getter for menuBar
	 * 
	 * @return the menu bar created.
	 */
	public JMenuBar getMenuBar() {
		return menuBar;
	}
}
