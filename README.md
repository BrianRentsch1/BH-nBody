# BH-nBody
An n-body physics simulation based on the Barnes-Hut algorithm. Completed by Brian Rentsch for CSC 250 at TCNJ.

To run the program:
  1. Compile the files with `javac NBody.java Body.java BHTree.java Quad.java StdIn.java StdOut.java StdDraw.java`
  2. Use `java NBody < saturnrings.txt` to run the program with the provided test file.
  
How the test files are organized (in case you want to make your own):<br>
  1. The first number is the number of celestial bodies you will be simulating<br>
  2. The second number is the radius of the universe<br>
  3. The remaining lines of the file should contain <b>initial values</b> for each body, with each line organized like this:<br>
    `<x-position> <y-position> <x-velocity> <y-velocity> <mass> <r> <g> <b>`<br>
    The r, g, and b components define the color of the body on the RGB scale (each body is a pixel with a distinct color).
