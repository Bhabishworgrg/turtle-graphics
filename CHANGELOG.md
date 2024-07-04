# Changelog

## [1.0.0] - 2024-07-04
***Added***
- **Basic Application**
  - Initial Setup: Uses `LBUGraphics` (provided as a JAR file) for drawing and displaying graphics.
  - Main Class: `MainClass.java` executes the program.
  - Graphics System: `GraphicsSystem.java` extends `LBUGraphics` to draw using commands.

- **Command Support**
  - `penup`: Lifts the pen from the canvas, so that movement does not get shown.
  - `pendown`: Places the pen down on the canvas so movement gets shown as a drawn line.
  - `pencolour <r>,<g>,<b>`:	Makes the RGB colour from <r>,<g> and <b> parameters and sets the pencolour to the made colour.
  - `penwidth <width>`:	Sets the pen’s width to specified width.
  - `turnleft <degrees>`: Turn <degrees> to the left.
  - `turnright <degrees>`: Turn <degrees> to the right.
  - `forward <distance>`: Move forward the specified distance.
  - `backward <distance>`: Move backwards the specified distance.
  - `position <x>,<y>`:	Places the turtle in the specified coordinates.
  - `black`: Sets the pen color to black.
  - `green`: Sets the pen color to green.
  - `red`: Sets the pen color to red.
  - `white`: Sets the pen color to white.
  - `reset`: Resets the canvas and the pen to the initial state.
  - `clear`: Clears the display.
  - `Save`: Allows the user to save the image and the set of commands that the user has typed in.
  - `Load`: Allows the user load the saved image and/or commands.
  - `about`:	Draws a simple graphic on the canvas and reports the version number of the class along with my name.
  - `square <length>`:	Draws a square of specified length. The turtle, however, remains in the original position.
  - `triangle <side>`:	Draws equilateral triangles of specified side.
  - `triangle <s1>,<s2>,<s3>`:	Draws a triangle of specified sides.
  - `circle <radius>`:	Draws a circle of specified radius.
  - `rectangle <length>,<width>`:	Draws a rectangle of specified length and width.

- **Command Validation**
  - Disallows invalid commands
  - Detects missing parameters in parametric commands.
  - Detects non-integer data for parameters.
  - Correctly bounds parameters and reports errors.

- **GUI Warning Dialogues**
  - If the current image or commands are not saved.
  - Before closing the application.
  - When invalid commands or nonsensical parameters are entered.
  - Before changing the background colour.
  - If turtle goes out of frame.

- **Menu options**
  - `File`
    - `New`: Run the application in a new window
    - `Exit This File`: Close the current application window
    - `Exit All`: Closes all windows
  - `Project`
    - `Set Background`: Opens a colour palette to change the background colour.
    - `Show Interface`: Hides command box and other compnents in the application when disabled.
  - `Help`
    - `Commands`: Opens a documentation on commands.
    - `More Commands`: Opens a documentation on extra commands.
