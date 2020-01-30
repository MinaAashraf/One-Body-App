package com.questionsanawya.dell.sanawyaapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ComingGroupActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView b1, b2, b3, b4, b5, b6, b7, b8, b9,b10,b11,b12;
    Typeface typeface;
    String code;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ImageView editimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coming_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();

        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b10 = findViewById(R.id.button10);
        b11 = findViewById(R.id.button11);
        b12 = findViewById(R.id.button12);

        typeface = Typeface.createFromAsset(getAssets(),"fonts/asmaa.ttf");
b1.setTypeface(typeface);
b2.setTypeface(typeface);
b3.setTypeface(typeface);
b4.setTypeface(typeface);
b5.setTypeface(typeface);
b6.setTypeface(typeface);
b7.setTypeface(typeface);
b8.setTypeface(typeface);
b9.setTypeface(typeface);
b10.setTypeface(typeface);
b11.setTypeface(typeface);
b12.setTypeface(typeface);
        final Button cominggroupname = findViewById(R.id.coming_group_name);
        final Button maodo3 = findViewById(R.id.elmaudo3);
        cominggroupname.setTypeface(typeface);
        maodo3.setTypeface(typeface);
        TextView text = findViewById(R.id.text);
        text.setTypeface(typeface);
        LinearLayout above , below ;
        below = findViewById(R.id.belowlinear);
        above = findViewById(R.id.abovelinear);
        TextView fqarat = findViewById(R.id.fqarat);
        CoordinatorLayout coordinatorLayout = findViewById(R.id.coming_layout);
        SharedPreferences sharedPreferences  = getSharedPreferences("mode",0);
        boolean checked = sharedPreferences.getBoolean("checked",true);
        if (!checked)
        {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#FFFAFAFA"));
            text.setTextColor(Color.parseColor("#ffffff"));
            cominggroupname.setTextColor(Color.parseColor("#ffffff"));
            cominggroupname.setBackgroundResource(R.drawable.view3);
            maodo3.setTextColor(Color.parseColor("#ffffff"));
            maodo3.setBackgroundResource(R.drawable.view3);
            below.setBackgroundResource(R.drawable.view);
            above.setBackgroundColor(Color.parseColor("#101c28"));
            fqarat.setTextColor(Color.parseColor("#ffffff"));
            fqarat.setBackgroundResource(R.drawable.view3);

        }
        else
        {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#101c28"));
            text.setTextColor(Color.parseColor("#121f2a"));
            cominggroupname.setTextColor(Color.parseColor("#121f2a"));
            cominggroupname.setBackgroundResource(R.drawable.b);
            maodo3.setTextColor(Color.parseColor("#121f2a"));
            maodo3.setBackgroundResource(R.drawable.b);
            below.setBackgroundResource(R.drawable.new_gradient);
            above.setBackgroundResource(R.drawable.new_gradient2);
            fqarat.setBackgroundResource(R.drawable.b);
        }


        editimage = findViewById(R.id.edit_image);
        editimage.setEnabled(false);
        editimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ComingGroupActivity.this, EditAActivity.class));
            }
        });

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();
        final ArrayList<Integer> arrayList = new ArrayList<>();
        final ArrayList<Integer> arrayList2 = new ArrayList<>();
        final ArrayList<String> keylist = new ArrayList<>();
        final ArrayList<String> keylist2 = new ArrayList<>();
         databaseReference = FirebaseDatabase.getInstance().getReference();


        databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                code = dataSnapshot.getValue().toString();
                if (code.equals("bisho") || code.equals("mgamal") || code.equals("msaeed") || code.equals("mikko") || code.equals("ne3ma") || code.equals("sam")) {
                    editimage.setAlpha(1f);
                    editimage.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("comingdata").child("btn alpha").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                arrayList.add(dataSnapshot.getValue(Integer.class));
                keylist.add(dataSnapshot.getKey());
                if (arrayList.size() == 9) {
                    if (arrayList.get(0) == 0)
                        databaseReference.child("comingdata").child("activities").child("button1").setValue("___");

                    if (arrayList.get(1) == 0)
                        databaseReference.child("comingdata").child("activities").child("button2").setValue("___");

                    if (arrayList.get(2) == 0)
                        databaseReference.child("comingdata").child("activities").child("button3").setValue("___");

                    if (arrayList.get(3) == 0)
                        databaseReference.child("comingdata").child("activities").child("button4").setValue("___");
                    if (arrayList.get(4) == 0)
                        databaseReference.child("comingdata").child("activities").child("button5").setValue("___");


                    if (arrayList.get(5) == 0)
                        databaseReference.child("comingdata").child("activities").child("button6").setValue("___");

                    if (arrayList.get(6) == 0)
                        databaseReference.child("comingdata").child("activities").child("button7").setValue("___");

                    if (arrayList.get(7) == 0)
                        databaseReference.child("comingdata").child("activities").child("button8").setValue("___");

                    if (arrayList.get(8) == 0)
                        databaseReference.child("comingdata").child("activities").child("button9").setValue("___");

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                arrayList.set(keylist.indexOf(dataSnapshot.getKey()), dataSnapshot.getValue(Integer.class));
                    if (arrayList.get(0) == 0)
                        databaseReference.child("comingdata").child("activities").child("button1").setValue("...");



                if (arrayList.get(1) == 0)
                    databaseReference.child("comingdata").child("activities").child("button2").setValue("...");

                if (arrayList.get(2) == 0)
                    databaseReference.child("comingdata").child("activities").child("button3").setValue("...");

                if (arrayList.get(3) == 0)
                    databaseReference.child("comingdata").child("activities").child("button4").setValue("...");
                if (arrayList.get(4) == 0)
                    databaseReference.child("comingdata").child("activities").child("button5").setValue("...");


                if (arrayList.get(5) == 0)
                    databaseReference.child("comingdata").child("activities").child("button6").setValue("...");

                if (arrayList.get(6) == 0)
                    databaseReference.child("comingdata").child("activities").child("button7").setValue("...");

                if (arrayList.get(7) == 0)
                    databaseReference.child("comingdata").child("activities").child("button8").setValue("...");

                if (arrayList.get(8) == 0)
                    databaseReference.child("comingdata").child("activities").child("button9").setValue("...");

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
databaseReference.child("comingdata").child("btn alpha2").addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        arrayList2.add(dataSnapshot.getValue(Integer.class));
        keylist2.add(dataSnapshot.getKey());
        if (arrayList2.size() == 3) {
            if (arrayList2.get(0) == 0)
                databaseReference.child("comingdata").child("activities").child("button10").setValue("...");

            if (arrayList2.get(1) == 0)
                databaseReference.child("comingdata").child("activities").child("button11").setValue("...");

            if (arrayList2.get(2) == 0)
                databaseReference.child("comingdata").child("activities").child("button12").setValue("...");
        }
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        arrayList2.set(keylist2.indexOf(dataSnapshot.getKey()), dataSnapshot.getValue(Integer.class));

        if (arrayList2.get(0) == 0)
            databaseReference.child("comingdata").child("activities").child("button10").setValue("...");

        if (arrayList2.get(1) == 0)
            databaseReference.child("comingdata").child("activities").child("button11").setValue("...");

        if (arrayList2.get(2) == 0)
            databaseReference.child("comingdata").child("activities").child("button12").setValue("...");

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

        databaseReference.child("comingdata").child("maudo3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                maodo3.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("comingdata").child("groupname").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cominggroupname.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("comingdata").child("activities").child("button1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b1.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("comingdata").child("activities").child("button2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b2.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        databaseReference.child("comingdata").child("activities").child("button3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b3.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("comingdata").child("activities").child("button4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b4.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("comingdata").child("activities").child("button5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b5.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("comingdata").child("activities").child("button6").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b6.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("comingdata").child("activities").child("button7").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b7.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("comingdata").child("activities").child("button8").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b8.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference.child("comingdata").child("activities").child("button9").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b9.setText(dataSnapshot.getValue().toString());
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        databaseReference.child("comingdata").child("activities").child("button10").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b10.setText(dataSnapshot.getValue().toString());
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        databaseReference.child("comingdata").child("activities").child("button11").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b11.setText(dataSnapshot.getValue().toString());
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        databaseReference.child("comingdata").child("activities").child("button12").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                b12.setText(dataSnapshot.getValue().toString());
                progressDialog.dismiss();
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
      final   TextView username_nav = headerview.findViewById(R.id.user_name_nav);

        databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserModel  userModel = dataSnapshot.getValue(UserModel.class);
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
            startActivity(new Intent(ComingGroupActivity.this,Main2Activity.class));

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
                    if (!code.equals("A111")&&!code.equals("B222")&&!code.equals("C333")&&!code.equals("mgamal")&&!code.equals("msaeed")&&!code.equals("ne3ma")&&!code.equals("mikko")&&!code.equals("bisho")&&!code.equals("sam"))
                    {
                        startActivity(new Intent(ComingGroupActivity.this,Main2Activity.class));
                        finish();
                        Toast.makeText(ComingGroupActivity.this, "Enter the code!", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        startActivity(new Intent(ComingGroupActivity.this, GroupsActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }  else if (id == R.id.cominggroup_nav) {
            startActivity(new Intent(ComingGroupActivity.this, ComingGroupActivity.class));
            finish();


        } else if (id == R.id.score_nav) {
            startActivity(new Intent(ComingGroupActivity.this, ScoreActivity.class));
            finish();

        } else if (id == R.id.tranim_nav) {
            startActivity(new Intent(ComingGroupActivity.this, TranimActivity.class));
            finish();

        } else if (id == R.id.aya_nav) {
            startActivity(new Intent(ComingGroupActivity.this, AyaActivity.class));
            finish();

        }
        else if (id == R.id.mygroup_nav) {

            databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String code = dataSnapshot.getValue().toString();
                    if (!code.equals("A111")&&!code.equals("B222")&&!code.equals("C333")&&!code.equals("mgamal")&&!code.equals("msaeed")&&!code.equals("ne3ma")&&!code.equals("mikko")&&!code.equals("bisho")&&!code.equals("sam"))
                    { startActivity(new Intent(ComingGroupActivity.this,Main2Activity.class));
                        finish();
                        Toast.makeText(ComingGroupActivity.this, "Enter the code!", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        startActivity(new Intent(ComingGroupActivity.this, MyGroupActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else if (id == R.id.setting_nav) {
            startActivity(new Intent(ComingGroupActivity.this, Settings.class));
            finish();

        }
        else if (id == R.id.logout_nav) {
            firebaseAuth.signOut();
            startActivity(new Intent(ComingGroupActivity.this,MainActivity.class));
            finish();
        }
        else if (id == R.id.questions_nav)
        {
            startActivity(new Intent(ComingGroupActivity.this,QuestionActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
