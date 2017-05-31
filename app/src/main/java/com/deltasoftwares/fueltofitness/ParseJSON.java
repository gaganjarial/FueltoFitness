package com.deltasoftwares.fueltofitness;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Belal on 9/22/2015.
 */
public class ParseJSON {
    public static String[] firstname;
    public static String[] lastname;
    public static String[] email;
    public static String[] password;
    public static String[] sex;
    public static String[] age;
    public static String[] weight;
    public static String[] height;
    public static String[] bmi;
    public static String[] pid;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_FIRST = "firstname";
    public static final String KEY_LAST = "lastname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_SEX = "sex";
    public static final String KEY_AGE = "age";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_BMI = "bmi";
    public static final String KEY_PID = "pid";


    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {

            /*Log.v("ParseJSON",json);*/
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            firstname = new String[users.length()];
            lastname = new String[users.length()];
            email = new String[users.length()];
            password = new String[users.length()];
            sex  = new String[users.length()];
            age = new String[users.length()];
            weight = new String[users.length()];
            height = new String[users.length()];
            bmi = new String[users.length()];
            pid = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                firstname[i] = jo.getString(KEY_FIRST);
                lastname[i] = jo.getString(KEY_LAST);
                email[i] = jo.getString(KEY_EMAIL);
                password[i] = jo.getString(KEY_PASSWORD);
                sex[i] = jo.getString(KEY_SEX);
                age[i] = jo.getString(KEY_AGE);
                weight[i] = jo.getString(KEY_WEIGHT);
                height[i] = jo.getString(KEY_HEIGHT);
                bmi[i] = jo.getString(KEY_BMI);
                pid[i] = jo.getString(KEY_PID);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
