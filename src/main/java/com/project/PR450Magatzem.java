package com.project;

import java.util.ArrayList;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PR450Magatzem {

    private PropertyChangeSupport llistaObservers;
    private ArrayList<PR450Producte> productes;
    private int capacitat;

    public PR450Magatzem() {
        this.llistaObservers = new PropertyChangeSupport(this);
        this.productes = new ArrayList<PR450Producte>();
        this.capacitat = 10;
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
        if (productes.size() < capacitat) {
            productes.add(producte);
            llistaObservers.firePropertyChange("magatzemAdd", null, producte.getId());
        }
        else {
            System.out.println("Magatzem ple. No es poden afegir mes productes.");
        }
    }

    public void removeProducte(PR450Entregues entregues, int id) {
        for (int i = 0; i < productes.size(); i++) {
            if (productes.get(i).getId() == id) {
                entregues.addProducte(productes.get(i));
                PR450Producte producte = productes.remove(i);
                llistaObservers.firePropertyChange("magatzemRemove", producte.getId(), null);
                llistaObservers.firePropertyChange("magatzemEntrega", null, producte.getId());
                break;
            }
        }
    }

    public int getCapacitat() {
        return capacitat;
    }

    public void setCapacitat(int capacitat) {
        int oldCapacitat = this.capacitat;
        this.capacitat = capacitat;
        llistaObservers.firePropertyChange("capacitat", oldCapacitat, capacitat);
    }

    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder("Productes al magatzem:  [ ");
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