/******************************************
CSC 250 Fall 2017 Project 3
Brian Rentsch

Usage:
-Compile: javac NBody.java Body.java BHTree.java Quad.java StdIn.java StdOut.java StdDraw.java
-Run: java NBody < your_test_file.txt (you can use the provided saturnrings.txt)
*******************************************/

import java.awt.Color;

public class NBody{
    public static void main(String[] args) {
        final double dt = 0.1;                     // time quantum
        int N = StdIn.readInt();                   // number of particles
        double radius = StdIn.readDouble();        // radius of universe
        BHTree bht;
        Quad quad = new Quad(0.0,0.0,radius * 2);

        // turn on animation mode and rescale coordinate system
        StdDraw.show(0);
        StdDraw.setXscale(-radius, +radius);
        StdDraw.setYscale(-radius, +radius);

        // read in and initialize bodies
        Body[] bodies = new Body[N];               // array of N bodies
        for (int i = 0; i < N; i++) {
            double px   = StdIn.readDouble();
            double py   = StdIn.readDouble();
            double vx   = StdIn.readDouble();
            double vy   = StdIn.readDouble();
            double mass = StdIn.readDouble();
            int red     = StdIn.readInt();
            int green   = StdIn.readInt();
            int blue    = StdIn.readInt();
            Color color = new Color(red, green, blue);
            bodies[i]   = new Body(px, py, vx, vy, mass, color);
        }

        bht = new BHTree(quad);
        
        for(int i = 0; i < N; i++){
            if(bodies[i].in(quad)){
                bht.insert(bodies[i]);
            }
        }
        
        // simulate the universe
        for (double t = 0.0; true; t = t + dt) {
            
            // reset forces
            for (int i = 0; i < N; i++) {
                bodies[i].resetForce();
            }
            
            //update forces
            for (int i = 0; i < N; i++) {
               bht.updateForce(bodies[i]);
            }
            
            // update the body positions
            for (int i = 0; i < N; i++) {
                bodies[i].update(dt);
            }

            // draw the bodies
            StdDraw.clear(StdDraw.BLACK);
            for (int i = 0; i < N; i++) {
                bodies[i].draw();
            }
            StdDraw.show(10);

        }
    }
}
