package com.questionsanawya.dell.sanawyaapp;

import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Settings extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;
    RelativeLayout rel1,rel2;
    boolean checked;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();
        final CoordinatorLayout coordinatorLayout = findViewById(R.id.settings_layout);
        Switch mode = findViewById(R.id.modeswitch);
        sharedPreferences = getSharedPreferences("mode", 0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        boolean b = sharedPreferences.getBoolean("checked", true);
        final TextView rate = findViewById(R.id.rate), share = findViewById(R.id.share);
        ImageView rateimage = findViewById(R.id.rate_image), shareimage = findViewById(R.id.share_image);
        final TextView modetxt = findViewById(R.id.mode);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/asmaa.ttf");
        modetxt.setTypeface(typeface);
        share.setTypeface(typeface);
        rate.setTypeface(typeface);
        rel1 = findViewById(R.id.rel1);
        rel2 = findViewById(R.id.rel2);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));

                }
            }
        });
        rateimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));

                }
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "تطبيق جسد واحد" + "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
                startActivity(Intent.createChooser(intent, "مشاركة التطبيق"));
            }
        });
        shareimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "تطبيق جسد واحد" + "\n" + "https://play.google.com/store/apps/details?id=" + getPackageName());
                startActivity(Intent.createChooser(intent, "مشاركة التطبيق"));
            }
        });


        mode.setChecked(b);

        if (!b) {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#FFFAFAFA"));
            rel1.setBackgroundResource(R.drawable.view);
            rel2.setBackgroundResource(R.drawable.view);
            share.setTextColor(Color.parseColor("#000000"));
            rate.setTextColor(Color.parseColor("#000000"));
            modetxt.setTextColor(Color.parseColor("#000000"));

        } else {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#101c28"));
            rel1.setBackgroundResource(R.drawable.new_gradient);
            rel2.setBackgroundResource(R.drawable.new_gradient);
            share.setTextColor(Color.parseColor("#a3acb6"));
            rate.setTextColor(Color.parseColor("#a3acb6"));
            modetxt.setTextColor(Color.parseColor("#a3acb6"));
        }

        mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                editor.putBoolean("checked", b);
                editor.apply();


                sharedPreferences = getSharedPreferences("mode", 0);
                 checked = sharedPreferences.getBoolean("checked", true);
                if (!checked) {
                    coordinatorLayout.setBackgroundColor(Color.parseColor("#FFFAFAFA"));
                    rel1.setBackgroundResource(R.drawable.view);
                    rel2.setBackgroundResource(R.drawable.view);
                    share.setTextColor(Color.parseColor("#000000"));
                    rate.setTextColor(Color.parseColor("#000000"));
                    modetxt.setTextColor(Color.parseColor("#000000"));
                } else {
                    coordinatorLayout.setBackgroundColor(Color.parseColor("#101c28"));
                    rel1.setBackgroundResource(R.drawable.new_gradient);
                    rel2.setBackgroundResource(R.drawable.new_gradient);
                    share.setTextColor(Color.parseColor("#a3acb6"));
                    rate.setTextColor(Color.parseColor("#a3acb6"));
                    modetxt.setTextColor(Color.parseColor("#a3acb6"));
                }
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
         databaseReference = FirebaseDatabase.getInstance().getReference();
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
            startActivity(new Intent(Settings.this, Main2Activity.class));
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
                    {startActivity(new Intent(Settings.this,Main2Activity.class));
                        finish();
                        Toast.makeText(Settings.this, "Enter the code!", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        startActivity(new Intent(Settings.this, GroupsActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        } else if (id == R.id.cominggroup_nav) {
            startActivity(new Intent(Settings.this, ComingGroupActivity.class));
            finish();


        } else if (id == R.id.score_nav) {
            startActivity(new Intent(Settings.this, ScoreActivity.class));
            finish();


        } else if (id == R.id.tranim_nav) {
            startActivity(new Intent(Settings.this, TranimActivity.class));
            finish();


        } else if (id == R.id.aya_nav) {
            startActivity(new Intent(Settings.this, AyaActivity.class));
            finish();


        } else if (id == R.id.mygroup_nav) {
            databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String code = dataSnapshot.getValue().toString();
                    if (!code.equals("A111")&&!code.equals("B222")&&!code.equals("C333")&&!code.equals("mgamal")&&!code.equals("msaeed")&&!code.equals("ne3ma")&&!code.equals("mikko")&&!code.equals("bisho")&&!code.equals("sam"))
                    {
                        startActivity(new Intent(Settings.this,Main2Activity.class));
                        finish();
                        Toast.makeText(Settings.this, "Enter the code!", Toast.LENGTH_SHORT).show();

                    }
                    else {

                        startActivity(new Intent(Settings.this, MyGroupActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            startActivity(new Intent(Settings.this, MyGroupActivity.class));
            finish();


        } else if (id == R.id.setting_nav) {
            startActivity(new Intent(Settings.this, Settings.class));
            finish();


        } else if (id == R.id.logout_nav) {
            firebaseAuth.signOut();
            startActivity(new Intent(Settings.this, MainActivity.class));
            finish();

        } else if (id == R.id.questions_nav) {
            startActivity(new Intent(Settings.this, QuestionActivity.class));
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
