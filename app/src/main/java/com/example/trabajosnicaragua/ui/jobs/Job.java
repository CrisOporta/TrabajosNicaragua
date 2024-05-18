package com.example.trabajosnicaragua.ui.jobs;

public class Job {
    private String title;
    private String description;
    private int imageResId; // Cambiar a int para el ID de recurso de la imagen

    public Job(String title, String description, int imageResId) {
        this.title = title;
        this.description = description;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
