package com.example.gestiondestock;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapterProduit extends ArrayAdapter<Produit> {

    Context context;
    List<Produit> produitList;


    public MyAdapterProduit(@NonNull Context context, List<Produit>produit) {
        super(context, R.layout.produit_item,produit);
        this.context = context;
        this.produitList = produit;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.produit_item,null,true);

        TextView nom = view.findViewById(R.id.nom);
        TextView couleur = view.findViewById(R.id.couleur);
        TextView categorie = view.findViewById(R.id.categorie);
        TextView fournisseur = view.findViewById(R.id.fournisseur);
        TextView prixU = view.findViewById(R.id.prixU);
        TextView qte = view.findViewById(R.id.qte);


        //color styles to apply on substrings
        ForegroundColorSpan mBlue = new ForegroundColorSpan(Color.rgb(40,118,156)); //blue color


        //nom.setText("Nom : "+ produitList.get(position).getNom());
        String mText1 = "Nom : "+ produitList.get(position).getNom();
        //creating spannable string from normal string, we will use it to apply forgroundcolorspan to substring
        SpannableString mSpannableString1 = new SpannableString(mText1);
        //applying color styles to substrings
        mSpannableString1.setSpan(mBlue, 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //setting text to TextView
        nom.setText(mSpannableString1);

       //couleur.setText("Couleur : "+ produitList.get(position).getCouleur());
        String mText2 = "Couleur : "+ produitList.get(position).getCouleur();
        //creating spannable string from normal string, we will use it to apply forgroundcolorspan to substring
        SpannableString mSpannableString2 = new SpannableString(mText2);
        //applying color styles to substrings
        mSpannableString2.setSpan(mBlue, 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //setting text to TextView
        couleur.setText(mSpannableString2);

        //categorie.setText("Categorie : "+ produitList.get(position).getCategorie());
        String mText3 = "Categorie : "+ produitList.get(position).getCategorie();
        //creating spannable string from normal string, we will use it to apply forgroundcolorspan to substring
        SpannableString mSpannableString3 = new SpannableString(mText3);
        //applying color styles to substrings
        mSpannableString3.setSpan(mBlue, 0, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //setting text to TextView
        categorie.setText(mSpannableString3);

        //fournisseur.setText("Fournisseur : "+ produitList.get(position).getFournisseur());
        String mText4 = "Fournisseur : "+ produitList.get(position).getFournisseur();
        //creating spannable string from normal string, we will use it to apply forgroundcolorspan to substring
        SpannableString mSpannableString4 = new SpannableString(mText4);
        //applying color styles to substrings
        mSpannableString4.setSpan(mBlue, 0, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //setting text to TextView
        fournisseur.setText(mSpannableString4);

        //prixU.setText("Prix unitaire : "+ produitList.get(position).getPrixU());
        String mText5 = "Prix unitaire : "+ produitList.get(position).getPrixU();
        //creating spannable string from normal string, we will use it to apply forgroundcolorspan to substring
        SpannableString mSpannableString5 = new SpannableString(mText5);
        //applying color styles to substrings
        mSpannableString5.setSpan(mBlue, 0, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //setting text to TextView
        prixU.setText(mSpannableString5);


        //qte.setText("Quantité disponible : "+ produitList.get(position).getQte());
        String mText6 = "Quantité disponible : "+ produitList.get(position).getQte();
        //creating spannable string from normal string, we will use it to apply forgroundcolorspan to substring
        SpannableString mSpannableString6 = new SpannableString(mText6);
        //color styles to apply on substrings
        //applying color styles to substrings
        mSpannableString6.setSpan(mBlue, 0, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //setting text to TextView
        qte.setText(mSpannableString6);


        return view;
    }
}
