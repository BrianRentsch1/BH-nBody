/******************************************
CSC 250 Fall 2017 Project 3
Brian Rentsch
*******************************************/


import java.lang.Math;

public class Quad {
    
    private double xmid;    //Middle x-coordinate of quadrant
    private double ymid;    //Middle y-coordinate of quadrant
    private double length;  //Length of quad (assuming quad is a square)
    
    // create a new square with the given parameters (assume it is square)
    public Quad(double xmid, double ymid, double length) {
        this.xmid = xmid;
        this.ymid = ymid;
        this.length = length;
    }

    // return the length of one side of the square quadrant
    public double length() {
        return this.length;
    }

    // does quadrant contain (x, y)?
    public boolean contains(double x, double y) {
        double newX = x - this.xmid;                //Shift the given point as if this quadrant were centered around the origin
        double newY = y - this.ymid;
        double halfLength = length / 2.0;
        
        if(-halfLength <= newX && newX <= halfLength && -halfLength <= newY && newY <= halfLength){
            return true;
        }
        else{
            return false;
        }
    }

    // return a new object that represents the northwest quadrant
    public Quad NW() {
        double newXMid = this.xmid - (this.length / 4.0);
        double newYMid = this.ymid + (this.length / 4.0);
        double newLength = this.length / 2.0;
        return new Quad(newXMid, newYMid, newLength);
    }

    // return a new object that represents the northeast quadrant
    public Quad NE() {
        double newXMid = this.xmid + (this.length / 4.0);
        double newYMid = this.ymid + (this.length / 4.0);
        double newLength = this.length / 2.0;
        return new Quad(newXMid, newYMid, newLength);
    }

    // return a new object that represents the southwest quadrant
    public Quad SW() {
        double newXMid = this.xmid - (this.length / 4.0);
        double newYMid = this.ymid - (this.length / 4.0);
        double newLength = this.length / 2.0;
        return new Quad(newXMid, newYMid, newLength);
    }

    // return a new object that represents the southeast quadrant
    public Quad SE() {
        double newXMid = this.xmid + (this.length / 4.0);
        double newYMid = this.ymid - (this.length / 4.0);
        double newLength = this.length / 2.0;
        return new Quad(newXMid, newYMid, newLength);
    }

    // draw an unfilled rectangle that represents the quadrant
    public void draw() {
        StdDraw.rectangle(this.xmid, this.ymid, this.length / 2.0, this.length / 2.0);
    }

    // return a string representation of the quadrant for debugging
    public String toString() {
        return new String("xmid: " + this.xmid + " ymid: " + this.ymid + " length: " + this.length);
    }

}
