package com.example.gestiondestock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

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

public class EditProduit extends AppCompatActivity implements IAbstractConfig {

    EditText id, nom, categorie, couleur, fournisseur, prixU, qte;
    ProgressBar progressBar;
    Button button;

    String idP, nomP, categorieP, couleurP, fournisseurP, prixUP, qteP;
    String url = "http://"+IP+"/AndroidAPI/modifierProduit.php/";



    BottomNavigationView bottomNavigationView;
    Menu menu = new Menu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_produit);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container,menu).commit();

                        Intent intent = new Intent(EditProduit.this,Menu.class);
                        startActivity(intent);
                        //return true;

                }

                return false;
            }
        });

        id = findViewById(R.id.id);
        nom = findViewById(R.id.nom);
        categorie = findViewById(R.id.categorie);
        couleur = findViewById(R.id.couleur);
        fournisseur = findViewById(R.id.fournisseur);
        prixU = findViewById(R.id.prixU);
        qte = findViewById(R.id.qte);
        progressBar = findViewById(R.id.pb);
        button = findViewById(R.id.modifier_btn);

        Bundle bundle = getIntent().getExtras();
        if ( bundle != null){

            idP = bundle.getString("id");
            nomP = bundle.getString("nom");
            categorieP = bundle.getString("categorie");
            couleurP = bundle.getString("couleur");
            fournisseurP = bundle.getString("fournisseur");
            prixUP = bundle.getString("prixU");
            qteP = bundle.getString("qte");


            id.setText(idP);
            nom.setText(nomP);
            categorie.setText(categorieP);
            couleur.setText(couleurP);
            fournisseur.setText(fournisseurP);
            prixU.setText(prixUP);
            qte.setText(qteP);

            
        }else {
            Toast.makeText(this, "no data received", Toast.LENGTH_SHORT).show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                String nomUp = nom.getText().toString();
                String categorieUp = categorie.getText().toString();
                String couleurUp = couleur.getText().toString();
                String fournisseurUp = fournisseur.getText().toString();
                String prixUUp = prixU.getText().toString();
                String qteUp = qte.getText().toString();


                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(EditProduit.this, response.toString(), Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditProduit.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);

                    }

                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<String, String>();

                        params.put("id",id.getText().toString());
                        params.put("nomP",nomUp);
                        params.put("couleurP",couleurUp);
                        params.put("categorieP",categorieUp);
                        params.put("fournisseurP",fournisseurUp);
                        params.put("prixUP",prixUUp);
                        params.put("qteP",qteUp);
                        params.put("qteOLD",qteP);

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(EditProduit.this);
                requestQueue.add(request);

                Intent intent = new Intent(EditProduit.this,ListProduit.class);
                startActivity(intent);

            }
        });


    }
}