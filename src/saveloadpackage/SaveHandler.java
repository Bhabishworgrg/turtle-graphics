package saveloadpackage;

import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * SaveHandler handles the saving operation of the image and text file.
 */
public class SaveHandler {
	private String fileName;
	private BufferedImage image;
	private String commandList;
	
	
	public SaveHandler(BufferedImage image, String commandList) {
		this.image = image;
		this.commandList = commandList;
	}
	
	
	/**
	 * Runs the whole saving sequence.
	 */
	public void runSaveSequence() {		
		showDialog();

		if (!(fileName == null || fileName.equals(""))) {
			try {
				saveFiles();
				JOptionPane.showMessageDialog(null, "Your file is saved.", "Saved", 
											  JOptionPane.INFORMATION_MESSAGE);
			} catch(IOException exception) {}
		}
	}
	
	
	/**
	 * Shows dialog to name the file.
	 */
	private void showDialog() {
		Icon saveIcon = new ImageIcon("resources/saveIcon.png");
		fileName = (String) JOptionPane.showInputDialog(null, "File name: ", "Save as", JOptionPane.PLAIN_MESSAGE, 
														saveIcon, null, null);
	}

	
	/**
	 * Saves the text and image inside a folder in folderPath.
	 * 
	 * @throws IOException
	 */
	private void saveFiles() throws IOException {
		File saveFolder = new File("save");

		if (!saveFolder.exists()) {
			saveFolder.mkdir();
		}
		
		String folderPath = "save/" + fileName;
		
		File folder = new File(folderPath);
		folder.mkdir();
		
		File imageFile = new File(folderPath + "/" + fileName + ".png");
		ImageIO.write(image, "png", imageFile);
	
		FileWriter fileWriter = new FileWriter(folderPath + "/" + fileName + ".txt");
		fileWriter.write(commandList);
		fileWriter.close();
	}
}