# Turtle Graphics Program
This project is a simple graphics tool implemented in Java using the Swing classes. It allows users to type in simple commands to move a turtle around the screen, drawing lines as it moves. This project was developed as part of an academic coursework.


## Table of Contents
- [Getting Started](#getting-started)
- [Prerequisites](#prerequisites)
- [Running the Projects](#running-the-projects)
- [Features](#features)
- [License](#license)
- [Get in Touch](#get-in-touch)


## Getting Started
To get started with the project, clone the repository to your local machine using the following command:
```bash
git clone https://github.com/Bhabishworgrg/turtle-graphics.git
```

## Prerequisites
To run these projects, you need to have the following installed on your machine:
- [Java Development Kit (JDK)](https://www.oracle.com/in/java/technologies/downloads/)
- [LBUGraphics.jar v4.5](https://github.com/LBU-OOP/OOPturtleGraphicsLibrary/blob/db72a5f59815045516ea444bd8680b1b4cc26e33/LBUGraphics.jar)


## Running the Projects
- Add the `LBUGraphics.jar` to your project:
  - For VS Code,
    - Create a `lib` folder
    - Paste the jar file in the folder
    - Update your `java.project.referencedLibraries` in `settings.json`:
    ```json
    "java.project.referencedLibraries": [
      "lib/LBUGraphics.jar"
    ]
    ```
  - For other IDEs, follow the respective instructions to add external JAR files.

- Compile and run the project:
  - Navigate to the project folder
  ```powershell
  cd turtle-graphics
  ```
  - For compiling, make `bin` folder and `sources.txt` file
  ```powershell
  mkdir bin
  Get-ChildItem -Path .\src -Recurse -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
  ```
  - Compile and run the project
  ```powershell
  javac -d bin -cp "lib/*" @(Get-Content sources.txt)
  java -cp "bin;lib/*" mainpackage.MainClass
  ```


## Features
### Basic Application
- **Initial Setup**: Uses `LBUGraphics` (provided as a JAR file) for drawing and displaying graphics.
- **Main Class**: `MainClass.java` executes the program.
- **Graphics System**: `GraphicsSystem.java` extends `LBUGraphics` to draw using commands.

### Command Support
- **Pen Commands**:
  - `penup`: Lifts the pen from the canvas, so that movement does not get shown.
  - `pendown`: Places the pen down on the canvas so movement gets shown as a drawn line.
  - `pencolour <r>,<g>,<b>`:	Makes the RGB colour from <r>,<g> and <b> parameters and sets the pencolour to the made colour.
  - `penwidth <width>`:	Sets the pen’s width to specified width.

- **Movement Commands**:
  - `turnleft <degrees>`: Turn <degrees> to the left.
  - `turnright <degrees>`: Turn <degrees> to the right.
  - `forward <distance>`: Move forward the specified distance.
  - `backward <distance>`: Move backwards the specified distance.
  - `position <x>,<y>`:	Places the turtle in the specified coordinates.

- **Color Commands**:
  - `black`: Sets the pen color to black.
  - `green`: Sets the pen color to green.
  - `red`: Sets the pen color to red.
  - `white`: Sets the pen color to white.

- **Reset and Clear**:
  - `reset`: Resets the canvas and the pen to the initial state.
  - `clear`: Clears the display.

- **Saving and Loading**:
  - `Save`: Allows the user to save the image and the set of commands that the user has typed in.
  - `Load`: Allows the user load the saved image and/or commands.

- **Drawing Commands**:
  - `about`:	Draws a simple graphic on the canvas and reports the version number of the class along with my name.
  - `square <length>`:	Draws a square of specified length. The turtle, however, remains in the original position.
  - `triangle <side>`:	Draws equilateral triangles of specified side.
  - `triangle <s1>,<s2>,<s3>`:	Draws a triangle of specified sides.
  - `circle <radius>`:	Draws a circle of specified radius.
  - `rectangle <length>,<width>`:	Draws a rectangle of specified length and width.

### Command Validation
- Disallows invalid commands
- Detects missing parameters in parametric commands.
- Detects non-integer data for parameters.
- Correctly bounds parameters and reports errors.

### GUI Warning Dialogues
- If the current image or commands are not saved.
- Before closing the application.
- When invalid commands or nonsensical parameters are entered.
- Before changing the background colour.
- If turtle goes out of frame.

### Menu options
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


## License
This repository is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.


## Acknowledgments
Professor Duncan Mullier: Module Leader for the Object-Oriented Programming course.
LBUGraphics: Provided JAR file for graphics functionalities.


## Get in Touch
Feel free to reach out if you want to collaborate on a project, have a question, or just want to connect!
- LinkedIn: [in/bhabishwor-gurung](https://www.linkedin.com/in/bhabishwor-gurung/)
- Email: [bhabishworgrg@gmail.com](mailto:bhabishworgrg@gmail.com)