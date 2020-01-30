package com.questionsanawya.dell.sanawyaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

public class Mosapaa2Activity extends AppCompatActivity {
TextView questiontxt;
Button answer1,answer2,answer3,answer4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mosapaa2);
        Toolbar toolbar = findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        questiontxt = findViewById(R.id.question_txt);
        answer1 = findViewById(R.id.answer1_btn);
        answer2 = findViewById(R.id.answer2_btn);
        answer3 = findViewById(R.id.answer3_btn);
        answer4 = findViewById(R.id.answer4_btn);




    }
}
