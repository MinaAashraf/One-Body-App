package com.questionsanawya.dell.sanawyaapp;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class GroupsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    InputStream input;
    LinearLayout linear1,linear2,linear3,linear4,linear5,linear6,linear7,linear8 , linear9;
    byte[] bytes;
    String names;
    TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8, txt9, txt10, txt11, txt12;
    String id = new String();
    int enb;
    float rate;
    int alpha1;
    int alpha2;
    int alpha3;
    RatingBar rb1;
    RatingBar rb2;
    RatingBar rb3;
    CardView card1, card2, card3, card4, card5, card6;
    FirebaseAuth firebaseAuth;
    int counter1 = 0, counter2 = 0, counter3 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);
        linear5 = findViewById(R.id.linear5);
        linear6 = findViewById(R.id.linear6);
        linear7 = findViewById(R.id.linear7);
        linear8 = findViewById(R.id.linear8);
        linear9 = findViewById(R.id.linear9);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
        txt6 = findViewById(R.id.txt6);
        txt7 = findViewById(R.id.txt7);
        txt8 = findViewById(R.id.txt8);
        txt9 = findViewById(R.id.txt9);
        txt10 = findViewById(R.id.txt10);
        txt11 = findViewById(R.id.txt11);
        txt12 = findViewById(R.id.txt12);
        final Button score1 = findViewById(R.id.score1);
        final Button score2 = findViewById(R.id.score2);
        final Button score3 = findViewById(R.id.score3);
        rb1 = findViewById(R.id.rating1);
        rb2 = findViewById(R.id.rating2);
        rb3 = findViewById(R.id.rating3);
        firebaseAuth = FirebaseAuth.getInstance();
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);
        card5 = findViewById(R.id.card5);
        card6 = findViewById(R.id.card6);
        final ImageView drop1, drop2, drop3;
        drop1 = findViewById(R.id.drop1);
        drop2 = findViewById(R.id.drop2);
        drop3 = findViewById(R.id.drop3);
        final ScrollView scrollView = findViewById(R.id.scroll_view);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/asmaa.ttf");
        txt1.setTypeface(typeface);
        txt2.setTypeface(typeface);
        txt3.setTypeface(typeface);
        txt4.setTypeface(typeface);
        txt5.setTypeface(typeface);
        txt6.setTypeface(typeface);
        txt7.setTypeface(typeface);
        txt8.setTypeface(typeface);
        txt9.setTypeface(typeface);
        txt10.setTypeface(typeface);
        txt11.setTypeface(typeface);
        txt12.setTypeface(typeface);
        score1.setTypeface(typeface);
        score2.setTypeface(typeface);
        score3.setTypeface(typeface);
        TextView leader1, mina, ayman, mariam, leaders2, bishoy, naama, ereny, leaders3, name3, peter, miko, sam;
        leader1 = findViewById(R.id.leaders1);
        mina = findViewById(R.id.mina);
        ayman = findViewById(R.id.ayman);
        mariam = findViewById(R.id.mariam);
        leader1.setTypeface(typeface);
        mina.setTypeface(typeface);
        ayman.setTypeface(typeface);
        mariam.setTypeface(typeface);
        TextView name1, name2;
        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        bishoy = findViewById(R.id.bishoy);
        naama = findViewById(R.id.naama);
        ereny = findViewById(R.id.Ereny);
        name1.setTypeface(typeface);
        name2.setTypeface(typeface);
        bishoy.setTypeface(typeface);
        ereny.setTypeface(typeface);
        naama.setTypeface(typeface);
        leaders2 = findViewById(R.id.leaders2);
        leaders2.setTypeface(typeface);
        leaders3 = findViewById(R.id.leaders3);
        name3 = findViewById(R.id.name3);
        peter = findViewById(R.id.peter);
        sam = findViewById(R.id.sam);
        miko = findViewById(R.id.miko);

        leaders3.setTypeface(typeface);
        name3.setTypeface(typeface);
        peter.setTypeface(typeface);
        sam.setTypeface(typeface);
        miko.setTypeface(typeface);
        TextView gorg = findViewById(R.id.gorge);
        gorg.setTypeface(typeface);
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        Button open_rating_btn  = findViewById(R.id.open_Rating_btn);
        open_rating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("users data").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        UserModel model = dataSnapshot.getValue(UserModel.class);
                        String code = model.getCode();
                        if (code.equals("A111")||code.equals("B222")||code.equals("C333")||code.equals("mgamal")||code.equals("mikko"))
                        {
                            databaseReference.child("enable").child(dataSnapshot.getKey()).setValue(1);
                        }
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


        drop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (++counter1 % 2 != 0) {
                    card1.setVisibility(View.VISIBLE);
                    card2.setVisibility(View.VISIBLE);
                    drop1.setImageResource(R.drawable.up);
                    scrollView.post(new Runnable() {
                        public void run() {
                            scrollView.smoothScrollBy(0, 200);
                        }
                    });


                } else {
                    card1.setVisibility(View.GONE);
                    card2.setVisibility(View.GONE);
                    drop1.setImageResource(R.drawable.drop);
                    scrollView.post(new Runnable() {
                        public void run() {
                            scrollView.smoothScrollBy(0, -200);
                        }
                    });

                }
            }
        });

        drop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (++counter2 % 2 != 0) {
                    card3.setVisibility(View.VISIBLE);
                    card4.setVisibility(View.VISIBLE);
                    drop2.setImageResource(R.drawable.up);
                    scrollView.post(new Runnable() {
                        public void run() {
                            scrollView.smoothScrollBy(0, 600);
                        }
                    });


                } else {
                    card3.setVisibility(View.GONE);
                    card4.setVisibility(View.GONE);
                    drop2.setImageResource(R.drawable.drop);
                    scrollView.post(new Runnable() {
                        public void run() {
                            scrollView.smoothScrollBy(0, -200);
                        }
                    });
                }
            }
        });

        drop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (++counter3 % 2 != 0) {
                    card5.setVisibility(View.VISIBLE);
                    card6.setVisibility(View.VISIBLE);
                    drop3.setImageResource(R.drawable.up);
                    scrollView.post(new Runnable() {
                        public void run() {
                            scrollView.smoothScrollBy(0, 900);
                        }
                    });

                } else {
                    card5.setVisibility(View.GONE);
                    card6.setVisibility(View.GONE);
                    drop3.setImageResource(R.drawable.drop);

                }
            }
        });
        CoordinatorLayout coordinatorLayout = findViewById(R.id.groups_layout);
        SharedPreferences sharedPreferences = getSharedPreferences("mode", 0);
        boolean checked = sharedPreferences.getBoolean("checked", true);
        if (!checked) {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#FFFAFAFA"));
linear1.setBackgroundColor(Color.parseColor("#101c28"));
linear4.setBackgroundColor(Color.parseColor("#101c28"));
linear7.setBackgroundColor(Color.parseColor("#101c28"));
            linear2.setBackgroundResource(R.drawable.view);
            linear3.setBackgroundResource(R.drawable.view);

            linear5.setBackgroundResource(R.drawable.view);
            linear6.setBackgroundResource(R.drawable.view);

            linear8.setBackgroundResource(R.drawable.view);
            linear9.setBackgroundResource(R.drawable.view);
            mina.setTextColor(Color.parseColor("#000000"));
            ayman.setTextColor(Color.parseColor("#000000"));
            mariam.setTextColor(Color.parseColor("#000000"));
            txt1.setTextColor(Color.parseColor("#000000"));
            txt2.setTextColor(Color.parseColor("#000000"));
            txt3.setTextColor(Color.parseColor("#000000"));
            txt4.setTextColor(Color.parseColor("#000000"));
            txt5.setTextColor(Color.parseColor("#000000"));
            txt6.setTextColor(Color.parseColor("#000000"));
            txt7.setTextColor(Color.parseColor("#000000"));
            txt8.setTextColor(Color.parseColor("#000000"));
            txt9.setTextColor(Color.parseColor("#000000"));
            txt10.setTextColor(Color.parseColor("#000000"));
            txt11.setTextColor(Color.parseColor("#000000"));
            txt12.setTextColor(Color.parseColor("#000000"));
            bishoy.setTextColor(Color.parseColor("#000000"));
            gorg.setTextColor(Color.parseColor("#000000"));
            ereny.setTextColor(Color.parseColor("#000000"));
            sam.setTextColor(Color.parseColor("#000000"));
            peter.setTextColor(Color.parseColor("#000000"));
            naama.setTextColor(Color.parseColor("#000000"));
            miko.setTextColor(Color.parseColor("#000000"));

        } else {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#101c28"));
            linear1.setBackgroundResource(R.drawable.new_gradient);
            linear4.setBackgroundResource(R.drawable.new_gradient);
            linear7.setBackgroundResource(R.drawable.new_gradient);

            linear2.setBackgroundResource(R.drawable.new_gradient);
            linear3.setBackgroundResource(R.drawable.new_gradient);

            linear5.setBackgroundResource(R.drawable.new_gradient);
            linear6.setBackgroundResource(R.drawable.new_gradient);

            linear8.setBackgroundResource(R.drawable.new_gradient);
            linear9.setBackgroundResource(R.drawable.new_gradient);
            mina.setTextColor(Color.parseColor("#ffffff"));
            ayman.setTextColor(Color.parseColor("#ffffff"));
            mariam.setTextColor(Color.parseColor("#ffffff"));
            txt1.setTextColor(Color.parseColor("#ffffff"));
            txt2.setTextColor(Color.parseColor("#ffffff"));
            txt3.setTextColor(Color.parseColor("#ffffff"));
            txt4.setTextColor(Color.parseColor("#ffffff"));
            txt5.setTextColor(Color.parseColor("#ffffff"));
            txt6.setTextColor(Color.parseColor("#ffffff"));
            txt7.setTextColor(Color.parseColor("#ffffff"));
            txt8.setTextColor(Color.parseColor("#ffffff"));
            txt9.setTextColor(Color.parseColor("#ffffff"));
            txt10.setTextColor(Color.parseColor("#ffffff"));
            txt11.setTextColor(Color.parseColor("#ffffff"));
            txt12.setTextColor(Color.parseColor("#ffffff"));
            bishoy.setTextColor(Color.parseColor("#ffffff"));
            ereny.setTextColor(Color.parseColor("#ffffff"));
            gorg.setTextColor(Color.parseColor("#ffffff"));
            sam.setTextColor(Color.parseColor("#ffffff"));
            peter.setTextColor(Color.parseColor("#ffffff"));
            naama.setTextColor(Color.parseColor("#ffffff"));
            miko.setTextColor(Color.parseColor("#ffffff"));
        }



        databaseReference.child("alpha").child("alpha1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alpha1 = dataSnapshot.getValue(Integer.class);

                if (alpha1 == 1) {
                    rb1.setVisibility(View.VISIBLE);
                    score1.setVisibility(View.GONE);


                } else {
                    rb1.setVisibility(View.GONE);
                    score1.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("alpha").child("alpha2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alpha2 = dataSnapshot.getValue(Integer.class);
                if (alpha2 == 1) {
                    rb2.setVisibility(View.VISIBLE);
                    score2.setVisibility(View.GONE);

                } else {
                    rb2.setVisibility(View.GONE);
                    score2.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("alpha").child("alpha3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alpha3 = dataSnapshot.getValue(Integer.class);
                if (alpha3 == 1) {
                    rb3.setVisibility(View.VISIBLE);
                    score3.setVisibility(View.GONE);

                } else {
                    rb3.setVisibility(View.GONE);
                    score3.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("enable").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                enb = dataSnapshot.getValue(Integer.class);
                if (enb == 0) {
                    rb1.setEnabled(false);

                } else {

                    rb1.setEnabled(true);
                    rb1.setRating(0);

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("enable").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                enb = dataSnapshot.getValue(Integer.class);
                if (enb == 0) {
                    rb2.setEnabled(false);

                } else {

                    rb2.setEnabled(true);
                    rb2.setRating(0);

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("enable").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                enb = dataSnapshot.getValue(Integer.class);
                if (enb == 0) {
                    rb3.setEnabled(false);

                } else {

                    rb3.setEnabled(true);
                    rb3.setRating(0);

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        rb1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rate = v;
                if (v != 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GroupsActivity.this);
                    builder.setTitle("Your Rating.").setIcon(R.drawable.score_image).setMessage("Rating by "+v+" stars ?")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    databaseReference1.child("score").child("s1").setValue(rate + Float.valueOf(score1.getText().toString()));
                                    databaseReference.child("enable").child(firebaseAuth.getCurrentUser().getUid()).setValue(0);
                                }
                            }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            rb1.setRating(0);
                        }
                    });
                    builder.show();
                }
            }
        });

        rb2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rate = v;
                if (v != 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GroupsActivity.this);
                    builder.setTitle("Your Rating.").setIcon(R.drawable.score_image).setMessage("Rating by "+v+" stars ?")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    databaseReference1.child("score").child("s2").setValue(rate + Float.valueOf(score2.getText().toString()));
                                    databaseReference.child("enable").child(firebaseAuth.getCurrentUser().getUid()).setValue(0);
                                }
                            }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            rb2.setRating(0);
                        }
                    });
                    builder.show();
                }
            }
        });

        rb3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rate = v;
                if (v != 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GroupsActivity.this);
                    builder.setTitle("Your Rating.").setIcon(R.drawable.score_image).setMessage("Rating by "+v+" stars ?")
                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    databaseReference1.child("score").child("s3").setValue(rate + Float.valueOf(score3.getText().toString()));
                                    databaseReference.child("enable").child(firebaseAuth.getCurrentUser().getUid()).setValue(0);
                                }
                            }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            rb3.setRating(0);
                        }
                    });
                    builder.show();
                }
            }
        });


        try {
            input = getAssets().open("group1");
            bytes = new byte[input.available()];
            input.read(bytes);
            input.close();
            names = new String(bytes);
            txt1.setText(names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input = getAssets().open("group1.1");
            bytes = new byte[input.available()];
            input.read(bytes);
            input.close();
            names = new String(bytes);
            txt2.setText(names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input = getAssets().open("group1.2");
            bytes = new byte[input.available()];
            input.read(bytes);
            input.close();
            names = new String(bytes);
            txt3.setText(names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input = getAssets().open("group1.3");
            bytes = new byte[input.available()];
            input.read(bytes);
            input.close();
            names = new String(bytes);
            txt4.setText(names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input = getAssets().open("group2.1");
            bytes = new byte[input.available()];
            input.read(bytes);
            input.close();
            names = new String(bytes);
            txt5.setText(names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input = getAssets().open("group2.2");
            bytes = new byte[input.available()];
            input.read(bytes);
            input.close();
            names = new String(bytes);
            txt6.setText(names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input = getAssets().open("group2.3");
            bytes = new byte[input.available()];
            input.read(bytes);
            input.close();
            names = new String(bytes);
            txt7.setText(names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input = getAssets().open("group2.4");
            bytes = new byte[input.available()];
            input.read(bytes);
            input.close();
            names = new String(bytes);
            txt8.setText(names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input = getAssets().open("group3.1");
            bytes = new byte[input.available()];
            input.read(bytes);
            input.close();
            names = new String(bytes);
            txt9.setText(names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input = getAssets().open("group3.2");
            bytes = new byte[input.available()];
            input.read(bytes);
            input.close();
            names = new String(bytes);
            txt10.setText(names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input = getAssets().open("group3.3");
            bytes = new byte[input.available()];
            input.read(bytes);
            input.close();
            names = new String(bytes);
            txt11.setText(names);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            input = getAssets().open("group3.4");
            bytes = new byte[input.available()];
            input.read(bytes);
            input.close();
            names = new String(bytes);
            txt12.setText(names);
        } catch (IOException e) {
            e.printStackTrace();
        }


        databaseReference.child("score").child("s1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                score1.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("score").child("s2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                score2.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("score").child("s3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                score3.setText(dataSnapshot.getValue().toString());
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
            startActivity(new Intent(GroupsActivity.this, Main2Activity.class));
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.magmo3at_nav) {
            startActivity(new Intent(GroupsActivity.this, GroupsActivity.class));
            finish();
        } else if (id == R.id.cominggroup_nav) {
            startActivity(new Intent(GroupsActivity.this, ComingGroupActivity.class));
            finish();

        } else if (id == R.id.score_nav) {
            startActivity(new Intent(GroupsActivity.this, ScoreActivity.class));
            finish();

        } else if (id == R.id.tranim_nav) {
            startActivity(new Intent(GroupsActivity.this, TranimActivity.class));
            finish();

        } else if (id == R.id.aya_nav) {
            startActivity(new Intent(GroupsActivity.this, AyaActivity.class));
            finish();

        } else if (id == R.id.setting_nav) {
            startActivity(new Intent(GroupsActivity.this, Settings.class));
            finish();

        } else if (id == R.id.mygroup_nav) {
            startActivity(new Intent(GroupsActivity.this, MyGroupActivity.class));
            finish();

        } else if (id == R.id.logout_nav) {
            firebaseAuth.signOut();
            startActivity(new Intent(GroupsActivity.this, MainActivity.class));
            finish();
        } else if (id == R.id.questions_nav) {
            startActivity(new Intent(GroupsActivity.this, QuestionActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
