package saveloadpackage;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;

/**
 * LoadFilePanel provides panel that allows to select the file and its format to load.
 */
public class LoadFilePanel extends JPanel {
	private JComboBox filesCombo;
	private JButton button;
	private LoadHandler loader;
	private String selectedFile;
	private String[] fileNames;
	
	
	public LoadFilePanel() {
		loader = new LoadHandler();
		fileNames = getFileNames();
		
		JLabel label = new JLabel("Select the file you want to load:");
		filesCombo = new JComboBox(fileNames);
		button = new JButton("Ok");
		
		add(label);
		add(filesCombo);
		add(button);
		setPreferredSize(new Dimension(250,70));
		
		Listener listener = new Listener();
		
		filesCombo.addActionListener(listener);
		button.addActionListener(listener);
	}
	
	
	private class Listener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (filesCombo == e.getSource()) {
				selectedFile = fileNames[filesCombo.getSelectedIndex()];
			} else {
				closeCurrentWindow(e);
				
				// Provides a frame to select format of the file.
				JFrame frame = new JFrame();
				FormatSelectorPanel panel = new FormatSelectorPanel();
				
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setResizable(false);
				frame.add(panel);
				frame.pack();
				frame.setVisible(true);
			}
		}
    }
	
	
	private class FormatSelectorPanel extends JPanel {
		private JCheckBox image, text;
		
		public FormatSelectorPanel() {
			JLabel label = new JLabel("Select the format of the file:");
			
			image = new JCheckBox("Image");
			text = new JCheckBox("Text");
			button = new JButton("Ok");
			
			add(label);
			add(image);
			add(text);
			add(button);
			setPreferredSize(new Dimension(250, 70));
			
			ButtonListener listener = new ButtonListener();
			button.addActionListener(listener);
		}
		
		private class ButtonListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeCurrentWindow(e);

				if (image.isSelected() && text.isSelected()) {
					// Loads both image and text file.
					loader.loadImage(selectedFile);
					loader.loadText(selectedFile);
				} else if (image.isSelected()) {
					// Loads only the image file.
					loader.loadImage(selectedFile);
				} else if (text.isSelected()) {
					// Loads only the text file.
					loader.loadText(selectedFile);
				}
			}
		}
	}
	
	
	/**
	 * Getter for fileNames
	 * 
	 * @return the file names in the save folder.
	 */
	private String[] getFileNames() {
		String savePath = loader.getSavePath();
	    
	    File folder = new File(savePath);
	    File[] files = folder.listFiles();
	    int fileCount = files.length;
	    
	    String[] fileNames = new String[fileCount];
	    for (int i = 0; i < files.length; i++) {
	    	fileNames[i] = files[i].getName();
	    }
	    return fileNames;
	}
	
	
	/**
	 * Gets the active window and closes it.
	 * 
	 * @param e The actionevent.
	 */
	private void closeCurrentWindow(ActionEvent e){
		JComponent component = (JComponent) e.getSource();
		Window window = SwingUtilities.getWindowAncestor(component);
		
		window.dispose();
	}
}

