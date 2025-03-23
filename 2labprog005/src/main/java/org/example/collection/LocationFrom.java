package org.example.collection;

public class LocationFrom {
    private Float x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private float z;
    private String name; //Поле может быть null

    // Constructor
    public LocationFrom(Float x, Long y, float z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }



    // Getters
    public Float getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "LocationFrom{x=" + x + ", y=" + y + ", z=" + z + ", name='" + name + "'}";
    }
}