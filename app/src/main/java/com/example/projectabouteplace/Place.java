package com.example.projectabouteplace;

public class Place {
    private String ID;
    private String name;

    public Place() {
        super();
    }

    public Place(String ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
