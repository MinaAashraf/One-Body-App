package com.questionsanawya.dell.sanawyaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessagesActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayList<String> arrayList3 = new ArrayList<>();
    ArrayList<String> arrayList4 = new ArrayList<>();
    ArrayList<String> arrayList5 = new ArrayList<>();
    ArrayList<String> arrayList6 = new ArrayList<>();
    ArrayAdapter arrayAdapter1;
ArrayAdapter arrayAdapter2 ;
    ArrayAdapter arrayAdapter3;
    ArrayAdapter arrayAdapter4;
    ArrayAdapter arrayAdapter5;
    ArrayAdapter arrayAdapter6;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
      final   ArrayList<String> arrayList2 = new ArrayList<>();
        listView = findViewById(R.id.listq);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        LinearLayout linearLayout = findViewById(R.id.messages_layout);
        SharedPreferences sharedPreferences  = getSharedPreferences("mode",0);
        boolean checked = sharedPreferences.getBoolean("checked",true);
        if (!checked)
        {
            linearLayout.setBackgroundColor(Color.parseColor("#FFFAFAFA"));


        }
        else
        {
            linearLayout.setBackgroundColor(Color.parseColor("#0e1a26"));

        }



        databaseReference.child("users data").child(firebaseAuth.getCurrentUser().getUid()).child("code").addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        String code = dataSnapshot.getValue().toString();
        switch (code) {
            case "mikko":

                databaseReference.child("questions").child("maichael").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        arrayList1.add(dataSnapshot.getValue().toString());
                        arrayAdapter1 = new ArrayAdapter(MessagesActivity.this, android.R.layout.simple_list_item_1, arrayList1);
                        listView.setAdapter(arrayAdapter1);
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
                break;

            case "mgamal":

                databaseReference.child("questions").child("mina").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        arrayList2.add(dataSnapshot.getValue().toString());
                        arrayAdapter2 = new ArrayAdapter(MessagesActivity.this, android.R.layout.simple_list_item_1, arrayList2);
                        listView.setAdapter(arrayAdapter2);
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

                break;
            case "bisho":

                databaseReference.child("questions").child("beshoy").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        arrayList3.add(dataSnapshot.getValue().toString());
                        arrayAdapter3 = new ArrayAdapter(MessagesActivity.this, android.R.layout.simple_list_item_1, arrayList3);
                        listView.setAdapter(arrayAdapter3);
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
                break;
            case "msaeed":

                databaseReference.child("questions").child("mariam").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        arrayList4.add(dataSnapshot.getValue().toString());
                        arrayAdapter4 = new ArrayAdapter(MessagesActivity.this, android.R.layout.simple_list_item_1, arrayList4);
                        listView.setAdapter(arrayAdapter4);
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
                break;
            case "sam":

                databaseReference.child("questions").child("samah").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        arrayList5.add(dataSnapshot.getValue().toString());
                        arrayAdapter5 = new ArrayAdapter(MessagesActivity.this, android.R.layout.simple_list_item_1, arrayList5);
                        listView.setAdapter(arrayAdapter5);
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
                break;
            case "ne3ma":

                databaseReference.child("questions").child("ne3ma").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        arrayList6.add(dataSnapshot.getValue().toString());
                        arrayAdapter6 = new ArrayAdapter(MessagesActivity.this, android.R.layout.simple_list_item_1, arrayList6);
                        listView.setAdapter(arrayAdapter6);
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


    }
    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});



}

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MessagesActivity.this,Main2Activity.class));
        finish();
        super.onBackPressed();
    }
}
