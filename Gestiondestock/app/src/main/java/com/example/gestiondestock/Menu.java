package com.example.gestiondestock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Menu extends  AppCompatActivity {

/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_menu, container, false);
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        TextView listProduits = findViewById(R.id.textView3);

        listProduits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,ListProduit.class);
                startActivity(intent);
            }
        });

        TextView listCategories = findViewById(R.id.textView4);

        listCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,ListCategorie.class);
                startActivity(intent);
            }
        });


        TextView ajouterProduit = findViewById(R.id.textView6);

        ajouterProduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,AjouterProduitActivity.class);
                startActivity(intent);
            }
        });


        TextView mouvementProduits = findViewById(R.id.textView7);

        mouvementProduits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,MouvementProduits.class);
                startActivity(intent);
            }
        });


        TextView statistique = findViewById(R.id.textView8);

        statistique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,Statistique.class);
                startActivity(intent);
            }
        });


        TextView logOut = findViewById(R.id.textView9);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent9 = new Intent(Menu.this,LogIn.class);
                startActivity(intent9);
            }
        });


    }
}