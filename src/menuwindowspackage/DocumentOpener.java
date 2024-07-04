package menuwindowspackage;

import java.io.IOException;

/**
 * DocumentOpener provides open(String fileName) method to open an external document file.
 */
public class DocumentOpener{
	ProcessBuilder processBuilder;
	String resourcePath;
	
	public DocumentOpener() {
		// Gets the location of the documents.
		resourcePath = System.getProperty("user.dir") + "/resources/";
	}
	
	
	/**
	 * Opens a file from resourcePath.
	 * 
	 * @param fileName The name of the file to be opened.
	 */
	public void open(String fileName) {
		try {
			processBuilder = new ProcessBuilder("cmd.exe", "/c", resourcePath + fileName + ".docx");
			processBuilder.start();
		} catch (IOException e) {}
	}

}
