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

public class Update extends AppCompatActivity implements View.OnClickListener{

    EditText first,second,email,password,age,height,weight;
    TextView login;
    Button update;
    RadioGroup group;
    RadioButton radio,male,female;


    private static final String REGISTER_URL = "";

    public static final String KEY_FIRSTNAME = "firstname";
    public static final String KEY_LASTNAME = "lastname";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_AGE = "age";
    public static final String KEY_SEX = "sex";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_BMI = "bmi";

    String id = ParseJSON.pid[0];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        first = (EditText) findViewById(R.id.first_name);
        second = (EditText) findViewById(R.id.last_name);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        age = (EditText) findViewById(R.id.age);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        update = (Button) findViewById(R.id.update);
        group = (RadioGroup)findViewById(R.id.myradiogroup);
        male=(RadioButton)findViewById(R.id.male);
        female=(RadioButton)findViewById(R.id.female);

        String test =ParseJSON.sex[0];
               if(test.equals("Male"))
            male.setChecked(true);
        else if(test.equals("Female"))
            female.setChecked(true);



        first.setText(ParseJSON.firstname[0]);
        second.setText(ParseJSON.lastname[0]);
        email.setText(ParseJSON.email[0]);
        password.setText(ParseJSON.password[0]);
        age.setText(ParseJSON.age[0]);
        height.setText(ParseJSON.height[0]);
        weight.setText(ParseJSON.weight[0]);

        id = ParseJSON.pid[0];

        update.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        registerUser();
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



        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://fueltofitness.esy.es/fit/update.php?pid="+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Update.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Update.this,error.toString(),Toast.LENGTH_LONG).show();
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
}
