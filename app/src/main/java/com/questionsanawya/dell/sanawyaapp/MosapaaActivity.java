package com.questionsanawya.dell.sanawyaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MosapaaActivity extends AppCompatActivity {
    ImageView put;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosapaa);
        Toolbar toolbar = findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        put = findViewById(R.id.put_questions);
        RelativeLayout relativeLayout = findViewById(R.id.mospaa_layout);
        SharedPreferences sharedPreferences  = getSharedPreferences("mode",0);
        boolean checked = sharedPreferences.getBoolean("checked",true);
        Button start = findViewById(R.id.start_btn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MosapaaActivity.this,Mosapaa2Activity.class));
            }
        });
        if (!checked)
        {
            relativeLayout.setBackgroundColor(Color.parseColor("#ffffff"));


        }
        else
        {
            relativeLayout.setBackgroundColor(Color.parseColor("#0e1a26"));

        }


        put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(MosapaaActivity.this,PutQuestions.class));
            }
        });



    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MosapaaActivity.this, Main2Activity.class));
        finish();
    }
}
