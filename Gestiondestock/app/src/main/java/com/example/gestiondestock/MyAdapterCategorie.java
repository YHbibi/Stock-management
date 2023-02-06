package com.example.gestiondestock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapterCategorie extends ArrayAdapter<Categorie> {

    Context context;
    List<Categorie> categorieList;

    public MyAdapterCategorie(@NonNull Context context,  List<Categorie> categorie) {

        super(context, R.layout.categorie_item,categorie);
        this.context = context;
        this.categorieList = categorie;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categorie_item,null,true);
        TextView categorie = view.findViewById(R.id.categorie);
        categorie.setText("Categorie : "+ categorieList.get(position).getCategorie());

        return view;
    }
}
