package com.example.gestiondestock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ListBonEntree extends AppCompatActivity implements IAbstractConfig {

    ListView listView;
    MyAdapterBonEntree myAdapter;
    BonEntree bonEntree;
    String url = "http://"+IP+"/AndroidAPI/bonEproduit.php/";

    String nomP;
    public static ArrayList<BonEntree> bonEntreeArrayList = new ArrayList<>();


    BottomNavigationView bottomNavigationView;
    Menu menu = new Menu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bon_entree);


        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container,menu).commit();

                        Intent intent = new Intent(ListBonEntree.this,Menu.class);
                        startActivity(intent);
                        //return true;

                }

                return false;
            }
        });


        Bundle bundle = getIntent().getExtras();
        if ( bundle != null){


             nomP = bundle.getString("nom");

        }else {
            Toast.makeText(this, "pas de donnees", Toast.LENGTH_SHORT).show();
        }

        listView = findViewById(R.id.listviewBE);
        myAdapter = new MyAdapterBonEntree(this,bonEntreeArrayList);
        listView.setAdapter(myAdapter);


        getBonEntree(nomP);

    }

    private void getBonEntree(String nomP) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                bonEntreeArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if(success.equals("1")){

                        for ( int i =0; i<jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);


                            String nom = object.getString("nomP");
                            String dateE = object.getString("dateE");
                            String qteE = object.getString("qteE");

                            bonEntree = new BonEntree(nom, dateE, qteE);
                            bonEntreeArrayList.add(bonEntree);
                            myAdapter.notifyDataSetChanged();
                        }

                    }else {
                        Toast.makeText(ListBonEntree.this, "Pas de bon d'entree", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(ListBonEntree.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListBonEntree.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String, String>();

                params.put("nomP",nomP);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ListBonEntree.this);
        requestQueue.add(request);

    }
}