package com.example.nsyri.model;

public class Figure {
    private String name;
    private int imageResourceId;
    private String biography;

    public Figure(String name, int imageResourceId, String biography) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getBiography() {
        return biography;
    }
} 