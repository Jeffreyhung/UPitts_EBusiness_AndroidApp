package com.ebusiness.android_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    EditText search, pricemin, pricemax, company;
    CheckBox arms, chests, feet, hands, medical, lifestyle, industrial, fitness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = findViewById(R.id.et_search);
        pricemin = findViewById(R.id.et_pricemin);
        pricemax = findViewById(R.id.et_pricemax);
        company = findViewById(R.id.et_company);
        arms = findViewById(R.id.cb_location_arms);
        chests = findViewById(R.id.cb_location_chests);
        feet = findViewById(R.id.cb_location_feet);
        hands = findViewById(R.id.cb_location_hands);
        medical = findViewById(R.id.cb_category_medical);
        lifestyle = findViewById(R.id.cb_category_lifestyle);
        industrial = findViewById(R.id.cb_category_industrial);
        fitness = findViewById(R.id.cb_category_fitness);


        Button submit = (Button)findViewById(R.id.bt_search);
        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                TextView test = findViewById(R.id.test);
                test.append(search.getText().toString()+'\n');
                test.append(pricemin.getText().toString()+'\n');
                test.append(pricemax.getText().toString()+'\n');
                test.append(company.getText().toString()+'\n');
                test.append(String.valueOf(arms.isChecked()));
                test.append(String.valueOf(chests.isChecked()));
                test.append(String.valueOf(medical.isChecked()));
                test.append(String.valueOf(lifestyle.isChecked()));
            }
        });
    }
}
