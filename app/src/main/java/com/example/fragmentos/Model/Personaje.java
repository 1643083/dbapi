package com.example.fragmentos.Model;

public class Personaje {
    private String id, name, ki, race, gender, affiliation;

    public Personaje(String id, String name, String ki, String race, String gender, String affiliation) {
        this.id = id;
        this.name = name;
        this.ki = ki;
        this.race = race;
        this.gender = gender;
        this.affiliation = affiliation;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getKi() { return ki; }

    public String getRace() { return race; }

    public String getGender() { return gender; }

    public String getAffiliation() { return affiliation; }
}