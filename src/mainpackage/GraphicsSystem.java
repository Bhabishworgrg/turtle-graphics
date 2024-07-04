package mainpackage;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import reportpackage.*;
import saveloadpackage.*;
import uk.ac.leedsbeckett.oop.LBUGraphics;

/**
 * GraphicsSystem provides a simple tool that allows users to draw and save images by 
 * entering various commands.
 * 
 * It extends LBUGraphics to achieve the result in addition to Swing and AWT frameworks.
 */
public class GraphicsSystem extends LBUGraphics {
	private boolean isCommandRunning; // Checks whether the command is running or not.
	private Graphics page;
	private JFrame mainFrame;
	private String commandList;		  // Contains all the commands typed.
	private String[] terms;			  // Contains command and its parameters.
	private WarningReporter warningReporter;
	
	public GraphicsSystem() {
		isCommandRunning = false;
		page = getGraphicsContext();
		mainFrame = new JFrame();
		commandList = "";
		warningReporter = new WarningReporter();
		MenuSystem menuSystem = new MenuSystem();
		
		// Sets up the frame that contains the main tool.
	    mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    mainFrame.setJMenuBar(menuSystem.getMenuBar());
	    mainFrame.setResizable(false);
		mainFrame.add(this);
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		WindowsListener listener = new WindowsListener();
		mainFrame.addWindowListener(listener);
		
		page.setColor(PenColour);
		penDown();
	}
	
	
	private class WindowsListener implements WindowListener{
		@Override
		public void windowClosing(WindowEvent e) {
			// Triggers warningReporter to warn about the closing of the window.
			warningReporter.warnExit(mainFrame);
		}
		@Override
		public void windowOpened(WindowEvent e) {}
		@Override
		public void windowClosed(WindowEvent e) {}
		@Override
		public void windowIconified(WindowEvent e) {}
		@Override
		public void windowDeiconified(WindowEvent e) {}
		@Override
		public void windowActivated(WindowEvent e) {}
		@Override
		public void windowDeactivated(WindowEvent e) {}
	}
	
	
	@Override
	public void processCommand(String command) {
		ErrorReporter errorReporter = new ErrorReporter();
		commandList += command + "\n";	
		
		// Checks if the command is running.
		if (!isCommandRunning) {
			// Runs the command.
			isCommandRunning = true;
			
			command = command.toLowerCase();
			switch (command) {
			case "save":
				save();
				break;
			case "load":
				load();
				break;
			case "about":
				about();
				break;
			case "penup":
				penUp();
				break;
			case "pendown":
				penDown();
				break;
			case "black":
				setPenColour(Color.BLACK);
				break;
			case "green":
				setPenColour(Color.GREEN);
				break;
			case "red":
				setPenColour(Color.RED);
				break;
			case "white":
				setPenColour(Color.WHITE);
				break;
			case "reset":
				reset();
				break;
			case "clear":
				clear();
				break;
			default:
			    terms = command.split(" ");
			    // Checks if there is any invalidity.
			    if (!errorReporter.hasFoundInvalidity(terms))
			    	runParametricCommand();
			    else
			    	// Reports the invalidity.
			    	errorReporter.reportInvalidity();
			}
			
			checkTurtlePosition();
			isCommandRunning = false;
		}
		else {
			// Reports that the command is still running.
			errorReporter.reportCommandRunning();
		}
	}
	
	
	/**
	 * Getter for the mainFrame.
	 * 
	 * @return the frame that's executing the main tool.
	 */
	public JFrame getMainFrame() {
		return mainFrame;
	}
	
	
	/**
	 * Sets the name in the window's title bar.
	 * 
	 * @param name The new name in the title bar.
	 */
	public void setWindowTitle(String name) {
		mainFrame.setTitle(name);
	}
	
	
	/**
	 * Checks for and runs the command that need one or two or three parameters.
	 */
	private void runParametricCommand() {
		String command = terms[0].toLowerCase();
		String parameter = terms[1];
		String[] parameters = parameter.split(",");
		
		// Checks the number of parameters.
		if (parameters.length == 1) {
			int value = Integer.parseInt(parameter);
			
			switch (command) {
			case "forward":
				forward(value);
				break;
			case "backward":
				forward(value*(-1));
				break;
			case "turnleft":
				turnLeft(value);
				break;
			case "turnright":
				turnRight(value);
				break;
			case "penwidth":
				setStroke(value);
				break;
			case "square":
				square(value);
				break;
			case "triangle":
				triangle(value);
				break;
			case "circle":
				circle(value);
			}
		} else if (parameters.length == 2){			
			int value1, value2;
			value1 = Integer.parseInt(parameters[0]);
			value2 = Integer.parseInt(parameters[1]);
			
			switch(command) {
			case "rectangle":
				rectangle(value1, value2);
				break;
			case "position":
				position(value1, value2);
			}	
		} else {			
			int value1, value2, value3;
			value1 = Integer.parseInt(parameters[0]);
			value2 = Integer.parseInt(parameters[1]);
			value3 = Integer.parseInt(parameters[2]);
			
			switch(command) {
			case "pencolour":
				setPenColour(new Color(value1, value2, value3));
				break;
			case "triangle":
				triangle(value1, value2, value3);
			}
		}
	}
	
	
	@Override
	public void about() {
		super.about();
		page.drawString("Bhabishwor Gurung", 500, 300);
		repaint();
	}
	
	
	@Override
	public void reset() {
		super.reset();
		penDown();
		setPenColour(Color.RED);
	}
	
	
	/**
	 * Runs the saving sequence to save the current image and commands 
	 * as external files.
	 */
	public void save() {
		SaveHandler saver = new SaveHandler(getBufferedImage(), commandList);
		saver.runSaveSequence();
	}
	
	
	/**
	 * Runs the loading sequence to load the image and/or text file
	 * if the image is saved.
	 * 
	 * Reminds to save if the image isn't saved.
	 */
	private void load() {
		LoadHandler loader = new LoadHandler();
		if (loader.hasImageSaved(commandList))
			loader.runLoadSequence();
		else
			loader.remindToSave();
	}
	
	
	/**
	 * Draws a square with a side in turtle's direction.
	 * 
	 * @param value The length of the sides.
	 */
	private void square(int value) {
		int x1, x2, x3, x4, y1, y2, y3, y4;		//Coordinates of the square's 4 points
		double turtleAngle, rightAngle;
		
		turtleAngle = (Math.PI / 180) * direction;
		rightAngle = Math.PI / 2;
		
		x1 = xPos;
		y1 = yPos;
		x2 = x1 + (int)(value * Math.cos(turtleAngle));				
		y2 = y1 + (int)(value * Math.sin(turtleAngle));
		x3 = x2 + (int)(value * Math.cos(turtleAngle-rightAngle));
		y3 = y2 + (int)(value * Math.sin(turtleAngle-rightAngle));
		x4 = x3 - (int)(value * Math.cos(turtleAngle));
		y4 = y3 - (int)(value * Math.sin(turtleAngle));
		
		int[] xCoordinates = {x1, x2, x3, x4};
		int[] yCoordinates = {y1, y2, y3, y4};
		
		page.drawPolygon(xCoordinates, yCoordinates, 4);
		repaint();
	}
	
	
	/**
	 * Draws an equilateral triangle with a side in turtle's direction.
	 * 
	 * @param value The length of the sides.
	 */
	private void triangle(int value) {
		int x1, x2, x3, y1, y2, y3;			//Coordinates of the triangle's 3 points
		double turtleAngle, angle60;

		turtleAngle = (Math.PI / 180) * direction;
		angle60 = Math.PI / 3;
		
		x1 = xPos;
		y1 = yPos;
		x2 = x1 + (int)(value * Math.cos(turtleAngle));				
		y2 = y1 + (int)(value * Math.sin(turtleAngle));
		x3 = x1 + (int)(value * Math.cos(turtleAngle-angle60));
		y3 = y1 + (int)(value * Math.sin(turtleAngle-angle60));
		
		int[] xCoordinates = {x1, x2, x3};
		int[] yCoordinates = {y1, y2, y3};
		
		page.drawPolygon(xCoordinates, yCoordinates, 3);
		repaint();
	}
	
	
	/**
	 * Draws a triangle without moving the triangle.
	 * 
	 * @param value1 The side that goes in the turtle's direction.
	 * @param value2 The side untouched by the turtle.
	 * @param value3 The remaining side of the triangle.
	 */
	private void triangle(int value1, int value2, int value3) {
		int x1, x2, x3, y1, y2, y3;			// Coordinates of the triangle's 3 points
		double angle1, angle2;
		
		angle1 = (Math.PI / 180) * direction;
		angle2 = Math.acos((Math.pow(value1,2)+Math.pow(value2,2)-Math.pow(value3, 2)) / (2*value1*value2));
		
		x1 = xPos;
		y1 = yPos;	
		x2 = x1 + (int)(value1 * Math.cos(angle1));				
		y2 = y1 + (int)(value1 * Math.sin(angle1));
		x3 = x1 + (int)(value3 * Math.cos(angle1-angle2));
		y3 = y1 + (int)(value3 * Math.sin(angle1-angle2));
		
		int[] xCoordinates = {x1, x2, x3};
		int[] yCoordinates = {y1, y2, y3};

		page.drawPolygon(xCoordinates, yCoordinates, 3);
		repaint();
	}
	
	
	/**
	 * Draws a rectangle without moving the turtle.
	 * 
	 * @param value1 The sides going in the turtle's direction.
	 * @param value2 The sides going 90 degrees left to the turtle's direction
	 */
	private void rectangle(int value1, int value2) {
		int x1, x2, x3, x4, y1, y2, y3, y4;		// Coordinates of the rectangle's 4 points
		double turtleAngle, rightAngle;
		
		turtleAngle = (Math.PI / 180) * direction;
		rightAngle = Math.PI / 2;
		
		x1 = xPos;
		y1 = yPos;
		x2 = x1 + (int)(value1 * Math.cos(turtleAngle));				
		y2 = y1 + (int)(value1 * Math.sin(turtleAngle));
		x3 = x2 + (int)(value2 * Math.cos(turtleAngle-rightAngle));
		y3 = y2 + (int)(value2 * Math.sin(turtleAngle-rightAngle));
		x4 = x3 - (int)(value1 * Math.cos(turtleAngle));
		y4 = y3 - (int)(value1 * Math.sin(turtleAngle));
		
		int[] xCoordinates = {x1, x2, x3, x4};
		int[] yCoordinates = {y1, y2, y3, y4};
		
		page.drawPolygon(xCoordinates, yCoordinates, 4);
		repaint();
	}
	
	
	/**
	 * Sets the position of the turtle.
	 * 
	 * @param value1 The turtle's new x-coordinate.
	 * @param value2 The turtle's new y-coordinate.
	 */
	private void position(int value1, int value2) {
		setxPos(value1);
		setyPos(value2);
		repaint();
	}
	
	
	/**
	 * Triggers the warningReporter to warn if 
	 * the turtle is out of the window frame.
	 */
	private void checkTurtlePosition() {
		if (xPos < 0 || xPos > 800 || yPos < 0 || yPos > 400)
			warningReporter.warnOutOfFrame();
	}
}
