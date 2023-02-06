package com.example.gestiondestock;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.HashMap;
import java.util.Map;

public class AjouterProduitActivity extends AppCompatActivity implements IAbstractConfig {


    EditText nom, categorie, couleur, fournisseur, prixU , qte;
    Button ajouter_btn;
    String url = "http://"+IP+"/AndroidAPI/ajouterProduit.php/";

    BottomNavigationView bottomNavigationView;
    Menu menu = new Menu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_produit);

        nom = findViewById(R.id.nom);
        categorie = findViewById(R.id.categorie);
        couleur = findViewById(R.id.couleur);
        fournisseur = findViewById(R.id.fournisseur);
        prixU = findViewById(R.id.prixU);
        qte = findViewById(R.id.qte);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container,menu).commit();

                        Intent intent1 = new Intent(AjouterProduitActivity.this,Menu.class);
                        startActivity(intent1);
                        //return true;

                }
                return true;
            }
        });

        ajouter_btn = findViewById(R.id.ajouter_btn);

        TextView listProduits = findViewById(R.id.listProduits);

        listProduits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AjouterProduitActivity.this,ListProduit.class);
                startActivity(intent);
            }
        });



        ajouter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nomP = nom.getText().toString();
                String categorieP = categorie.getText().toString();
                String couleurP = couleur.getText().toString();
                String fournisseurP = fournisseur.getText().toString();
                String prixUP = prixU.getText().toString();
                String qteP = qte.getText().toString();

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        nom.setText("");
                        categorie.setText("");
                        couleur.setText("");
                        fournisseur.setText("");
                        prixU.setText("");
                        qte.setText("");

                        Toast.makeText(AjouterProduitActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AjouterProduitActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<String, String>();

                        params.put("nomP",nomP);
                        params.put("couleurP",couleurP);
                        params.put("categorieP",categorieP);
                        params.put("fournisseurP",fournisseurP);
                        params.put("prixUP",prixUP);
                        params.put("qteP",qteP);

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(AjouterProduitActivity.this);
                requestQueue.add(request);

                Intent intent = new Intent(AjouterProduitActivity.this,ListProduit.class);
                startActivity(intent);
            }
        });
    }
}
