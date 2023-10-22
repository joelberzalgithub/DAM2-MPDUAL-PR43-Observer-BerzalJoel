package com.project;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PR450Producte {

    private PropertyChangeSupport llistaObservers;
    private int id;
    private String nom;

    public PR450Producte(int id, String nom) {
        this.llistaObservers = new PropertyChangeSupport(this);
        this.id = id;
        this.nom = nom;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        llistaObservers.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        llistaObservers.removePropertyChangeListener(listener);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        int oldId = this.id;
        this.id = id;
        llistaObservers.firePropertyChange("producteId", oldId, id);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        String oldNom = this.nom;
        this.nom = nom;
        llistaObservers.firePropertyChange("producteName", oldNom, nom);
    }
}