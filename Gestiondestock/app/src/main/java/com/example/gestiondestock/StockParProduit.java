package com.example.gestiondestock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class StockParProduit extends AppCompatActivity implements IAbstractConfig{

    String categorieP;

    EditText categorie, stock;

    String url = "http://"+IP+"/AndroidAPI/stockParCategorie.php/";

    BottomNavigationView bottomNavigationView;
    Menu menu = new Menu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_par_produit);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container,menu).commit();

                        Intent intent = new Intent(StockParProduit.this,Menu.class);
                        startActivity(intent);
                        //return true;

                }

                return false;
            }
        });


        categorie = findViewById(R.id.categorie);
        stock = findViewById(R.id.stock);

        Bundle bundle = getIntent().getExtras();
        if ( bundle != null){


            categorieP = bundle.getString("categorie");

            categorie.setText(categorieP);


        }else {
            Toast.makeText(this, "no data received", Toast.LENGTH_SHORT).show();
        }
        stockParProduit(categorieP);
    }


    private void stockParProduit(String categorieP) {


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

                             String stocke = object.getString("stockParCat");

                             stock.setText(stocke);
                        }

                    }else {
                        Toast.makeText(StockParProduit.this, "Pas de produits en stocke pour cette categorie", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                        Toast.makeText(StockParProduit.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
                //Toast.makeText(ListProduit.this, response.toString(), Toast.LENGTH_SHORT).show();

            }

        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StockParProduit.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }

        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<String, String>();

                params.put("categorieP",categorieP);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(StockParProduit.this);
        requestQueue.add(request);



    }
}