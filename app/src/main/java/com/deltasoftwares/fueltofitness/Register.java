package com.deltasoftwares.fueltofitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener{

    EditText first,second,email,password,age,height,weight;
    TextView login;
    Button register;
    RadioGroup group;
    RadioButton radio;

    private static final String REGISTER_URL = "http://fueltofitness.esy.es/fit/register.php";

    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_AGE = "age";
    public static final String KEY_SEX = "sex";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_BMI = "bmi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        login = (TextView) findViewById(R.id.login);
        first = (EditText) findViewById(R.id.first_name);
        second = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        age = (EditText) findViewById(R.id.age);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        register = (Button) findViewById(R.id.register);
        group = (RadioGroup)findViewById(R.id.myradiogroup);

        register.setOnClickListener(this);

        login.setOnClickListener(this);
    }

    private void registerUser(){
        int selectedId=group.getCheckedRadioButtonId();
        radio=(RadioButton)findViewById(selectedId);

        float h,w,bmif;
        h = Integer.parseInt(height.getText().toString().trim());
        w = Integer.parseInt(weight.getText().toString().trim());
        h= h/100;
        bmif = w/(h*h);
        final String firstname1 = first.getText().toString().trim();
        final String lastname1 = second.getText().toString().trim();
        final String password1 = password.getText().toString().trim();
        final String email1 = email.getText().toString().trim();
        final String age1 = age.getText().toString().trim();
        final String sex1 = radio.getText().toString().trim();
        final String weight1 = weight.getText().toString().trim();
        final String height1 = height.getText().toString().trim();
        final String bmi = Float.toString(bmif);



        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Register.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),Login.class);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_FIRSTNAME,firstname1);
                params.put(KEY_LASTNAME,lastname1);
                params.put(KEY_EMAIL, email1);
                params.put(KEY_PASSWORD,password1);
                params.put(KEY_SEX, sex1);
                params.put(KEY_AGE, age1);
                params.put(KEY_WEIGHT, weight1);
                params.put(KEY_HEIGHT, height1);
                params.put(KEY_BMI, bmi);


                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
         case R.id.register:   registerUser();
        break;
            case R.id.login:
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();

        }
    }
}
