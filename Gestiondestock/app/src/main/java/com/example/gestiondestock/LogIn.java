package com.example.gestiondestock;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

public class LogIn extends AppCompatActivity implements IAbstractConfig {

    EditText username,password;
    ProgressBar progressBar;
    Button button;
    String mail, passwd;
    String url = "http://"+IP+"/AndroidAPI/logIn.php/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        progressBar = findViewById(R.id.loading);
        button = findViewById(R.id.login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                mail = username.getText().toString();
                passwd = password.getText().toString();

                Login(mail,passwd);
            }
        });
    }

    private void Login(String mail,String passwd ) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("cnx avec succes")){
                    Intent intent = new Intent(LogIn.this,Menu.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(LogIn.this, "mail ou mot de passe incorrecte!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LogIn.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("mail",mail);
                params.put("passwd",passwd);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(LogIn.this);
        requestQueue.add(request);
    }
}