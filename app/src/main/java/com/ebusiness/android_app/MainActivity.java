package com.ebusiness.android_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import org.json.*;

public class MainActivity extends AppCompatActivity {
    EditText search, pricemin, pricemax, company;
    CheckBox location_arms, location_chests, location_feet, location_hands, location_waist,
            location_wrist, location_head, location_neck, location_torso;
    CheckBox category_medical, category_lifestyle, category_industrial, category_fitness,
            category_entertainment, category_pet;
    final String[] category_name = {"lifestyle", "medical", "fitness", "industrial", "entertainment", "petsandanimals"};
    final String[] location_name = {"waist", "wrist", "hands", "head", "neck", "chests", "feet", "arms", "torso"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //edit text
        search = findViewById(R.id.et_search);
        pricemin = findViewById(R.id.et_pricemin);
        pricemax = findViewById(R.id.et_pricemax);
        company = findViewById(R.id.et_company);
        //category
        category_entertainment = findViewById(R.id.cb_category_entertainment);
        category_pet = findViewById(R.id.cb_category_petsandanimal);
        category_medical = findViewById(R.id.cb_category_medical);
        category_lifestyle = findViewById(R.id.cb_category_lifestyle);
        category_industrial = findViewById(R.id.cb_category_industrial);
        category_fitness = findViewById(R.id.cb_category_fitness);
        //location
        location_waist = findViewById(R.id.cb_location_waist);
        location_wrist = findViewById(R.id.cb_location_wrist);
        location_head = findViewById(R.id.cb_location_head);
        location_neck = findViewById(R.id.cb_location_neck);
        location_torso = findViewById(R.id.cb_location_torso);
        location_arms = findViewById(R.id.cb_location_arms);
        location_chests = findViewById(R.id.cb_location_chests);
        location_feet = findViewById(R.id.cb_location_feet);
        location_hands = findViewById(R.id.cb_location_hands);

        Button submit = (Button)findViewById(R.id.bt_search);
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                final boolean[] location = {location_waist.isChecked(), location_wrist.isChecked(),
                        location_hands.isChecked(), location_head.isChecked(), location_neck.isChecked(),
                        location_chests.isChecked(), location_feet.isChecked(), location_arms.isChecked(),
                        location_torso.isChecked()};
                final boolean[] category = {category_lifestyle.isChecked(), category_medical.isChecked(),
                        category_fitness.isChecked(), category_industrial.isChecked(),
                        category_entertainment.isChecked(), category_pet.isChecked()};

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SearchResult.class);
                Bundle bundle = new Bundle();

                bundle.putString("search", search.getText().toString());
                bundle.putInt("pricemin", Integer.parseInt(pricemin.getText().toString()));
                bundle.putInt("pricemax", Integer.parseInt(pricemin.getText().toString()));
                bundle.putString("company", company.getText().toString());
                for(int i=0; i<location.length; i++){
                    if (location[i]) bundle.putBoolean(location_name[i], location[i]);
                }
                for (int i=0; i<category.length; i++){
                    if (category[i]) bundle.putBoolean(category_name[i], category[i]);
                }
                intent.putExtras(bundle);
                //startActivity(intent);
              }
          });
    }
}
