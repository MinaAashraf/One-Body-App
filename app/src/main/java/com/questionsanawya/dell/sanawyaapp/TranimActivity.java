package com.questionsanawya.dell.sanawyaapp;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

public class TranimActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView textView;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String tranim[] = {"يا اِلهي اعمق الحب هواك","اِسمع صراخي ياسيدي","غريبا عشت في الدنيا","اِلهي اِلهي كن قائدي","يارب اسمع صلاتي","يا صاحب الحنان","امسك يارب ايدي","ما احلي السجود","اخذا صورة عبد","في وقت ضعفي","بالأحضان الأبوية","الرب قريب","درب الصليب","يدك المثقوبة","تنده عليا","كيف انسي","غالي عليك","لا تخف","ما لي سواك"};
public class MyAdapter extends ArrayAdapter
{

    public MyAdapter(@NonNull Context context, int resource, @NonNull Object[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.item,parent,false);
         textView = view.findViewById(R.id.trnima);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/asmaa.ttf");
       textView.setTypeface(typeface);
        ImageView imageView = view.findViewById(R.id.image_view);
        textView.setText(tranim[position]);
        imageView.setImageResource(R.drawable.tranim_image);
        return  view;
    }
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranim);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CoordinatorLayout coordinatorLayout = findViewById(R.id.tranim_layout);
        ListView listView = findViewById(R.id.list);
        firebaseAuth =FirebaseAuth.getInstance();
        SharedPreferences sharedPreferences  = getSharedPreferences("mode",0);
        boolean checked = sharedPreferences.getBoolean("checked",true);
        if (!checked)
        {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#FFFAFAFA"));
            listView.setBackgroundColor(Color.parseColor("#ffffff"));


        }
        else
        {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#101c28"));
            listView.setBackgroundColor(Color.parseColor("#101c28"));

        }


        MyAdapter adapter = new MyAdapter(this,android.R.layout.simple_list_item_1,tranim);
       listView.setAdapter(adapter);
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(TranimActivity.this,KaleematActivity.class).putExtra("t",i));
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
         databaseReference  = FirebaseDatabase.getInstance().getReference().child("users data");
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
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
            startActivity(new Intent(TranimActivity.this,Main2Activity.class));
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
                    {startActivity(new Intent(TranimActivity.this,Main2Activity.class));
                        finish();
                        Toast.makeText(TranimActivity.this, "Enter the code!", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        startActivity(new Intent(TranimActivity.this, GroupsActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }  else if (id == R.id.cominggroup_nav) {

            startActivity(new Intent(TranimActivity.this, ComingGroupActivity.class));
            finish();


        } else if (id == R.id.score_nav) {
            startActivity(new Intent(TranimActivity.this, ScoreActivity.class));
            finish();

        } else if (id == R.id.tranim_nav) {
            startActivity(new Intent(TranimActivity.this, TranimActivity.class));
            finish();


        } else if (id == R.id.aya_nav) {
            startActivity(new Intent(TranimActivity.this, AyaActivity.class));
            finish();


        }
        else if (id == R.id.setting_nav) {
            startActivity(new Intent(TranimActivity.this, Settings.class));
            finish();


        }
        else if (id == R.id.logout_nav) {
            firebaseAuth.signOut();
            startActivity(new Intent(TranimActivity.this,MainActivity.class));
            finish();

        }
        else if (id == R.id.mygroup_nav) {
            databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String code = dataSnapshot.getValue().toString();
                    if (!code.equals("A111")&&!code.equals("B222")&&!code.equals("C333")&&!code.equals("mgamal")&&!code.equals("msaeed")&&!code.equals("ne3ma")&&!code.equals("mikko")&&!code.equals("bisho")&&!code.equals("sam"))
                    {
                        startActivity(new Intent(TranimActivity.this,Main2Activity.class));
                        finish();
                        Toast.makeText(TranimActivity.this, "Enter the code!", Toast.LENGTH_SHORT).show();

                    }
                    else {

                        startActivity(new Intent(TranimActivity.this, MyGroupActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });}
        else if (id == R.id.questions_nav)
        {
            startActivity(new Intent(TranimActivity.this,QuestionActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
