package org.example.collection;

/**
 * Represents a set of coordinates with x and y values.
 */
public class Coordinates {
    private long x; // Cannot be null
    private int y;  // No restrictions

    /**
     * Constructor for initializing coordinates.
     */
    public Coordinates(long x, int y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{x=" + x + ", y=" + y + "}";

    }





}
