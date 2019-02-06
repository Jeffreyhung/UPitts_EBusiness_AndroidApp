package com.ebusiness.android_app;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import org.json.*;


public class SearchResult extends AppCompatActivity {
    boolean category_boo = false, location_boo = false;
    final String[] category_name = {"lifestyle", "medical", "fitness", "industrial", "entertainment", "petsandanimals"};
    final String[] location_name = {"waist", "wrist", "hands", "head", "neck", "chests", "feet", "arms", "torso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TextView test = findViewById(R.id.textView);

        String link = createLink();
        String info = retrieveData(link);
        addToPage(info);

    }

    private void addToPage(String info){
        try {
            JSONObject json = new JSONObject(info);
        }catch(JSONException e){}



    }

    private String retrieveData(String link){
        String result="";
        try {
            URL url = new URL(link);
            try{
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                result = rd.readLine();
                rd.close();
            }catch(IOException e){}
        }catch (MalformedURLException e){}
        return result;
    }

    private String createLink(){
        List<String> category = new ArrayList<String>();
        List<String> location = new ArrayList<String>();
        Bundle bundle = getIntent().getExtras();
        StringBuilder stringBuilder = new StringBuilder();
        String apiLink = "https://www.upitt-dbms.tk/AndroidApp/Api.php?";
        stringBuilder.append(apiLink);

        for (String key : bundle.keySet()) {
            if (Arrays.asList(category_name).contains(key)) {
                category_boo = true;
                category.add(key);
            } else if (Arrays.asList(location_name).contains(key)) {
                location_boo = true;
                location.add(key);
            } else if (key.equals("pricemin")) {
                stringBuilder.append("PriceMin=");
                stringBuilder.append(bundle.get(key));
                stringBuilder.append("&");
            } else if (key.equals("pricemax")) {
                stringBuilder.append("PriceMax=");
                stringBuilder.append(bundle.get(key));
                stringBuilder.append("&");
            } else if (key.equals("search")) {
                stringBuilder.append("Search=");
                stringBuilder.append(bundle.get(key));
                stringBuilder.append("&");
            } else if (key.equals("company")) {
                stringBuilder.append("Company=");
                stringBuilder.append(bundle.get(key));
                stringBuilder.append("&");
            } else {
                continue;
            }
        }

        if(category_boo){
            stringBuilder.append("Category=");
            for (int i=0; i<category.size();i++){
                stringBuilder.append(category.get(i));
                stringBuilder.append(",");
            }
        }

        if(location_boo){
            stringBuilder.append("Location=");
            for (int i=0; i<location.size();i++){
                stringBuilder.append(location.get(i));
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }
}
