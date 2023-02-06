package com.example.gestiondestock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MouvementProduits extends AppCompatActivity implements IAbstractConfig {

    ListView listView;
    MyAdapterBonEntree myAdapter;
    MyAdapterBonSortie myAdapter2;

    BonEntree bonEntree;
    BonSortie bonSortie;
    String url = "http://"+IP+"/AndroidAPI/historiqueMouvement.php/";

    BottomNavigationView bottomNavigationView;
    Menu menu = new Menu();

    public static ArrayList<BonEntree> bonEntreeArrayList = new ArrayList<>();
    public static ArrayList<BonSortie> bonSortieArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouvement_produits);


        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container,menu).commit();

                        Intent intent = new Intent(MouvementProduits.this,Menu.class);
                        startActivity(intent);
                        //return true;

                }

                return false;
            }
        });

        listView = findViewById(R.id.listviewMV);

        myAdapter = new MyAdapterBonEntree(this,bonEntreeArrayList);
        listView.setAdapter(myAdapter);




/*
        myAdapter2 = new MyAdapterBonSortie(this,bonSortieArrayList);
        listView.setAdapter(myAdapter2);
*/



        getMouvement();
    }


    private void getMouvement(){

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                bonEntreeArrayList.clear();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("dataE");

                    if(success.equals("1")){

                        for ( int i =0; i<jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);

                            String nom = object.getString("nomP");

                            String date = object.getString("dateE");
                            String qte = object.getString("qteE");

                            bonEntree = new BonEntree( nom,date, qte);
                            bonEntreeArrayList.add(bonEntree);
                            myAdapter.notifyDataSetChanged();
                        }
                    }else {
                        Toast.makeText(MouvementProduits.this, "Pas de produits en stocke", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(MouvementProduits.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(MouvementProduits.this, e.getCause().getMessage(), Toast.LENGTH_SHORT).show();
                }

               // bonSortieArrayList.clear();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("dataS");

                    if(success.equals("1")){

                        for ( int i =0; i<jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);

                            String nom = object.getString("nomP");

                            String date = object.getString("dateS");
                            String qte = object.getString("qteS");

                            bonEntree = new BonEntree( nom,date, qte);
                            bonEntreeArrayList.add(bonEntree);
                            myAdapter.notifyDataSetChanged();
                        }
                    }else {
                        Toast.makeText(MouvementProduits.this, "Pas de produits en stocke", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(MouvementProduits.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }


                //Toast.makeText(ListProduit.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MouvementProduits.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(MouvementProduits.this);
        requestQueue.add(request);

    }
}