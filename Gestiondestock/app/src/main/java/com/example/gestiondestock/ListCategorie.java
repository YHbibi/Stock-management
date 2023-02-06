package com.example.gestiondestock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

public class ListCategorie extends AppCompatActivity implements IAbstractConfig {

    ListView listView;
    MyAdapterCategorie myAdapter;
    Categorie categorie;

    String categorieP;

    String url = "http://"+IP+"/AndroidAPI/listCategorie.php/";

    public static ArrayList<Categorie> categorieArrayList = new ArrayList<>();


    BottomNavigationView bottomNavigationView;
    Menu menu = new Menu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categorie);

        bottomNavigationView  = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container,menu).commit();

                        Intent intent = new Intent(ListCategorie.this,Menu.class);
                        startActivity(intent);
                        //return true;

                }

                return false;
            }
        });
        listView = findViewById(R.id.listviewCat);
        myAdapter = new MyAdapterCategorie(this,categorieArrayList);
        listView.setAdapter(myAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                String categorie = categorieArrayList.get(i).getCategorie();

                AlertDialog.Builder builder = new AlertDialog.Builder(ListCategorie.this);
                CharSequence [] items = {"Stock par categorie"};
                builder.setTitle(categorieArrayList.get(i).getCategorie());
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {

                        switch (j){

                            case 0:

                                Intent intent = new Intent(ListCategorie.this,StockParProduit.class);
                                intent.putExtra("categorie",categorie);

                                startActivity(intent);

                                break;

                        }
                    }
                });

                builder.create().show();
            }
        });

        getCategorie();
    }

    private void getCategorie() {

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                categorieArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if(success.equals("1")){

                        for ( int i =0; i<jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);


                            String categ = object.getString("categorieP");


                            categorie = new Categorie(categ);
                            categorieArrayList.add(categorie);
                            myAdapter.notifyDataSetChanged();
                        }

                    }else {
                        Toast.makeText(ListCategorie.this, "Pas de categorie", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(ListCategorie.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        },  new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListCategorie.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(ListCategorie.this);
        requestQueue.add(request);

    }
}