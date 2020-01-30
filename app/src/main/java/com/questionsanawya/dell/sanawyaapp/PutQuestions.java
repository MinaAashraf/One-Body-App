package com.questionsanawya.dell.sanawyaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class PutQuestions extends AppCompatActivity {
    EditText question_edt, answer1_edt, answer2_edt, answer3_edt, answer4_edt;
    Button save, cancel, another, before;
    boolean b;
    TextView questionnum;
    int i = 1;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put_questions);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        question_edt = findViewById(R.id.putquestion_edtxt);
        answer1_edt = findViewById(R.id.putanswer1_edt);
        answer2_edt = findViewById(R.id.putanswer2_edt);
        answer3_edt = findViewById(R.id.putanswer3_edt);
        answer4_edt = findViewById(R.id.putanswer4_edt);
        questionnum = findViewById(R.id.question_num);
        save = findViewById(R.id.save_btn);
        cancel = findViewById(R.id.cancel);
        another = findViewById(R.id.another);
        before = findViewById(R.id.before);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("mosapaa").child("question"+i).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Mosapaa mosapaa = dataSnapshot.getValue(Mosapaa.class);
                if (mosapaa.getQ().isEmpty())
                    another.setEnabled(false);
                else
                    another.setEnabled(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b = true;
                String question = question_edt.getText().toString();
                String answer1 = answer1_edt.getText().toString();
                String answer2 = answer2_edt.getText().toString();
                String answer3 = answer3_edt.getText().toString();
                String answer4 = answer4_edt.getText().toString();

                if (question.isEmpty()) {
                    question_edt.setError("Required");
                    b = false;
                }

                if (answer1.isEmpty()) {
                    answer1_edt.setError("Required");
                    b = false;
                }

                if (answer2.isEmpty()) {
                    answer2_edt.setError("Required");
                    b = false;
                }

                if (answer3.isEmpty()) {
                    answer3_edt.setError("Required");
                    b = false;
                }

                if (answer4.isEmpty()) {
                    answer4_edt.setError("Required");
                    b = false;
                }

                if (b) {
                    Mosapaa mosapaa = new Mosapaa(question, answer1, answer2, answer3, answer4);
                    databaseReference.child("mosapaa").child("question" + i).setValue(mosapaa);
                    another.setEnabled(true);

before.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

    }
});
                }


            }
        });

        another.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionnum.setText(++i);
                databaseReference.child("mosapaa").child("question"+i).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Mosapaa mosapaa = dataSnapshot.getValue(Mosapaa.class);
                        question_edt.setText(mosapaa.getQ());
                        answer1_edt.setText(mosapaa.getAns1());
                        answer2_edt.setText(mosapaa.getAns2());
                        answer3_edt.setText(mosapaa.getAns3());
                        answer4_edt.setText(mosapaa.getAns4());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }

        });
before.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        questionnum.setText(--i);
        databaseReference.child("mosapaa").child("question"+i).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Mosapaa mosapaa = dataSnapshot.getValue(Mosapaa.class);
                question_edt.setText(mosapaa.getQ());
                answer1_edt.setText(mosapaa.getAns1());
                answer2_edt.setText(mosapaa.getAns2());
                answer3_edt.setText(mosapaa.getAns3());
                answer4_edt.setText(mosapaa.getAns4());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
});
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
