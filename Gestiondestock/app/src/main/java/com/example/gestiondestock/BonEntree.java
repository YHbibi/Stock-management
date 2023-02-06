package com.example.gestiondestock;

public class BonEntree {

    String nom, dateE, qteE;

    public BonEntree(String nom, String dateE, String qteE) {
        this.nom = nom;
        this.dateE = dateE;
        this.qteE = qteE;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateE() {
        return dateE;
    }

    public void setDateE(String dateE) {
        this.dateE = dateE;
    }

    public String getQteE() {
        return qteE;
    }

    public void setQteE(String qteE) {
        this.qteE = qteE;
    }
}
