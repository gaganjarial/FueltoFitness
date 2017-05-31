package com.deltasoftwares.fueltofitness;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ContactUs.OnFragmentInteractionListener,Workout.OnFragmentInteractionListener,
        Trainer.OnFragmentInteractionListener , AdapterView.OnItemSelectedListener,Diet.OnFragmentInteractionListener
        , View.OnClickListener,Tips.OnFragmentInteractionListener,
        Video.OnFragmentInteractionListener{
        String lol,po;
        TextView name,bmi,tips,height,weight,age;
        Spinner spinner;
        Button update;


@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (TextView)findViewById(R.id.name);
        bmi = (TextView)findViewById(R.id.bmi);
        tips = (TextView)findViewById(R.id.tips);
        height = (TextView)findViewById(R.id.height);
        weight = (TextView)findViewById(R.id.weight);
        age = (TextView)findViewById(R.id.age);

        update= (Button)findViewById(R.id.button);

        update.setOnClickListener(this);


        SharedPreferences sp =getSharedPreferences("SP", Context.MODE_PRIVATE);
        po = sp.getString("EMAIL","");
        po="'"+po+"'";




        spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener

        spinner.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        categories.add("Shredding");
        categories.add("Bulk Gaining");
        categories.add("Lean Gaining");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
        int op = sp.getInt("category",-1);
        spinner.setSelection(op);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sendRequest();
        }

private void sendRequest(){

        StringRequest stringRequest = new StringRequest("http://fueltofitness.esy.es/fit/fetch.php?email="+po,
        new Response.Listener<String>() {
@Override
public void onResponse(String response) {
        showJSON(response);
        }
        },
        new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError error) {
        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
        }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        }

private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();


        name.setText(ParseJSON.firstname[0]);


        lol = ParseJSON.bmi[0].substring(0,5);
        bmi.setText(lol);
        float bodymass = Float.parseFloat(lol);

        lol = ParseJSON.height[0];
        lol = lol + " cm";
        height.setText(lol);


        lol = ParseJSON.weight[0];
        lol = lol + " kg";
        weight.setText(lol);


        age.setText(ParseJSON.age[0]);

        if(bodymass<18.5)
        tips.setText("you are underweight, with proper training and diet you can gain weight and maintain your physique, go for Bulk gaining");
        if(18.5<bodymass&&bodymass<24.9)
        {
        tips.setText("you are in pink of your health, mould your body the way you want to, you must go for Lean Gaining");
        }
        if(25<bodymass&&bodymass<29.9)
        {
        tips.setText("you are Overweight, with proper training you can loose weight and get to best of your health, you must go for Shredding.");
        }

        if(29.9<bodymass)
        {
        tips.setText("you are obese, this is a alaraming sitation, u need to workout hard.\n" +
        "go for losse weight training.");
        }

        }

@Override
public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
        drawer.closeDrawer(GravityCompat.START);
        } else {
                AlertDialog.Builder alert=new AlertDialog.Builder(this);
                alert.setMessage("Are you sure, You want to exit?");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {
                                       finish();
                                }
                        });

                alert.setNegativeButton("No",new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {

                        }
                });

                AlertDialog alertDialog = alert.create();
                alertDialog.show();
        }
        }

@Override
public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
        }

@Override
public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.uinfo) {
                Intent intent = new Intent(getApplicationContext(),Update.class);
                startActivity(intent);
        return true;
        }

        return super.onOptionsItemSelected(item);
        }

@SuppressWarnings("StatementWithEmptyBody")
@Override
public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_workout) {
        Workout f = new Workout();
        getSupportFragmentManager().beginTransaction().replace(R.id.lay,f).commit();

        } else if (id == R.id.nav_diet) {
        Diet f = new Diet();
        getSupportFragmentManager().beginTransaction().replace(R.id.lay,f).commit();
        } else if (id == R.id.nav_profile) {

        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
        } else if (id == R.id.nav_video) {
        Video f = new Video();
        getSupportFragmentManager().beginTransaction().replace(R.id.lay,f).commit();



        }  else if (id == R.id.nav_trainer) {

        Trainer f = new Trainer();
        getSupportFragmentManager().beginTransaction().replace(R.id.lay,f).commit();
        }else if (id == R.id.nav_contact) {
        ContactUs f = new ContactUs();
        getSupportFragmentManager().beginTransaction().replace(R.id.lay,f).commit();

        } else if (id == R.id.nav_logout) {

        SharedPreferences sp =getSharedPreferences("SP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putBoolean("LOOGEDIN",false);
        editor.commit();
        Intent intent  = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();
        }
        else if (id == R.id.nav_tips) {
        Tips f = new Tips();
        getSupportFragmentManager().beginTransaction().replace(R.id.lay,f).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
        }


@Override
public void onFragmentInteraction(Uri uri) {

        }

@Override
public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "Training type : " + item, Toast.LENGTH_LONG).show();

        SharedPreferences sp =getSharedPreferences("SP", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("category",position);
        editor.commit();
        }

@Override
public void onNothingSelected(AdapterView<?> parent) {

        }

@Override
public void onClick(View v) {

        Intent intent = new Intent(getApplicationContext(),Update.class);
        startActivity(intent);
        }
        }
