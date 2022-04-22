package com.example.stockapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DisplayChartAndInfo extends AppCompatActivity {
    JSONObject jsonObj = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_chart_and_info);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        String ticker = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        setStrictModePolicy();

        try {
            jsonObj = createJSONObject(createURL(ticker));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Stock stock = createStockObject(ticker);
        loadTextViews(stock);
    }

    private void setStrictModePolicy() {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    private JSONObject isolateJSONData() {
        JSONObject isolatedData = new JSONObject();
        try {
            isolatedData = this.jsonObj.getJSONArray("data").getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return isolatedData;
    }

    private Stock createStockObject(String ticker) {
        Stock stock = new Stock();
        JSONObject isolatedData = isolateJSONData();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String urlForCompName = "http://api.marketstack.com/v1/tickers/" + ticker + "?access_key=4d52849737d48c895cf732a518c33188";
        URL compURL = null;
        try {
            compURL = new URL(urlForCompName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JSONObject compNameObj = createJSONObject(compURL);
        String compName = "";
        try {
            compName = compNameObj.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            stock = objectMapper.readValue(isolatedData.toString(), Stock.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        stock.setCompanyName(compName);
        stock.setTicker(ticker);

        return stock;
    }
    private void loadTextViews(Stock stock) {
        TextView textview1 = findViewById(R.id.textView8);
        TextView textView2 = findViewById(R.id.textView3);
        TextView textView3 = findViewById(R.id.textView1);
        TextView textView4 = findViewById(R.id.textView2);
        String items = "Open:\nHigh:\nLow:\nClose:\nVolume";
        String values = stock.getOpen() + "\n" + stock.getHigh() + "\n" + stock.getLow() + "\n"
                + stock.getClose() + "\n" + stock.getVolume();

        textview1.setText(items);
        textView2.setText(values);
        textView3.setText(stock.getCompanyName());
        textView4.setText(stock.getTicker());
    }
    private URL createURL(String ticker) throws MalformedURLException {
        return new URL("http://api.marketstack.com/v1/eod?access_key=4d52849737d48c895cf732a518c33188&symbols=" + ticker + "&date_from=" + "2022-04-19");
    }

    private JSONObject createJSONObject(URL url) {
        JSONObject jObj = null;
        String jString;
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            bufferedReader.close();
            connection.disconnect();
            jString = stringBuilder.toString();
            jObj = new JSONObject(jString);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return jObj;

    }


}