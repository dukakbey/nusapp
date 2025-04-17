package com.example.nsyri.model;

/**
 * Model class for religious days.
 */
public class ReligiousDay {
    private String name;
    private String date;
    private String description;
    private boolean isJoyous;
    private int daysRemaining;

    public ReligiousDay(String name, String date, String description, boolean isJoyous, int daysRemaining) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.isJoyous = isJoyous;
        this.daysRemaining = daysRemaining;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public boolean isJoyous() {
        return isJoyous;
    }

    public int getDaysRemaining() {
        return daysRemaining;
    }
} 