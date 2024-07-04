package saveloadpackage;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import mainpackage.*;

/**
 * LoadHandler handles the loading sequence of the program.
 */
public class LoadHandler extends JPanel {
	private JFrame frame;
	private String savePath;
	
	public LoadHandler() {
		frame = new JFrame();
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Gets the location of saved files.
		savePath = System.getProperty("user.dir") + "/save/";
	}
	
	
	/**
	 * Getter for savePath.
	 * 
	 * @return the location of saved files.
	 */
	public String getSavePath() {
		return savePath;
	}
	
	
	/**
	 * Runs the loading sequence.
	 */
	public void runLoadSequence() {
		frame.add(new LoadFilePanel());
		frame.pack();
		frame.setVisible(true);
	}
	
	
	/**
	 * Opens the text file.
	 * 
	 * @param fileName The name of the text file.
	 */
	public void loadText(String fileName) {
		ProcessBuilder processBuilder;
		
		try {
			processBuilder = new ProcessBuilder("cmd.exe", "/c", savePath + fileName + "/" + fileName + ".txt");
			processBuilder.start();
		} catch (IOException e) {}
	}
	
	
	/**
	 * Loads the image.
	 * 
	 * @param fileName The name of the image file.
	 */
	public void loadImage(String fileName) {
		MainClass mainClassObj = new MainClass();
		GraphicsSystem currentSystem = mainClassObj.getCurrentSystem();
		
		try {
	        currentSystem.setWindowTitle(fileName);
			
	        BufferedImage image = ImageIO.read(new File("save/" + fileName + "/" + fileName + ".png"));
			currentSystem.setBufferedImage(image);
			repaint();
		} catch(IOException e) {};
	}
	
	
	/**
	 * Checks if the current file is saved or not.
	 * 
	 * @param commandList The list of commands typed.
	 * @return true if the file is saved.
	 */
	public boolean hasImageSaved(String commandList) {
		try {
		int listLength = commandList.length();
		String str = commandList.substring(listLength-10, listLength-6);
		
		if (str.equals("Save"))
			return true;
		else
			return false;
		} catch(StringIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	
	/**
	 * Opens a dialog box to remind saving.
	 */
	public void remindToSave() {
		frame.add(new SaveReminderPanel());
		frame.pack();
		frame.setVisible(true);
	}
}
