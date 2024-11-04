package com.questionsanawya.dell.sanawyaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ScoreActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView name1, name2, name3, score1, score2, score3;
    View view1, view2, view3;
    Float s1, s2, s3;
    ImageView logo_image;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CoordinatorLayout coordinatorLayout = findViewById(R.id.scor_layout);
        logo_image = findViewById(R.id.logo_image);
        firebaseAuth = FirebaseAuth.getInstance();
        view1 = findViewById(R.id.view1);
        view2 = findViewById(R.id.view2);
        view3 = findViewById(R.id.view3);
        SharedPreferences sharedPreferences = getSharedPreferences("mode", 0);
        boolean checked = sharedPreferences.getBoolean("checked", true);
        if (!checked) {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#FFFAFAFA"));
            logo_image.setImageResource(R.drawable.log);
            view1.setBackgroundColor(Color.parseColor("#000000"));
            view2.setBackgroundColor(Color.parseColor("#000000"));
            view3.setBackgroundColor(Color.parseColor("#000000"));
        } else {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#0e1a26"));
            logo_image.setImageResource(R.drawable.squareicon);
            view1.setBackgroundColor(Color.parseColor("#054761"));
            view2.setBackgroundColor(Color.parseColor("#054761"));
            view3.setBackgroundColor(Color.parseColor("#054761"));

        }
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/asmaa.ttf");

        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);

        score1 = findViewById(R.id.group1_score);
        score2 = findViewById(R.id.group2_score);
        score3 = findViewById(R.id.group3_score);
        name1.setTypeface(typeface);
        name2.setTypeface(typeface);
        name3.setTypeface(typeface);
        score1.setTypeface(typeface);
        score2.setTypeface(typeface);
        score3.setTypeface(typeface);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("score").child("s1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                s1 = dataSnapshot.getValue(Float.class);
                databaseReference.child("score").child("s2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        s2 = dataSnapshot.getValue(Float.class);
                        databaseReference.child("score").child("s3").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                s3 = dataSnapshot.getValue(Float.class);
                                score2.setText(String.valueOf(s2));
                                score3.setText(String.valueOf(s3));
                                if (s1 >= s2 && s1 >= s3) {
                                    name1.setText("المصابيح");
                                    score1.setText(String.valueOf(s1));
                                    if (s2 > s3) {
                                        name2.setText("هلبيس");
                                        name3.setText("سيسيا");
                                        score2.setText(String.valueOf(s2));
                                        score3.setText(String.valueOf(s3));
                                    } else {
                                        name2.setText("سيسيا");
                                        name3.setText("هلبيس");
                                        score2.setText(String.valueOf(s3));
                                        score3.setText(String.valueOf(s2));

                                    }
                                }
                                if (s2 > s1 && s2 > s3) {
                                    name1.setText("هلبيس");
                                    score1.setText(String.valueOf(s2));

                                    if (s1 > s3) {
                                        name2.setText("المصابيح");
                                        name3.setText("سيسيا");
                                        score2.setText(String.valueOf(s1));
                                        score3.setText(String.valueOf(s3));
                                    } else {
                                        name2.setText("سيسيا");
                                        name3.setText("المصابيح");
                                        score2.setText(String.valueOf(s3));
                                        score3.setText(String.valueOf(s1));
                                    }
                                }
                                if (s3 > s1 && s3 > s2) {
                                    name1.setText("سيسيا");
                                    score1.setText(String.valueOf(s3));
                                    if (s1 > s2) {
                                        name2.setText("المصابيح");
                                        name3.setText("هلبيس");
                                        score2.setText(String.valueOf(s1));
                                        score3.setText(String.valueOf(s2));

                                    } else {
                                        name2.setText("هلبيس");
                                        name3.setText("المصابيح");
                                        score2.setText(String.valueOf(s2));
                                        score3.setText(String.valueOf(s1));

                                    }
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerview = navigationView.getHeaderView(0);
        final ImageView profile_nav = headerview.findViewById(R.id.profile_nav);
        final TextView username_nav = headerview.findViewById(R.id.user_name_nav);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                Picasso.get().load(userModel.getUrl()).placeholder(R.drawable.profileimage).into(profile_nav);
                username_nav.setText(userModel.getName());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(new Intent(ScoreActivity.this, Main2Activity.class));
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.magmo3at_nav) {
            databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String code = dataSnapshot.getValue().toString();
                    if (!code.equals("A111") && !code.equals("B222") && !code.equals("C333") && !code.equals("mgamal") && !code.equals("msaeed") && !code.equals("ne3ma") && !code.equals("mikko") && !code.equals("bisho") && !code.equals("sam")) {
                          startActivity(new Intent(ScoreActivity.this, Main2Activity.class));
                        finish();
                        Toast.makeText(ScoreActivity.this, "Enter the code!", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        startActivity(new Intent(ScoreActivity.this, GroupsActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        } else if (id == R.id.cominggroup_nav) {
            startActivity(new Intent(ScoreActivity.this, ComingGroupActivity.class));
            finish();


        } else if (id == R.id.score_nav) {
            startActivity(new Intent(ScoreActivity.this, ScoreActivity.class));
            finish();


        } else if (id == R.id.tranim_nav) {
            startActivity(new Intent(ScoreActivity.this, TranimActivity.class));
            finish();


        } else if (id == R.id.aya_nav) {
            startActivity(new Intent(ScoreActivity.this, AyaActivity.class));
            finish();


        } else if (id == R.id.setting_nav) {
            startActivity(new Intent(ScoreActivity.this, Settings.class));
            finish();


        } else if (id == R.id.logout_nav) {
            firebaseAuth.signOut();
            startActivity(new Intent(ScoreActivity.this, MainActivity.class));
            finish();

        } else if (id == R.id.mygroup_nav) {
            databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String code = dataSnapshot.getValue().toString();
                    if (!code.equals("A111") && !code.equals("B222") && !code.equals("C333") && !code.equals("mgamal") && !code.equals("msaeed") && !code.equals("ne3ma") && !code.equals("mikko") && !code.equals("bisho") && !code.equals("sam")) {

                            startActivity(new Intent(ScoreActivity.this, Main2Activity.class));
                            finish();
                            Toast.makeText(ScoreActivity.this, "Enter the code!", Toast.LENGTH_SHORT).show();

                    } else {

                        startActivity(new Intent(ScoreActivity.this, GroupsActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        } else if (id == R.id.questions_nav) {
            startActivity(new Intent(ScoreActivity.this, QuestionActivity.class));
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
