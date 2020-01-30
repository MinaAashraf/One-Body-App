package com.questionsanawya.dell.sanawyaapp;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {
    RelativeLayout magmo3at_layout, mygroup_layout, cominggroup_layout, score_layout, tranim_layout, aya_layout, question_layout, mosapaa;
    boolean bb;
    TextView logout;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ImageView title ;
    ImageView team , group , star , mygroup , settings , aya , tranim , request ;
    EditText code_edit_txt;
    Button enter_code, cancel_code, code_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        team = findViewById(R.id.teamimage);
        group = findViewById(R.id.groupimage);
        mygroup = findViewById(R.id.mygroupimage);
        star = findViewById(R.id.starimage);
        tranim = findViewById(R.id.tranimimage);
        aya = findViewById(R.id.ayaimage);
        settings = findViewById(R.id.settingsimage);
        request = findViewById(R.id.requestimage);

        code_btn = findViewById(R.id.code_btn);
        aya_layout = findViewById(R.id.ayat_layout);
        magmo3at_layout = findViewById(R.id.groupat_layout);
        mygroup_layout = findViewById(R.id.mygroup_layout);
        cominggroup_layout = findViewById(R.id.cominggroup_layout);
        score_layout = findViewById(R.id.score_layout);
        tranim_layout = findViewById(R.id.tranim_lay_out);
        question_layout = findViewById(R.id.question_layout);
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.customdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        code_edit_txt = dialog.findViewById(R.id.code_edit_txt);
        enter_code = dialog.findViewById(R.id.enter_code_btn);
        cancel_code = dialog.findViewById(R.id.cancel_code_btn);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        firebaseAuth = FirebaseAuth.getInstance();
        final ImageView message = findViewById(R.id.message);
        mosapaa = findViewById(R.id.mosapaa);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/asmaa.ttf");
        title = findViewById(R.id.logo);
        TextView t1, t2, t3, t4, t5, t6, t7, t8;
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t8 = findViewById(R.id.t8);
        t1.setTypeface(typeface);
        t2.setTypeface(typeface);
        t3.setTypeface(typeface);
        t4.setTypeface(typeface);
        t5.setTypeface(typeface);
        t6.setTypeface(typeface);
        t7.setTypeface(typeface);
        t8.setTypeface(typeface);
        logout = findViewById(R.id.logout);
        logout.setTypeface(typeface);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(Main2Activity.this, MainActivity.class));
                finish();
            }
        });
        ScrollView scrollView = findViewById(R.id.main2_scroll);
        SharedPreferences sharedPreferences = getSharedPreferences("mode", 0);
        boolean checked = sharedPreferences.getBoolean("checked", true);
        if (!checked) {
            scrollView.setBackgroundColor(Color.parseColor("#FFFAFAFA"));
            magmo3at_layout.setBackgroundResource(R.drawable.view);
            mygroup_layout.setBackgroundResource(R.drawable.view);
            cominggroup_layout.setBackgroundResource(R.drawable.view);
            score_layout.setBackgroundResource(R.drawable.view);
            tranim_layout.setBackgroundResource(R.drawable.view);
            aya_layout.setBackgroundResource(R.drawable.view);
            mosapaa.setBackgroundResource(R.drawable.view);
            question_layout.setBackgroundResource(R.drawable.view);

            group.setImageResource(R.drawable.group1);
            team.setImageResource(R.drawable.team1);
            mygroup.setImageResource(R.drawable.my_group_image1);
            star.setImageResource(R.drawable.score_image1);
            settings.setImageResource(R.drawable.settings_image1);
            tranim.setImageResource(R.drawable.tranim_image1);
            aya.setImageResource(R.drawable.aya_image1);
            request.setImageResource(R.drawable.box1);

            t1.setTextColor(Color.parseColor("#000000"));
            t2.setTextColor(Color.parseColor("#000000"));
            t3.setTextColor(Color.parseColor("#000000"));
            t4.setTextColor(Color.parseColor("#000000"));
            t5.setTextColor(Color.parseColor("#000000"));
            t6.setTextColor(Color.parseColor("#000000"));
            t7.setTextColor(Color.parseColor("#000000"));
            t8.setTextColor(Color.parseColor("#000000"));
            title.setImageResource(R.drawable.title2);
        } else {
            scrollView.setBackgroundColor(Color.parseColor("#101c28"));
            magmo3at_layout.setBackgroundResource(R.drawable.new_gradient);
            mygroup_layout.setBackgroundResource(R.drawable.new_gradient2);
            cominggroup_layout.setBackgroundResource(R.drawable.new_gradient);
            score_layout.setBackgroundResource(R.drawable.new_gradient2);
            tranim_layout.setBackgroundResource(R.drawable.new_gradient);
            aya_layout.setBackgroundResource(R.drawable.new_gradient);
            mosapaa.setBackgroundResource(R.drawable.new_gradient2);
            question_layout.setBackgroundResource(R.drawable.new_gradient2);

            group.setImageResource(R.drawable.group);
            team.setImageResource(R.drawable.team);
            mygroup.setImageResource(R.drawable.my_group_image);
            star.setImageResource(R.drawable.score_image);
            settings.setImageResource(R.drawable.settings_image);
            tranim.setImageResource(R.drawable.tranim_image);
            aya.setImageResource(R.drawable.aya_image);
            request.setImageResource(R.drawable.box);

            t1.setTextColor(Color.parseColor("#ffffff"));
            t2.setTextColor(Color.parseColor("#ffffff"));
            t3.setTextColor(Color.parseColor("#ffffff"));
            t4.setTextColor(Color.parseColor("#ffffff"));
            t5.setTextColor(Color.parseColor("#ffffff"));
            t6.setTextColor(Color.parseColor("#ffffff"));
            t7.setTextColor(Color.parseColor("#ffffff"));
            t8.setTextColor(Color.parseColor("#ffffff"));
            title.setImageResource(R.drawable.title);


        }

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String code = dataSnapshot.getValue().toString();
                if (!code.equals("A111") && !code.equals("B222") && !code.equals("C333") && !code.equals("mgamal") && !code.equals("msaeed") && !code.equals("ne3ma") && !code.equals("mikko") && !code.equals("bisho") && !code.equals("sam")) {
                    code_btn.setVisibility(View.VISIBLE);
                    logout.setVisibility(View.GONE);
                }
                if (code.equals("bisho") || code.equals("mgamal") || code.equals("msaeed") || code.equals("mikko") || code.equals("ne3ma") || code.equals("sam")) {
                    message.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        code_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                enter_code.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String code = code_edit_txt.getText().toString().trim();
                        bb = false;
                        if (code.equals("A111") || code.equals("B222") || code.equals("C333")) {
                            bb = true;

                        }

                        if (code.equals("bisho") || code.equals("mgamal") || code.equals("msaeed") || code.equals("mikko") || code.equals("ne3ma") || code.equals("sam")) {
                            bb = true;
                            message.setVisibility(View.VISIBLE);
                        }
                        if (code.isEmpty()) {
                            code_edit_txt.setError("Required");
                            return;
                        }
                        if (bb == true) {

                            databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).child("code").setValue(code);
                            dialog.dismiss();
                            code_btn.setVisibility(View.GONE);
                            logout.setVisibility(View.VISIBLE);
                            code_edit_txt.setText("");
                            databaseReference.child("enable").child(firebaseAuth.getCurrentUser().getUid()).setValue(1);
                        } else {
                            code_edit_txt.setError("Failed");
                        }

                    }
                });
                cancel_code.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        mosapaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, Settings.class));
                finish();
            }
        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, MessagesActivity.class));
                finish();
            }
        });


        question_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, QuestionActivity.class));
                finish();
            }
        });


        score_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, ScoreActivity.class));
                finish();
            }
        });
        aya_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, AyaActivity.class));
                finish();
            }
        });
        magmo3at_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String code = dataSnapshot.getValue().toString();
                        if (!code.equals("A111") && !code.equals("B222") && !code.equals("C333") && !code.equals("mgamal") && !code.equals("msaeed") && !code.equals("ne3ma") && !code.equals("mikko") && !code.equals("bisho") && !code.equals("sam")) {
                            progressDialog.dismiss();
                            Toast.makeText(Main2Activity.this, "Enter the code!", Toast.LENGTH_SHORT).show();
                            code_btn.setError("Code required");
                            code_btn.requestFocus();
                        } else {
                            progressDialog.dismiss();
                            startActivity(new Intent(Main2Activity.this, GroupsActivity.class));
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        cominggroup_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, ComingGroupActivity.class));
                finish();

            }
        });
        tranim_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main2Activity.this, TranimActivity.class));
                finish();

            }
        });
        mygroup_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String code = dataSnapshot.getValue().toString();
                        if (!code.equals("A111") && !code.equals("B222") && !code.equals("C333") && !code.equals("mgamal") && !code.equals("msaeed") && !code.equals("ne3ma") && !code.equals("mikko") && !code.equals("bisho") && !code.equals("sam")) {
                            progressDialog.dismiss();
                            Toast.makeText(Main2Activity.this, "Enter the code!", Toast.LENGTH_SHORT).show();
                            code_btn.setError("Code required");
                            code_btn.requestFocus();
                        } else {
                            progressDialog.dismiss();
                            startActivity(new Intent(Main2Activity.this, MyGroupActivity.class));
                            finish();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });


    }

    @Override
    public void onBackPressed() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setTitle("SisoMOY").setMessage("هل تريد الخروج من التطبيق ؟").setPositiveButton("اوافق", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).setNegativeButton("اِلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        builder.show();
    }
}
