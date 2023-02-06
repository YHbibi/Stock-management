package com.example.gestiondestock;

public class Produit {
    String id ,nom, categorie, couleur, fournisseur, prixU , qte;

    public Produit(){

    };
 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Produit(String id, String nom, String categorie, String couleur, String fournisseur, String prixU, String qte) {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.couleur = couleur;
        this.fournisseur = fournisseur;
        this.prixU = prixU;
        this.qte = qte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getPrixU() {
        return prixU;
    }

    public void setPrixU(String prixU) {
        this.prixU = prixU;
    }

    public String getQte() {
        return qte;
    }

    public void setQte(String qte) {
        this.qte = qte;
    }
}
