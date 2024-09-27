# Turtle Graphics Program
This project is a simple graphics tool implemented in Java using the Swing classes. It allows users to type in simple commands to move a turtle around the screen, drawing lines as it moves. This project was developed as part of an academic coursework.


## Table of Contents
- [Prerequisites](#prerequisites)
- [Build](#build)
- [Running](#running-the-projects)
- [Usage](#features)
- [License](#license)
- [Acknowledgements](#acknowledgements)
- [Get in Touch](#get-in-touch)


## Prerequisites
To run the application, you need to have the following installed:
- [JDK](https://www.oracle.com/in/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)
- [LBUGraphics.jar v4.5](https://github.com/LBU-OOP/OOPturtleGraphicsLibrary/blob/db72a5f59815045516ea444bd8680b1b4cc26e33/LBUGraphics.jar)


## Build
- Clone the repository:
```bash
git clone https://github.com/Bhabishworgrg/turtle-graphics.git
```
- Add LBUGraphics:
```bash
cd turtle-graphics
mkdir lib
mv path/to/LBUGraphics.jar lib/
```
- Install it locally:
```bash
mvn install:install-file \
    -Dfile=lib/LBUGraphics.jar \
    -DgroupId=uk.ac.leedsbeckett.oop \
    -DartifactId=LBUGraphics \
    -Dversion=4.5 \
    -Dpackaging=jar
```
- Compile the code:
```bash
mvn package
```


## Running
To run the application, execute the command:
```bash
java -jar target/turtle-graphics-1.0.1.jar
```


## Usage
The following commands are available to manipulate the turtle:
- **Pen Commands**:
  - `penup`: Lifts the pen from the canvas, so that movement does not get shown.
  - `pendown`: Places the pen down on the canvas so movement gets shown as a drawn line.
  - `pencolour <r>,<g>,<b>`:	Makes the RGB colour from \<r>,\<g> and \<b> parameters and sets the pencolour to the made colour.
  - `penwidth <width>`:	Sets the pen’s width to specified width.

- **Movement Commands**:
  - `turnleft <degrees>`: Turn \<degrees> to the left.
  - `turnright <degrees>`: Turn \<degrees> to the right.
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

Check `Help` menu (Only works for Windows as of now) for more detailed information. 


## License
This repository is licensed under the MIT License. See the [LICENSE](LICENSE) file for more information.


## Acknowledgements
Professor Duncan Mullier: Module Leader for the Object-Oriented Programming course.

LBUGraphics: Provided JAR file for graphics functionalities.


## Get in Touch
Feel free to reach out if you want to collaborate on a project, have a question, or just want to connect!
- LinkedIn: [in/bhabishwor-gurung](https://www.linkedin.com/in/bhabishwor-gurung/)
- Email: [bhabishworgrg@gmail.com](mailto:bhabishworgrg@gmail.com)
