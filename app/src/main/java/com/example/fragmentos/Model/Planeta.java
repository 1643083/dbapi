package com.example.fragmentos.Model;

public class Planeta {
    private String id, name, description;
    private boolean isDestroyed;

    public Planeta(String id, String name, String description, boolean isDestroyed) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isDestroyed = isDestroyed;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public boolean isDestroyed() { return isDestroyed; }
}