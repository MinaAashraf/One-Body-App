package com.questionsanawya.dell.sanawyaapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditAActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8, ed9,ed10,ed11,ed12, maudo3_edittxt, groupname_edittxt;
    Button edit_btn, cancel_btn;
    boolean aBoolean = true;
    ScrollView scrollView;
    DatabaseReference databaseReference,databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_a);
scrollView = findViewById(R.id.scrol_edit);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        ed3 = findViewById(R.id.ed3);
        ed4 = findViewById(R.id.ed4);
        ed5 = findViewById(R.id.ed5);
        ed6 = findViewById(R.id.ed6);
        ed7 = findViewById(R.id.ed7);
        ed8 = findViewById(R.id.ed8);
        ed9 = findViewById(R.id.ed9);
        ed10 = findViewById(R.id.ed10);
        ed11 = findViewById(R.id.ed11);
        ed12 = findViewById(R.id.ed12);
        maudo3_edittxt = findViewById(R.id.maudo3edittxt);
        groupname_edittxt = findViewById(R.id.groupname_edittxt);
        edit_btn = findViewById(R.id.edit_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("comingdata");
       Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/asmaa.ttf");
        ed1.setTypeface(typeface);
        ed2.setTypeface(typeface);
        ed3.setTypeface(typeface);
        ed4.setTypeface(typeface);
        ed5.setTypeface(typeface);
        ed6.setTypeface(typeface);
        ed7.setTypeface(typeface);
        ed8.setTypeface(typeface);
        ed9.setTypeface(typeface);
        ed10.setTypeface(typeface);
        ed11.setTypeface(typeface);
        ed12.setTypeface(typeface);
        cancel_btn.setTypeface(typeface);
        edit_btn.setTypeface(typeface);
        groupname_edittxt.setTypeface(typeface);
        maudo3_edittxt.setTypeface(typeface);


        SharedPreferences sharedPreferences  = getSharedPreferences("mode",0);
        boolean checked = sharedPreferences.getBoolean("checked",true);
        if (!checked)
        {
            scrollView.setBackgroundColor(Color.parseColor("#FFFAFAFA"));
        }
        else
        {
            scrollView.setBackgroundResource(R.drawable.new_gradient);
        }

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(EditAActivity.this);
                builder.setTitle("Edit").setMessage("Do you want to save your edit").setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        aBoolean = true;
                        if (maudo3_edittxt.getText().toString().isEmpty()) {
                            maudo3_edittxt.setError("Required");
                            aBoolean = false;
                        }
                        if (groupname_edittxt.getText().toString().isEmpty()) {
                            groupname_edittxt.setError("Required");
                            aBoolean = false;
                        }
                        if (ed1.getText().toString().isEmpty()) {
                            databaseReference.child("btn alpha").child("btn1").setValue(0);
                        } else {
                            databaseReference.child("btn alpha").child("btn1").setValue(1);
                            databaseReference.child("activities").child("button1").setValue(ed1.getText().toString());
                        }

                        if (ed2.getText().toString().isEmpty()) {
                            databaseReference.child("btn alpha").child("btn2").setValue(0);
                        } else {
                            databaseReference.child("btn alpha").child("btn2").setValue(1);
                            databaseReference.child("activities").child("button2").setValue(ed2.getText().toString());
                        }
                        if (ed3.getText().toString().isEmpty()) {
                            databaseReference.child("btn alpha").child("btn3").setValue(0);
                        } else {
                            databaseReference.child("btn alpha").child("btn3").setValue(1);
                            databaseReference.child("activities").child("button3").setValue(ed3.getText().toString());
                        }
                        if (ed4.getText().toString().isEmpty()) {
                            databaseReference.child("btn alpha").child("btn4").setValue(0);
                        } else {
                            databaseReference.child("btn alpha").child("btn4").setValue(1);
                            databaseReference.child("activities").child("button6").setValue(ed4.getText().toString());
                        }
                        if (ed5.getText().toString().isEmpty()) {
                            databaseReference.child("btn alpha").child("btn5").setValue(0);
                        } else {
                            databaseReference.child("btn alpha").child("btn5").setValue(1);
                            databaseReference.child("activities").child("button5").setValue(ed5.getText().toString());
                        }


                        if (ed6.getText().toString().isEmpty()) {
                            databaseReference.child("btn alpha").child("btn6").setValue(0);
                        } else {
                            databaseReference.child("btn alpha").child("btn6").setValue(1);
                            databaseReference.child("activities").child("button4").setValue(ed6.getText().toString());
                        }
                        if (ed7.getText().toString().isEmpty()) {
                            databaseReference.child("btn alpha").child("btn7").setValue(0);
                        } else {
                            databaseReference.child("btn alpha").child("btn7").setValue(1);
                            databaseReference.child("activities").child("button7").setValue(ed7.getText().toString());
                        }
                        if (ed8.getText().toString().isEmpty()) {
                            databaseReference.child("btn alpha").child("btn8").setValue(0);
                        } else {
                            databaseReference.child("btn alpha").child("btn8").setValue(1);
                            databaseReference.child("activities").child("button8").setValue(ed8.getText().toString());
                        }
                        if (ed9.getText().toString().isEmpty()) {
                            databaseReference.child("btn alpha").child("btn9").setValue(0);
                        } else {
                            databaseReference.child("btn alpha").child("btn9").setValue(1);
                            databaseReference.child("activities").child("button9").setValue(ed9.getText().toString());
                        }
                        if (ed10.getText().toString().isEmpty()) {
                            databaseReference.child("btn alpha2").child("btn10").setValue(0);
                        } else {
                            databaseReference.child("btn alpha2").child("btn10").setValue(1);
                            databaseReference.child("activities").child("button10").setValue(ed10.getText().toString());
                        }
                        if (ed11.getText().toString().isEmpty()) {
                            databaseReference.child("btn alpha2").child("btn11").setValue(0);
                        } else {
                            databaseReference.child("btn alpha2").child("btn11").setValue(1);
                            databaseReference.child("activities").child("button11").setValue(ed11.getText().toString());
                        }

                        if (ed12.getText().toString().isEmpty()) {
                            databaseReference.child("btn alpha2").child("btn12").setValue(0);
                        } else {
                            databaseReference.child("btn alpha2").child("btn12").setValue(1);
                            databaseReference.child("activities").child("button12").setValue(ed12.getText().toString());
                        }

                        if (aBoolean) {
                            databaseReference.child("maudo3").setValue(maudo3_edittxt.getText().toString());
                            databaseReference.child("groupname").setValue(groupname_edittxt.getText().toString());
                            startActivity(new Intent(EditAActivity.this, ComingGroupActivity.class));
                        }

                    }
                });
                builder.show();
            }
        });
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(EditAActivity.this, ComingGroupActivity.class));
                finish();

            }
        });

    }

}
