package org.example.collection;

import java.time.LocalDateTime;
import java.util.Objects;

public class Route {
    private long id;  ///Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; ////Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates;  ////Поле не может быть null
    private LocalDateTime creationDate; ////Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private LocationFrom from; ////Поле не может быть null
    private LocationTo to; //Поле может быть null
    private int distance;//Значение поля должно быть больше 1

    // Constructor
    public Route(long id, String name, Coordinates coordinates, LocalDateTime creationDate,
                 LocationFrom from, LocationTo to, int distance) {
        if (id <= 0) throw new IllegalArgumentException("ID must be greater than 0");
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        if (coordinates == null) throw new IllegalArgumentException("Coordinates cannot be null");
        if (from == null) throw new IllegalArgumentException("From location cannot be null");
        if (to == null) throw new IllegalArgumentException("To location cannot be null");
        if (distance <= 1) throw new IllegalArgumentException("Distance must be greater than 1");

        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate != null ? creationDate : LocalDateTime.now()
        ;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocationFrom getFrom() {
        return from;
    }

    public LocationTo getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }

    // ✅ Add these setter methods to allow updates
    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override

    public String toString() {
        return "Route{id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", from=" + from +
                ", to=" + to +
                ", distance=" + distance +
                '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Route route = (Route) obj;
        return id == route.id;  // Ensures uniqueness based on ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);  // Uses ID as unique identifier
    }

}
