package org.example.collection;

public class LocationTo {
    private float x;
    private Double y; //Поле не может быть null
    private Long z; //Поле не может быть null

    // Constructor
    public LocationTo(float x, Double y, Long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Getters
    public float getX() {
        return x;
    }



    public Double getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }

    // Setters
    public void setX(float x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setZ(Long z) {
        this.z = z;
    }



    @Override

    public String toString() {
        return "LocationTo{x=" + x + ", y=" + y + ", z=" + z + "}";
    }
}








