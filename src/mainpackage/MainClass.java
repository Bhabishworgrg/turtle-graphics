package mainpackage;

/**
 * This is the Main Class for the program and it executes the Graphics System.
 */
public class MainClass {
	private static GraphicsSystem currentSystem;	
	
	public static void main (String[] args) {
		currentSystem = new GraphicsSystem();
		currentSystem.setWindowTitle("Untitled");
	}
	
	
	/**
	 * Getter for the currentSystem.
	 * 
	 * @return the running Graphics System.
	 */
	public GraphicsSystem getCurrentSystem() {
		return currentSystem;
	}
}