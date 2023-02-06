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

public class MyAdapterBonSortie extends ArrayAdapter<BonSortie> {
    Context context;
    List<BonSortie> bonSortieList;


    public MyAdapterBonSortie(@NonNull Context context,  List<BonSortie> bonSortie) {

        super(context, R.layout.bon_sortie_item,bonSortie);
        this.context = context;
        this.bonSortieList = bonSortie;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bon_sortie_item,null,true);

        TextView nom = view.findViewById(R.id.nom);
        TextView dateE = view.findViewById(R.id.dateS);
        TextView qteE = view.findViewById(R.id.qteS);

        nom.setText("Nom : "+ bonSortieList.get(position).getNom());
        dateE.setText("Date: "+ bonSortieList.get(position).getDateS());
        qteE.setText("Quantite: "+ bonSortieList.get(position).getQteS());


        return view;
    }
}
