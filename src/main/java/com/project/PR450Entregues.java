package com.project;

import java.util.ArrayList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PR450Entregues {

    private PropertyChangeSupport llistaObservers;
    private ArrayList<PR450Producte> productes;

    public PR450Entregues() {
        this.llistaObservers = new PropertyChangeSupport(this);
        this.productes = new ArrayList<PR450Producte>();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        llistaObservers.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        llistaObservers.removePropertyChangeListener(listener);
    }

    public ArrayList<PR450Producte> getProductes() {
        return productes;
    }

    public void addProducte(PR450Producte producte) {
        productes.add(producte);
        llistaObservers.firePropertyChange("entreguesAdd", null, producte.getId());
    }

    public void removeProducte(int id) {
        for (int i = 0; i < productes.size(); i++) {
            if (productes.get(i).getId() == id) {
                PR450Producte producte = productes.remove(i);
                llistaObservers.firePropertyChange("entreguesRemove", producte.getId(), null);
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder("Productes per entregar:  [ ");
        for (PR450Producte producte : productes) {
            if (productes.indexOf(producte) == productes.size() - 1) {
                resultat.append(producte.getId()).append(": ").append(producte.getNom());
            }
            else {
                resultat.append(producte.getId()).append(": ").append(producte.getNom()).append(", ");
            }
        }
        return resultat.append(" ]").toString();
    }
}