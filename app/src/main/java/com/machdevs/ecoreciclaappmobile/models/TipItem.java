package com.machdevs.ecoreciclaappmobile.models;

public class TipItem {
    private String title;
    private String content;

    public TipItem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}