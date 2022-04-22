package com.example.stockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.stockapp.MESSAGE";
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            public static final String EXTRA_MESSAGE = "com.example.stockapp.MESSAGE";

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplayChartAndInfo.class);
                EditText editText = findViewById(R.id.editText);
                String ticker = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, ticker);
                startActivity(intent);
                //get stock info and save to object
                //load new screen with chart and info
            }
        });


    }
}