package menuwindowspackage;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import mainpackage.*;

/**
 * SetBackgroundPanel provides a colour choosing panel for setting the background colour.
 */
public class SetBackgroundPanel extends JPanel{
	JColorChooser chooser;
	
	
	public SetBackgroundPanel() {
		chooser = new JColorChooser();
		JButton button = new JButton("Ok");
		
		add(chooser);
		add(button);
		setPreferredSize(new Dimension(650,380));
		
		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
	}
	
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			MainClass mainClassObj = new MainClass();
			GraphicsSystem currentSystem = mainClassObj.getCurrentSystem();
			
			// Sets the background colour to chosen colour.
			currentSystem.setBackground_Col(chooser.getColor());
			currentSystem.clear();
		}
	}
}
