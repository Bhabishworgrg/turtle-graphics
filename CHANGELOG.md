# Changelog


## [1.0.0] - 2024-07-04
***Added***
- **Basic Application**
  - Uses `LBUGraphics` for graphics rendering.
  - `GraphicsSystem.java` extends `LBUGraphics`.
  - `MainClass.java` executes the program.

- **Command Support**
| Command                      | Description                                             |
|------------------------------|---------------------------------------------------------|
| `penup`                      | Lifts the pen to stop drawing.                          |
| `pendown`                    | Puts the pen down to draw.                              |
| `pencolour <r>,<g>,<b>`      | Sets the pen color to specified RGB values.             |
| `penwidth <width>`           | Sets the pen's width.                                   |
| `turnleft <degrees>`         | Turns left by specified degrees.                        |
| `turnright <degrees>`        | Turns right by specified degrees.                       |
| `forward <distance>`         | Moves forward by specified distance.                    |
| `backward <distance>`        | Moves backward by specified distance.                   |
| `position <x>,<y>`           | Moves directly to specified coordinates.                |
| `black`                      | Sets pen color to black.                                |
| `green`                      | Sets pen color to green.                                |
| `red`                        | Sets pen color to red.                                  |
| `white`                      | Sets pen color to white.                                |
| `reset`                      | Resets canvas and turtle.                               |
| `clear`                      | Clears canvas display.                                  |
| `Save`                       | Saves current image and commands.                       |
| `Load`                       | Loads saved image and/or commands.                      |
| `about`                      | Draws graphic with class version and author's name.     |
| `square <length>`            | Draws a square of specified length.                     |
| `triangle <side>`            | Draws an equilateral triangle of specified side length. |
| `triangle <s1>,<s2>,<s3>`    | Draws triangle with specified sides.                    |
| `circle <radius>`            | Draws a circle of specified radius.                     |
| `rectangle <length>,<width>` | Draws rectangle with specified dimensions.              |

- **Command Validation**
  - Detects missing parameters in parametric commands
  - Validates commands and parameters.
  - Correctly bounds parameters.

- **GUI Warning Dialogues**
  - For unsaved changes.
  - For application closure.
  - For invalid commands and parameters.
  - For background colour change.
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


## [1.0.1] - 2024-07-04
***Fixed***
- Addressed warnings with `@SuppressWarnings`.
- Resolved missing folder error during file operations.