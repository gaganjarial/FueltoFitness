package com.deltasoftwares.fueltofitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

public class Login extends AppCompatActivity implements View.OnClickListener{

    TextView email,password,register;
    Button signin;

    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String LOGIN_URL = "http://fueltofitness.esy.es/fit/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      email = (TextView)findViewById(R.id.email);
        password = (TextView)findViewById(R.id.password);
        register = (TextView)findViewById(R.id.register);

        signin = (Button) findViewById(R.id.sign_in);

        signin.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void userLogin() {
        final String email1 = email.getText().toString().trim();
        final String password1 = password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            SharedPreferences sp =getSharedPreferences("SP", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("EMAIL",email1);
                            editor.putBoolean("LOOGEDIN",true);
                            editor.putInt("random",4);
                            editor.commit();
                            


                            Intent intent  = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(Login.this,response,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Login.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_EMAIL,email1);
                map.put(KEY_PASSWORD,password1);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.sign_in:
                userLogin();

                break;

            case R.id.register:
                Intent intent = new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
                finish();
                break;


        }


    }
}
