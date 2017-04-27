package com.deltasoftwares.fueltofitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread t = new Thread(){

            public void run()
            {
                try{
                  sleep(3000);
                    SharedPreferences sp = getSharedPreferences("SP", Context.MODE_PRIVATE);
                    boolean check = sp.getBoolean("LOOGEDIN",false);
                    int identity  = sp.getInt("random",2);


                    if (check)
                    {

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {

                        Intent intent = new Intent(getApplicationContext(),Login.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally
                {

                }
            }





        };

        t.start();

    }
}
