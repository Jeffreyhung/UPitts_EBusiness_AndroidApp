package com.ebusiness.android_app;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


public class SearchResult extends AppCompatActivity {
    String search="", company="";
    int pricemax, pricemin;
    final String[] category_name = {"lifestyle", "medical", "fitness", "industrial", "entertainment", "petsandanimals"};
    final String[] location_name = {"waist", "wrist", "hands", "head", "neck", "chests", "feet", "arms", "torso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        List<String> category = new ArrayList<String>();
        List<String> location = new ArrayList<String>();
        Bundle bundle = getIntent().getExtras();
        TextView test = findViewById(R.id.textView);

        for (String key : bundle.keySet()) {
            test.append(key + " " + bundle.get(key).toString() + "/n");
            if (Arrays.asList(category_name).contains(key)) {
                category.add(key);
            } else if (Arrays.asList(location_name).contains(key)) {
                location.add(key);
            } else if (key.equals("pricemin")) {
                pricemin = bundle.getInt(key);
            } else if (key.equals("pricemax")) {
                pricemax = bundle.getInt(key);
            } else if (key.equals("search")) {
                search = bundle.get(key).toString();
            } else if (key.equals("company")) {
                company = bundle.get(key).toString();
            } else {
                continue;
            }
        }


        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL("https://www.upitt-dbms.tk/AndroidApp/test.php?Price=29.99");
            try{
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }
                rd.close();
                System.out.println(result.toString());
            }catch(IOException e){}
        }catch (MalformedURLException e){}

        System.out.println(category);
        System.out.println(location);
        System.out.println(pricemax);
        System.out.println(pricemin);
        System.out.println(search);
        System.out.println(company);

    }
}
