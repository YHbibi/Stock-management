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

public class MyAdapterBonEntree extends ArrayAdapter<BonEntree> {

    Context context;
    List<BonEntree> bonEntreeList;

    public MyAdapterBonEntree(@NonNull Context context, List<BonEntree> bonEntree) {

            super(context, R.layout.bon_entree_item,bonEntree);
            this.context = context;
            this.bonEntreeList = bonEntree;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bon_entree_item,null,true);

        TextView nom = view.findViewById(R.id.nom);
        TextView dateE = view.findViewById(R.id.dateE);
        TextView qteE = view.findViewById(R.id.qteE);

        nom.setText("Nom : "+ bonEntreeList.get(position).getNom());
        dateE.setText("Date: "+ bonEntreeList.get(position).getDateE());
        qteE.setText("Quantite : "+ bonEntreeList.get(position).getQteE());


        return view;
    }
}
