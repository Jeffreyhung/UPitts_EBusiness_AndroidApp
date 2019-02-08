package com.ebusiness.android_app;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String link = createLink();
        String info = retrieveData(link);
        addToPage(info);

    }

    private void addError(LinearLayout tl){
        TextView textView1 = new TextView(this);
        textView1.setText("No Result Found");
        textView1.setGravity(Gravity.CENTER);
        textView1.setTypeface(Typeface.DEFAULT_BOLD);
        textView1.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        tl.addView(textView1);
    }

    private void addRow(String tag, String info, TableLayout tl){
        TableRow tr = new TableRow(this);
        TextView textView1 = new TextView(this);
        textView1.setText(tag);
        textView1.setTypeface(Typeface.DEFAULT_BOLD);
        tr.addView(textView1);
        TextView textView2 = new TextView(this);
        textView2.setText(info);
        tr.addView(textView2);
        tl.addView(tr);
    }

    private void addLink(String tag, String info, TableLayout tl){
        String link = "<a href=\"";
        link += info;
        link += "\">LINK</a>";
        TableRow tr = new TableRow(this);
        TextView textView1 = new TextView(this);
        textView1.setText(tag);
        textView1.setTypeface(Typeface.DEFAULT_BOLD);
        tr.addView(textView1);
        TextView textView2 = new TextView(this);
        textView2.setText(Html.fromHtml(link, Html.FROM_HTML_MODE_LEGACY));
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        tr.addView(textView2);
        tl.addView(tr);
    }

    private void addToPage(String info){
        LinearLayout linearlayout = findViewById(R.id.result_linearlayout);
        TableLayout tableLayout;
        CardView cardview;
        TableLayout.LayoutParams params = new TableLayout.LayoutParams(
                TableLayout.LayoutParams.WRAP_CONTENT,
                TableLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20, 15, 20, 15);
        try {
            if (info.equals("[]")){
                addError(linearlayout);
            }else{
                JSONArray data = new JSONArray(info);
                for (int i = 0; i <= data.length(); i++) {
                    JSONObject temp = data.getJSONObject(i);
                    cardview = new CardView(this);
                    tableLayout = new TableLayout(this);
                    tableLayout.setLayoutParams(params);
                    cardview.addView(tableLayout);
                    addRow("Product: ", temp.getString("name"), tableLayout);
                    addRow("Price: ", temp.getString("price"), tableLayout);
                    addRow("Location: ", temp.getString("location"), tableLayout);
                    addRow("Category: ", temp.getString("category"), tableLayout);
                    addRow("Company: ", temp.getString("company"), tableLayout);
                    addLink("Product Link: ", temp.getString("link"), tableLayout);
                    linearlayout.addView(cardview);
                }
            }
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
        boolean category_boo = false, location_boo = false;
        String[] category_name = {"lifestyle", "medical", "fitness", "industrial", "entertainment", "petsandanimals"};
        String[] location_name = {"waist", "wrist", "hands", "head", "neck", "chests", "feet", "arms", "torso"};
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
