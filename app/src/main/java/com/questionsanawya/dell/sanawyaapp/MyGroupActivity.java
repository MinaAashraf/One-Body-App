package com.questionsanawya.dell.sanawyaapp;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;

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

import java.util.ArrayList;

public class MyGroupActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
TextView myname,mygroup,mywork;
ImageView myprofile;
ArrayList <Float> arrayList = new ArrayList<>();
    FirebaseAuth firebaseAuth;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    String uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        storageReference = FirebaseStorage.getInstance().getReference().child("profile images");
        CoordinatorLayout coordinatorLayout = findViewById(R.id.mymagmo3a_layout);
        SharedPreferences sharedPreferences  = getSharedPreferences("mode",0);
        boolean checked = sharedPreferences.getBoolean("checked",true);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/asmaa.ttf");
        TextView magmo3a,trteb;
        magmo3a = findViewById(R.id.magmo3a);
        trteb = findViewById(R.id.trteb);
        LinearLayout linearLayout = findViewById(R.id.linear);
        magmo3a = findViewById(R.id.magmo3a);
        trteb = findViewById(R.id.trteb);
        mygroup = findViewById(R.id.mygroup_name);
        mywork = findViewById(R.id.mywork);
        myname = findViewById(R.id.my_name);
        myprofile = findViewById(R.id.myprofile);
        magmo3a.setTypeface(typeface);
        trteb.setTypeface(typeface);
        mygroup.setTypeface(typeface);
        mywork.setTypeface(typeface);

        if (!checked)
        {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#FFFAFAFA"));
              linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
              myname.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            coordinatorLayout.setBackgroundColor(Color.parseColor("#101c28"));
            linearLayout.setBackgroundResource(R.drawable.new_gradient);
            myname.setTextColor(Color.parseColor("#054761"));

        }


         databaseReference = FirebaseDatabase.getInstance().getReference();
         firebaseAuth = FirebaseAuth.getInstance();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
           UserModel userModel = dataSnapshot.getValue(UserModel.class);
                Picasso.get().load(userModel.getUrl()).placeholder(R.drawable.profileimage).into(myprofile);
                myname.setText(userModel.getName());

                if (userModel.getCode().toString().equals("A111")||userModel.getCode().toString().equals("mgamal")||userModel.getCode().toString().equals("msaeed")) {
                    mygroup.setText("المصـابيـــح");
                    databaseReference.child("score").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            arrayList.add(dataSnapshot.getValue(Float.class));
                            if (arrayList.size()==3)
                            {
                                if (arrayList.get(0)>arrayList.get(1)&&arrayList.get(0)>arrayList.get(2))
                                    mywork.setText("1");
                                else if (arrayList.get(0)<arrayList.get(1)&& arrayList.get(0)<arrayList.get(2))
                               mywork.setText("3");
                                else mywork.setText("2");
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
                else if (userModel.getCode().toString().equals("B222")||userModel.getCode().toString().equals("bisho")||userModel.getCode().toString().equals("ne3ma")) {
                    mygroup.setText("ســيــســيــا");
                        databaseReference.child("score").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                arrayList.add(dataSnapshot.getValue(Float.class));
                                if (arrayList.size()==3)
                                {
                                    if (arrayList.get(2)>arrayList.get(1)&&arrayList.get(2)>arrayList.get(2))
                                        mywork.setText("1");
                                    else if (arrayList.get(2)<arrayList.get(1)&& arrayList.get(2)<arrayList.get(0))
                                        mywork.setText("3");
                                    else mywork.setText("2");
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

                else if (userModel.getCode().toString().equals("C333")||userModel.getCode().toString().equals("mikko")||userModel.getCode().toString().equals("sam")) {
                    mygroup.setText("هلـــبــيــس");
                    databaseReference.child("score").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            arrayList.add(dataSnapshot.getValue(Float.class));
                            if (arrayList.size()==3)
                            {
                                if (arrayList.get(1)>arrayList.get(0)&&arrayList.get(1)>arrayList.get(2))
                                    mywork.setText("1");
                                else if (arrayList.get(1)<arrayList.get(0)&& arrayList.get(1)<arrayList.get(2))
                                    mywork.setText("3");
                                else mywork.setText("2");
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





                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Choose image profile:"), 2);

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
            startActivity(new Intent(MyGroupActivity.this,Main2Activity.class));
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.magmo3at_nav) {
            startActivity(new Intent(MyGroupActivity.this, GroupsActivity.class));
            finish();

        }  else if (id == R.id.cominggroup_nav) {
            startActivity(new Intent(MyGroupActivity.this, ComingGroupActivity.class));
            finish();


        } else if (id == R.id.score_nav) {
            startActivity(new Intent(MyGroupActivity.this, ScoreActivity.class));
            finish();


        } else if (id == R.id.tranim_nav) {
            startActivity(new Intent(MyGroupActivity.this, TranimActivity.class));
            finish();


        } else if (id == R.id.aya_nav) {
            startActivity(new Intent(MyGroupActivity.this, AyaActivity.class));
            finish();


        }
        else if (id == R.id.setting_nav) {
            startActivity(new Intent(MyGroupActivity.this, Settings.class));
            finish();


        }
        else if (id == R.id.mygroup_nav) {
            startActivity(new Intent(MyGroupActivity.this, MyGroupActivity.class));
            finish();


        }
        else if (id == R.id.logout_nav) {
            firebaseAuth.signOut();
            startActivity(new Intent(MyGroupActivity.this,MainActivity.class));
            finish();

        }
        else if (id == R.id.questions_nav)
        {
            startActivity(new Intent(MyGroupActivity.this,QuestionActivity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if ((requestCode == 2) && resultCode == RESULT_OK && data != null) {
            anotheruri = data.getData();
            myprofile.setImageURI(anotheruri);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Profile image").setMessage("هل تريد اختيار هذه الصورة لتكون صورة حسابك ؟").setPositiveButton("اوافق", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    storageReference.child(firebaseAuth.getCurrentUser().getUid()).putFile(anotheruri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            uri = taskSnapshot.getDownloadUrl().toString();
                            databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).child("url").setValue(uri);
                            databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    UserModel  userModel = dataSnapshot.getValue(UserModel.class);
                                    Picasso.get().load(userModel.getUrl()).placeholder(R.drawable.profileimage).into(myprofile);


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                        }
                    });
                }
            }).setNegativeButton("اِلغاء", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
           databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).child("url").addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   String url = dataSnapshot.getValue().toString();
                   Picasso.get().load(url).into(myprofile);
               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });


                }
            }).show();

        }
    }

    Uri anotheruri;
}
