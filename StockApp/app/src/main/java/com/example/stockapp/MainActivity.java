package com.example.stockapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.stockapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            public static final String EXTRA_MESSAGE = "com.example.stockapp.MESSAGE";

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplayChartAndInfo.class);
                EditText editText = findViewById(R.id.editText);
                String ticker = editText.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, ticker);
                //get stock info and save to object
                //load new screen with info
                startActivity(intent);
            }

        });

    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
