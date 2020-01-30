package com.questionsanawya.dell.sanawyaapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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

import java.util.ArrayList;

public class SignupActivity extends AppCompatActivity {
    EditText nameedit, passwordedit, confirmpasswordedit, emailedittxt;
    Typeface typeface;
    ImageView profileimage;
    LinearLayout linearLayout;
    boolean bb;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;
    Button register;
    boolean b;
    String name, email, password, confirmpassword;
    String downloadurl;
    DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        linearLayout = findViewById(R.id.li);
        nameedit = findViewById(R.id.name_edittxt);
        passwordedit = findViewById(R.id.password_edittxt);
        confirmpasswordedit = findViewById(R.id.condirm_password_edittxt);
        emailedittxt = findViewById(R.id.email_editext);

        profileimage = findViewById(R.id.profile_image);
        register = findViewById(R.id.register_btn);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users data");
        storageReference = FirebaseStorage.getInstance().getReference().child("profile images");
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                name = nameedit.getText().toString();

                email = emailedittxt.getText().toString();
                password = passwordedit.getText().toString();
                confirmpassword = confirmpasswordedit.getText().toString();


                bb = false;
                b = true;
                if (nameedit.getText().toString().isEmpty()) {
                    nameedit.setError("Required");
                    b = false;
                }

                if (passwordedit.getText().toString().isEmpty()) {
                    passwordedit.setError("Required");
                    b = false;
                } else if (passwordedit.getText().toString().trim().length() < 8) {

                    passwordedit.setError("This password is very week!");
                    b = false;
                }
                if (confirmpasswordedit.getText().toString().isEmpty()) {
                    confirmpasswordedit.setError("Required");
                    b = false;
                } else if (!confirmpasswordedit.getText().toString().trim().equals(passwordedit.getText().toString().trim())) {
                    confirmpasswordedit.setError("Password not match.Check it");
                    b = false;
                }

                if (emailedittxt.getText().toString().isEmpty()) {
                    emailedittxt.setError("Required");
                    b = false;
                } else if (!((emailedittxt.getText().toString().contains("@gmail") || emailedittxt.getText().toString().contains("@yahoo")) && emailedittxt.getText().toString().contains(".com"))) {
                    emailedittxt.setError("invalid");
                    b = false;
                }


                if (b) {
                    if (uri == null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                        builder.setTitle("Select image").setIcon(R.drawable.profileimage).setMessage("هل تريد الصورة الاِفتراضية ان تكون صورتك ؟")
                                .setPositiveButton("اوافق", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        downloadurl = "https://firebasestorage.googleapis.com/v0/b/sanawyaapp.appspot.com/o/user%20images%2Fdefault%20profile%2Fprofileimage.png?alt=media&token=2e7314a7-33bd-4ff1-899c-c2d7ecdc9cb1";
                                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    UserModel userModel = new UserModel(name, email, "Empty", downloadurl, "___");
                                                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {

                                                                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                                                progressDialog.dismiss();
                                                                databaseReference1.child("enable").child(firebaseAuth.getCurrentUser().getUid()).setValue(0);

                                                                finish();
                                                                progressDialog.dismiss();


                                                            } else {
                                                                Toast.makeText(SignupActivity.this, "Error.Try again.", Toast.LENGTH_SHORT).show();
                                                                progressDialog.dismiss();
                                                            }

                                                        }
                                                    });
                                                } else {
                                                    Toast.makeText(SignupActivity.this, "Error.Try again.", Toast.LENGTH_SHORT).show();
                                                    progressDialog.dismiss();
                                                }
                                            }
                                        });
                                    }
                                }).setNegativeButton("اختيار صورة", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/*");
                                intent.putExtra("crop", "true").putExtra("aspectX", "1").putExtra("aspectY", "1")
                                        .putExtra("outputX", "200").putExtra("outputY", "200").putExtra("return-data", "true");
                                startActivityForResult(Intent.createChooser(intent, "Choose image profile:"), 1);
                                progressDialog.dismiss();


                            }
                        });
                        builder.show();


                    } else {
                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull final Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    storageReference.child(firebaseAuth.getCurrentUser().getUid()).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            if (task.isSuccessful()) {
                                                downloadurl = taskSnapshot.getDownloadUrl().toString();
                                                UserModel userModel = new UserModel(name, email, "Empty", downloadurl, "___");
                                                databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {

                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                                            progressDialog.dismiss();
                                                            finish();
                                                            databaseReference1.child("enable").child(firebaseAuth.getCurrentUser().getUid()).setValue(0);


                                                        } else {
                                                            Toast.makeText(SignupActivity.this, "Error.Try again.", Toast.LENGTH_SHORT).show();
                                                            progressDialog.dismiss();
                                                        }
                                                    }
                                                });
                                            } else {
                                                Toast.makeText(SignupActivity.this, "Error.Try again.", Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(SignupActivity.this, "Error.Try again.", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }
                        });


                    }
                } else {
                    progressDialog.dismiss();
                }
            }
        });


        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Choose image profile:"), 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (((requestCode == 0) || (requestCode == 1)) && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            profileimage.setImageURI(uri);
        }
    }

    Uri uri;
}
