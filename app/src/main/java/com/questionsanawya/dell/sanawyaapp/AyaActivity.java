package com.questionsanawya.dell.sanawyaapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class AyaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView aya_txt , username_nav;ImageView profile_nav;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aya);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        aya_txt = findViewById(R.id.ayatxt);
        String[] arr = getResources().getStringArray(R.array.ayat);
        Random rnd = new Random();
        int i = rnd.nextInt(arr.length);
        String txt = arr[i];
        aya_txt.setText(txt);
         firebaseAuth = FirebaseAuth.getInstance();
    Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/asmaa.ttf");
aya_txt.setTypeface(typeface);



        CoordinatorLayout coordinatorLayout = findViewById(R.id.aya_layout);
        SharedPreferences sharedPreferences  = getSharedPreferences("mode",0);

        boolean checked = sharedPreferences.getBoolean("checked",true);
        if (!checked)
        {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#FFFAFAFA"));
            aya_txt.setTextColor(Color.parseColor("#000000"));
        }
        else
        {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#101c28"));
            aya_txt.setTextColor(Color.parseColor("#ffffff"));
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        profile_nav = headerview.findViewById(R.id.profile_nav);
        username_nav = headerview.findViewById(R.id.user_name_nav);

        MenuItem menuItem = navigationView.getMenu().findItem(R.id.mygroup_nav);

         databaseReference = FirebaseDatabase.getInstance().getReference().child("users data");

        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
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
            startActivity(new Intent(AyaActivity.this,Main2Activity.class));
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.magmo3at_nav) {
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String code = dataSnapshot.getValue().toString();
                    if (!code.equals("A111")&&!code.equals("B222")&&!code.equals("C333")&&!code.equals("mgamal")&&!code.equals("msaeed")&&!code.equals("ne3ma")&&!code.equals("mikko")&&!code.equals("bisho")&&!code.equals("sam"))
                    {
                        startActivity(new Intent(AyaActivity.this,Main2Activity.class));
                        finish();
                        Toast.makeText(AyaActivity.this, "Enter the code!", Toast.LENGTH_SHORT).show();
                    }
                   else {
                        startActivity(new Intent(AyaActivity.this, GroupsActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if (id == R.id.mygroup_nav) {
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String code = dataSnapshot.getValue().toString();
                    if (!code.equals("A111")&&!code.equals("B222")&&!code.equals("C333")&&!code.equals("mgamal")&&!code.equals("msaeed")&&!code.equals("ne3ma")&&!code.equals("mikko")&&!code.equals("bisho")&&!code.equals("sam"))
                    { startActivity(new Intent(AyaActivity.this,Main2Activity.class));
                        finish();
                        Toast.makeText(AyaActivity.this, "Enter the code!", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        startActivity(new Intent(AyaActivity.this, MyGroupActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
         else if (id == R.id.cominggroup_nav) {
            startActivity(new Intent(AyaActivity.this, ComingGroupActivity.class));
            finish();

        } else if (id == R.id.score_nav) {
            startActivity(new Intent(AyaActivity.this, ScoreActivity.class));
            finish();

        } else if (id == R.id.tranim_nav) {
            startActivity(new Intent(AyaActivity.this, TranimActivity.class));
            finish();

        } else if (id == R.id.aya_nav) {
            startActivity(new Intent(AyaActivity.this, AyaActivity.class));
            finish();

        }
        else if (id == R.id.setting_nav) {
            startActivity(new Intent(AyaActivity.this, Settings.class));
            finish();

        }
        else if (id == R.id.logout_nav) {
          firebaseAuth.signOut();
          startActivity(new Intent(AyaActivity.this,MainActivity.class));
         finish();
        }
        else if (id == R.id.questions_nav)
        {
            startActivity(new Intent(AyaActivity.this,QuestionActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
