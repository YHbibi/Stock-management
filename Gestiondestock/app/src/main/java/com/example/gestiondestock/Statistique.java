package com.example.gestiondestock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

public class Statistique extends AppCompatActivity implements IAbstractConfig{


    String url = "http://"+IP+"/AndroidAPI/statistique.php/";



    BottomNavigationView bottomNavigationView;
    Menu menu = new Menu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistique);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container,menu).commit();

                        Intent intent = new Intent(Statistique.this,Menu.class);
                        startActivity(intent);
                        //return true;

                }

                return false;
            }
        });

        getStatistique();
    }


    private void getStatistique() {

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if(success.equals("1")){

                        for ( int i =0; i<jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);


                            String nbCat = object.getString("nbCat");
                            String nbProd = object.getString("nbProd");
                            String nbFour = object.getString("nbFour");
                            String nbMvt = object.getString("nbMvt");

                            TextView cat = findViewById(R.id.textView7);
                            cat.setText(nbCat);

                            TextView prod = findViewById(R.id.textView6);
                            prod.setText(nbProd);

                            TextView four = findViewById(R.id.textView8);
                            four.setText(nbFour);

                            TextView mvt = findViewById(R.id.textView9);
                            mvt.setText(nbMvt);





                        }

                    }else {
                        Toast.makeText(Statistique.this, "Pas de statistiques", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(Statistique.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        },  new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Statistique.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Statistique.this);
        requestQueue.add(request);

    }
}