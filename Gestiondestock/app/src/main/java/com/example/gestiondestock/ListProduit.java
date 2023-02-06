package com.example.gestiondestock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListProduit extends AppCompatActivity implements IAbstractConfig {

    ListView listView;
    MyAdapterProduit myAdapter;
    Produit produit;

    String url = "http://"+IP+"/AndroidAPI/listProduit.php/";
    String deleteurl = "http://"+IP+"/AndroidAPI/supprimerProduit.php/";
    Button button;
    public static ArrayList<Produit> produitArrayList = new ArrayList<>();


    BottomNavigationView bottomNavigationView;
    Menu menu = new Menu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produit);

        listView = findViewById(R.id.listview);
        myAdapter = new MyAdapterProduit(this,produitArrayList);
        listView.setAdapter(myAdapter);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container,menu).commit();

                        Intent intent = new Intent(ListProduit.this,Menu.class);
                        startActivity(intent);
                        //return true;

                }

                return false;
            }
        });

        button = findViewById(R.id.ajouter_btn);
        //button.setBackgroundColor(Color.RED);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListProduit.this,AjouterProduitActivity.class);
                startActivity(intent);

            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String id = produitArrayList.get(i).getId();
                String nom = produitArrayList.get(i).getNom();
                String couleur = produitArrayList.get(i).getCouleur();
                String categorie = produitArrayList.get(i).getCategorie();
                String fournisseur = produitArrayList.get(i).getFournisseur();
                String prixU = produitArrayList.get(i).getPrixU();
                String qte = produitArrayList.get(i).getQte();

                AlertDialog.Builder builder = new AlertDialog.Builder(ListProduit.this);
                CharSequence [] items = {"Modifier produit\t\t\t\t\t\t\t\t\t\t⟳", "Supprimer Produit\t\t\t\t\t\t\t\tX", "Bon d'entree\t\t\t\t\t\t\t\t\t\t\t\t\t↓", "Bon d'sortie\t\t\t\t\t\t\t\t\t\t\t\t\t\t↑"};
                builder.setTitle(produitArrayList.get(i).getNom());
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {

                        switch (j){

                            case 0:

                                Intent intent = new Intent(ListProduit.this,EditProduit.class);

                                intent.putExtra("id",id);
                                intent.putExtra("nom",nom);
                                intent.putExtra("couleur",couleur);
                                intent.putExtra("categorie",categorie);
                                intent.putExtra("fournisseur",fournisseur);
                                intent.putExtra("prixU",prixU);
                                intent.putExtra("qte",qte);

                                startActivity(intent);

                                break;

                            case  1:

                                deleteProduit(produitArrayList.get(i).getId(), produitArrayList.get(i).getNom(), produitArrayList.get(i).getQte());

                            case 2:

                                Intent intent2 = new Intent(ListProduit.this,ListBonEntree.class);

                                intent2.putExtra("nom",nom);
                                startActivity(intent2);

                                break;

                            case 3:

                                Intent intent3 = new Intent(ListProduit.this,ListBonSortie.class);

                                intent3.putExtra("nom",nom);
                                startActivity(intent3);

                                break;
                        }
                    }
                });

                builder.create().show();
            }
        });

        getProduit();
    }

    private void deleteProduit(String id,String nom ,String qte) {

        StringRequest request = new StringRequest(Request.Method.POST, deleteurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equalsIgnoreCase("suppression avec succes")){

                    Toast.makeText(ListProduit.this, "suppression avec succes", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ListProduit.this, "erreur de suppression produit", Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListProduit.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String, String>();

                params.put("id",id);
                params.put("nomP",nom);
                params.put("qteS",qte);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ListProduit.this);
        requestQueue.add(request);

        Intent intent = new Intent(ListProduit.this,ListProduit.class);
        startActivity(intent);

    }

    private void getProduit(){

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                produitArrayList.clear();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if(success.equals("1")){

                        for ( int i =0; i<jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);

                            String id = object.getString("id");
                            String nom = object.getString("nomP");
                            String couleur = object.getString("couleurP");
                            String categorie = object.getString("categorieP");
                            String fournisseur = object.getString("fournisseurP");
                            String prixU = object.getString("prixUP");
                            String qte = object.getString("qteP");

                            produit = new Produit(id, nom, categorie, couleur, fournisseur, prixU, qte);
                            produitArrayList.add(produit);
                            myAdapter.notifyDataSetChanged();
                        }
                    }else {
                        Toast.makeText(ListProduit.this, "Pas de produits en stocke", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ListProduit.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(ListProduit.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListProduit.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ListProduit.this);
        requestQueue.add(request);

    }
}