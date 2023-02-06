package com.example.gestiondestock;

public class BonSortie {

    String nom, dateS, qteS;

    public BonSortie(String nom, String dateS, String qteS) {
        this.nom = nom;
        this.dateS = dateS;
        this.qteS = qteS;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateS() {
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = dateS;
    }

    public String getQteS() {
        return qteS;
    }

    public void setQteS(String qteS) {
        this.qteS = qteS;
    }
}
