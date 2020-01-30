package com.questionsanawya.dell.sanawyaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class KaleematActivity extends AppCompatActivity {
InputStream inputStream = null;byte []bytes;String txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaleemat);
        TextView textView = findViewById(R.id.kaleemat);
        Toolbar toolbar = findViewById(R.id.toolbar8);
        setSupportActionBar(toolbar);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/asmaa.ttf");
         textView.setTypeface(typeface);
        ScrollView scrollView = findViewById(R.id.kalemat_scroll);

        SharedPreferences sharedPreferences  = getSharedPreferences("mode",0);
        boolean checked = sharedPreferences.getBoolean("checked",true);
        if (!checked)
        {
            scrollView.setBackgroundColor(Color.parseColor("#FFFAFAFA"));
            textView.setTextColor(Color.parseColor("#000000"));


        }
        else
        {
            scrollView.setBackgroundColor(Color.parseColor("#101c28"));
            textView.setTextColor(Color.parseColor("#ffffff"));

        }
TextView textView1 = findViewById(R.id.textView);
        textView1.setTypeface(typeface);


        int i = getIntent().getExtras().getInt("t");
        try {
            inputStream = getAssets().open("t"+(i+1));
            bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            txt = new String(bytes);
            textView.setText(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
