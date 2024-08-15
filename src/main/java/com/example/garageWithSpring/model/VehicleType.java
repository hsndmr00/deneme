package com.example.garageWithSpring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VehicleType {
    @Id
    private String name;
    private int size;
    private int space;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }
}
