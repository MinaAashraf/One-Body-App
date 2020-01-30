package com.questionsanawya.dell.sanawyaapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class QuestionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth firebaseAuth;
    ImageView profile_nav;TextView username_nav;
    DatabaseReference databaseReference1;
int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final EditText editText = findViewById(R.id.question_layout);

        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("users data");

        CoordinatorLayout coordinatorLayout = findViewById(R.id.q_layout);
        SharedPreferences sharedPreferences  = getSharedPreferences("mode",0);

        boolean checked = sharedPreferences.getBoolean("checked",true);
        if (!checked)
        {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#FFFAFAFA"));
            editText.setBackgroundResource(R.drawable.view);

        }
        else
        {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#101c28"));
            editText.setBackgroundColor(Color.parseColor("#a7a2a2"));

        }




        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("questions");
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this,R.array.names,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Button send_btn = findViewById(R.id.send_btn);
        firebaseAuth = FirebaseAuth.getInstance();

        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/asmaa.ttf");
        editText.setTypeface(typeface);
        send_btn.setTypeface(typeface);

        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             position = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty())
                {
                    editText.setError("اكتب شيء!");
                    return;
                }
                else {
                    switch (position)
                    {
                        case 0:
                            databaseReference.child("maichael").push().setValue(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(QuestionActivity.this, "تم الارسال الي ا.مايكل صبحي.", Toast.LENGTH_SHORT).show();

                                        editText.setText("");
                                    }

                                    else
                                    {
                                        Toast.makeText(QuestionActivity.this, "خطأ..تأكد من اتصالك بالاِنترنت.", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                             break;
                        case 1:
                            databaseReference.child("mina").push().setValue(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(QuestionActivity.this, "تم الارسال الي ا.مينا جمال.", Toast.LENGTH_SHORT).show();
                                        editText.setText("");

                                    }
                                    else
                                    {
                                        Toast.makeText(QuestionActivity.this, "خطأ..تأكد من اتصالك بالاِنترنت.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            break;
                        case 2:
                            databaseReference.child("beshoy").push().setValue(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(QuestionActivity.this, "تم الارسال الي ا.بيشوي القس.", Toast.LENGTH_SHORT).show();
                                        editText.setText("");

                                    }
                                    else
                                    {
                                        Toast.makeText(QuestionActivity.this, "خطأ..تأكد من اتصالك بالاِنترنت.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            break;
                        case 3:
                            databaseReference.child("mariam").push().setValue(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(QuestionActivity.this, "تم الارسال الي م.مريم.", Toast.LENGTH_SHORT).show();
                                        editText.setText("");

                                    }
                                    else
                                    {
                                        Toast.makeText(QuestionActivity.this, "خطأ..تأكد من اتصالك بالاِنترنت.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            break;
                        case 4:
                            databaseReference.child("samah").push().setValue(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(QuestionActivity.this, "تم الارسال الي م.سماح.", Toast.LENGTH_SHORT).show();
                                        editText.setText("");

                                    }
                                    else
                                    {
                                        Toast.makeText(QuestionActivity.this, "خطأ..تأكد من اتصالك بالاِنترنت.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            break;
                        case 5:
                            databaseReference.child("ne3ma").push().setValue(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(QuestionActivity.this, "تم الارسال الي م.نعمة", Toast.LENGTH_SHORT).show();
                                        editText.setText("");


                                    }
                                    else
                                    {
                                        Toast.makeText(QuestionActivity.this, "خطأ..تأكد من اتصالك بالاِنترنت.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            break;
                        default:
                            databaseReference.child("maichael").push().setValue(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(QuestionActivity.this, "تم الارسال الي الكل.", Toast.LENGTH_SHORT).show();
                                        databaseReference.child("mina").push().setValue(editText.getText().toString());
                                        databaseReference.child("beshoy").push().setValue(editText.getText().toString());
                                        databaseReference.child("mariam").push().setValue(editText.getText().toString());
                                        databaseReference.child("samah").push().setValue(editText.getText().toString());
                                        databaseReference.child("ne3ma").push().setValue(editText.getText().toString());
                                       editText.setText("");

                                    }
                                    else
                                    {
                                        Toast.makeText(QuestionActivity.this, "خطأ..تأكد من اتصالك بالاِنترنت.", Toast.LENGTH_SHORT).show();
                                    }


                                        }
                            });
                            break;


                    }
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
        profile_nav = headerview.findViewById(R.id.profile_nav);
        username_nav = headerview.findViewById(R.id.user_name_nav);



        databaseReference1.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
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
            startActivity(new Intent(QuestionActivity.this,Main2Activity.class));
            finish();
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.magmo3at_nav) {
            databaseReference1.child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String code = dataSnapshot.getValue().toString();
                    if (!code.equals("A111")&&!code.equals("B222")&&!code.equals("C333")&&!code.equals("mgamal")&&!code.equals("msaeed")&&!code.equals("ne3ma")&&!code.equals("mikko")&&!code.equals("bisho")&&!code.equals("sam"))
                    {startActivity(new Intent(QuestionActivity.this,Main2Activity.class));
                        finish();
                        Toast.makeText(QuestionActivity.this, "Enter the code!", Toast.LENGTH_SHORT).show();
                    }
                    else {

                        startActivity(new Intent(QuestionActivity.this, GroupsActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }  else if (id == R.id.cominggroup_nav) {
            startActivity(new Intent(QuestionActivity.this, ComingGroupActivity.class));
            finish();


        } else if (id == R.id.score_nav) {
            startActivity(new Intent(QuestionActivity.this, ScoreActivity.class));
            finish();


        } else if (id == R.id.tranim_nav) {
            startActivity(new Intent(QuestionActivity.this, TranimActivity.class));
            finish();


        } else if (id == R.id.aya_nav) {
            startActivity(new Intent(QuestionActivity.this, AyaActivity.class));
            finish();


        }
        else if (id == R.id.mygroup_nav) {
            databaseReference1.child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String code = dataSnapshot.getValue().toString();
                    if (!code.equals("A111")&&!code.equals("B222")&&!code.equals("C333")&&!code.equals("mgamal")&&!code.equals("msaeed")&&!code.equals("ne3ma")&&!code.equals("mikko")&&!code.equals("bisho")&&!code.equals("sam"))
                    {
                        startActivity(new Intent(QuestionActivity.this,Main2Activity.class));
                        finish();
                        Toast.makeText(QuestionActivity.this, "Enter the code!", Toast.LENGTH_SHORT).show();

                    }
                    else {

                        startActivity(new Intent(QuestionActivity.this, MyGroupActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
        else if (id == R.id.setting_nav) {
            startActivity(new Intent(QuestionActivity.this, Settings.class));
            finish();


        }
        else if (id == R.id.logout_nav) {
            firebaseAuth.signOut();
            startActivity(new Intent(QuestionActivity.this,MainActivity.class));
            finish();

        }
        else if (id == R.id.questions_nav)
        {
            startActivity(new Intent(QuestionActivity.this,QuestionActivity.class));
            finish();
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
