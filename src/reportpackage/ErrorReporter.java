package reportpackage;

import javax.swing.JOptionPane;

/**
 * ErrorReporter provides methods to report any errors in the drawing process.
 * 
 * It checks for invalid commands; invalid type, quantity or value of parameters; and
 * whether the command is still running or not.
 */
public class ErrorReporter {
	private String[] terms;
	private String command;
	private boolean hasValidCommand, hasValue, hasExtraValues, hasIntValue, 
					hasSensibleValue, hasTriParameter;
	
	/*
	 * Checks for any invalidity in command or parameters.
	 */
	public boolean hasFoundInvalidity(String[] terms) {
		this.terms = terms;
		command = terms[0];
		
		if (isCommandValid()) {
			hasValidCommand = true;
		} else {
			hasValidCommand = false;
			return true;
		}
			
		if (isValueProvided()) {
			hasValue = true;
		} else {
			hasValue = false;
			return true;
		}
		
		if(isMultipleValueProvided()) {
			hasExtraValues = true;
			return true;
		} else {
			hasExtraValues = false;
		}
		
		String[] values = terms[1].split(",");
		hasTriParameter = true;
		hasIntValue = true;
		hasSensibleValue = true;
		
		if (command.equals("pencolour")) {
			// Checks for unique invalidities in pencolour command.
			
			if (!isNeededValueProvided(values, 3)) {
				hasTriParameter = false;
				return true;
			}
			
			if (!isValueInt(values)) {
				hasIntValue = false;
				return true;
			}
			
			if (!isValueInRange(values)) {
				hasSensibleValue = false;
				return true;
			}
		} else if (command.equals("triangle")){
			if (values.length == 1) {
				// Checks for invalidities in triangle command with 1 parameter.
				
				if (!isValueInt()) {
					hasIntValue = false;
					return true;
				}
				
				if (!isValueSensible()) {
					hasSensibleValue = false;
					return true;
				}
			} else {
				// Checks for invalidities in triangle command with 3 parameters.
				
				if (!isNeededValueProvided(values, 3)) {
					hasTriParameter = false;
					return true;
				}
				
				if (!isValueInt(values)) {
					hasIntValue = false;
					return true;
				}
				
				if (!isValueSensible(values)) {
					hasSensibleValue = false;
					return true;
				}
			}
		} else if (command.equals("position")){
			// Checks for unique invalidities in position command.
			
			if (!isNeededValueProvided(values, 2))
				return true;
			
			if (!isValueInt(values)) {
				hasIntValue = false;
				return true;
			}
			
			if (!isValueInFrame(values)) {
				hasSensibleValue = false;
				return true;
			}
		} else if (command.equals("rectangle")){
			// Checks for unique invalidities in rectangle command.
			
			if (!isNeededValueProvided(values, 2))
				return true;
			
			if (!isValueInt(values)) {
				hasIntValue = false;
				return true;
			}
			
			if (!isValueSensible(values)) {
				hasSensibleValue = false;
				return true;
			}
		} else {
			if (!isValueInt()) {
				hasIntValue = false;
				return true;
			}
			
			if (!isValueSensible()) {
				hasSensibleValue = false;
				return true;
			}
		}
		
		return false;
	}
	
	
	/**
	 * Shows dialog boxes to report the found invalidities.
	 */
	public void reportInvalidity() {
		if (!hasValidCommand)
			JOptionPane.showMessageDialog(null, "This is an invalid command.", 
										  "Invalid Command", JOptionPane.ERROR_MESSAGE);
		else if (!hasValue)
			JOptionPane.showMessageDialog(null, "\"" + command + "\"" + " requires a parameter.", 
										  "Missing Parameter", JOptionPane.ERROR_MESSAGE);
		else if (!hasIntValue)
			JOptionPane.showMessageDialog(null, "The parameter must be an integer.", 
										  "Non-Integer Parameter", JOptionPane.ERROR_MESSAGE);
		else if (hasExtraValues)
			JOptionPane.showMessageDialog(null, "\"" + command + "\" only accepts one parameter.", 
										  "Extra Parameters", JOptionPane.ERROR_MESSAGE); 
		else if (!hasSensibleValue)
			JOptionPane.showMessageDialog(null, "The parameter has non-sensible value.", 
										  "Non-sensible Value", JOptionPane.ERROR_MESSAGE);
		else if (!hasTriParameter)
			JOptionPane.showMessageDialog(null, "\"" + command + "\" only accepts 3 parameters separated by a comma.", 
										  "Improper Parameter Syntax", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "\"" + command + "\" only accepts 2 parameters separated by a comma.", 
										  "Improper Parameter Syntax", JOptionPane.ERROR_MESSAGE);
	}
			
	
	/**
	 * Shows dialog box to report that command is still running.
	 */
	public void reportCommandRunning() {
		JOptionPane.showMessageDialog(null, "The previous command is still running. Please wait till it finishes.", 
									  "Command Still Running", JOptionPane.ERROR_MESSAGE);
	}
	
	
	/**
	 * Checks whether the command is valid.
	 * 
	 * @return true if valid.
	 */
	private boolean isCommandValid() {
		String[] validCommands = {"forward", "backward", "turnleft", "turnright", "pencolour", "penwidth",
								  "square", "triangle", "circle", "rectangle", "position"};
		for (String validCommand : validCommands) {
			if (command.equals(validCommand))
				return true;
		}
		return false;
	}
	
	
	/**
	 * Checks for the lack of parameter.
	 * 
	 * @return true if parameter is provided.
	 */
	private boolean isValueProvided() {
		if (terms.length == 1)
			return false;
		return true;
	}
	
	
	/**
	 * Checks for extra parameters.
	 * 
	 * @return true if extra parameters are provided.
	 */
	private boolean isMultipleValueProvided() {
		if (terms.length > 2)
			return true;
		return false;
	}
	
	
	/**
	 * Checks if required number of parameters are provided.
	 * 
	 * @param values The array of parameters.
	 * @param valueCount The needed number of parameters.
	 * @return true if required number of parameters are provided.
	 */
	private boolean isNeededValueProvided(String[] values, int valueCount) {
		if (values.length == valueCount)
			return true;
		return false;
	}
	
	
	/**
	 * Checks if the parameters are integers.
	 * 
	 * @param values The array of parameters.
	 * @return true if the parameters are integers.
	 */
	private boolean isValueInt(String[] values){
		int value1, value2, value3;
		try {
			value1 = Integer.parseInt(values[0]);
			value2 = Integer.parseInt(values[1]);
			value3 = Integer.parseInt(values[2]);
		} catch(NumberFormatException e) {
			return false;
		} catch(ArrayIndexOutOfBoundsException e) {
			return true;
		}
		return true;
	}
	
	
	/**
	 * Checks if the parameter is integer.
	 * 
	 * @return true if the parameter is integer.
	 */
	private boolean isValueInt() {
		int value;
		try {
			value = Integer.parseInt(terms[1]);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * Checks if the parameters are in between 0 and 255.
	 * 
	 * @param values The array of parameters.
	 * @return true if parameters are in range.
	 */
	private boolean isValueInRange(String[] values) {
		int value1, value2, value3;
		value1 = Integer.parseInt(values[0]);
		value2 = Integer.parseInt(values[1]);
		value3 = Integer.parseInt(values[2]);
		if (value1 < 0 || value2 < 0 || value3 < 0 || value1 > 255 || value2 > 255 || value3 > 255)
			return false;
		return true;
	}
	
	
	/**
	 * Checks if the parameters keep the turtle in frame.
	 * 
	 * @param values The array of parameters.
	 * @return true if 0<=values[0]<=800 and 0<=values[1]<=400.
	 */
	private boolean isValueInFrame(String[] values) {
		int value1, value2;
		value1 = Integer.parseInt(values[0]);
		value2 = Integer.parseInt(values[1]);
		if (value1 < 0 || value2 < 0 || value1 > 800 || value2 > 400)
			return false;
		return true;
	}
	
	
	/**
	 * Checks if the parameters are sensible.
	 * 
	 * @param values The array of parameters.
	 * @return true if the parameters are sensible.
	 */
	private boolean isValueSensible(String[] values) {
		int value1, value2, value3;
		value1 = Integer.parseInt(values[0]);
		value2 = Integer.parseInt(values[1]);
		try {
		value3 = Integer.parseInt(values[2]);
		if (value1 <= 0 || value2 <= 0 || value3 <= 0)
			return false;
		} catch(ArrayIndexOutOfBoundsException e) {
			if (value1 <= 0 || value2 <=0)
				return false;
		}
		return true;
	}
	
	
	/**
	 * Checks if the parameter is sensible.
	 * 
	 * @return true if the parameter is sensible.
	 */
	private boolean isValueSensible() {
		int value = Integer.parseInt(terms[1]);
		if (value <= 0)
			return false;
		return true;
	}
}
