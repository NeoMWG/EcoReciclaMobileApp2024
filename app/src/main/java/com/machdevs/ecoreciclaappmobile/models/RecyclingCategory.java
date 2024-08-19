package com.machdevs.ecoreciclaappmobile.models;

import java.io.Serializable;

public class RecyclingCategory implements Serializable {
    private String name;
    private String unit;
    private String description;
    private int backgroundImageResId;

    public RecyclingCategory(String name, String unit, String description, int backgroundImageResId) {
        this.name = name;
        this.unit = unit;
        this.description = description;
        this.backgroundImageResId = backgroundImageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBackgroundImageResId() {
        return backgroundImageResId;
    }

    public void setBackgroundImageResId(int backgroundImageResId) {
        this.backgroundImageResId = backgroundImageResId;
    }

    @Override
    public String toString() {
        return name + " (" + unit + ")";
    }
}