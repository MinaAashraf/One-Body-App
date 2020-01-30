package com.questionsanawya.dell.sanawyaapp;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
EditText email_edittxt , password_editxt;FirebaseAuth firebaseAuth;String email,password;boolean b;

Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
button = findViewById(R.id.btn);
email_edittxt = findViewById(R.id.emali_e_d);
password_editxt = findViewById(R.id.password_e_t);
firebaseAuth = FirebaseAuth.getInstance();
        TextView textView = findViewById(R.id.create);
        LinearLayout linearLayout = findViewById(R.id.main_layout);


if (firebaseAuth.getCurrentUser()!=null)
{
    startActivity(new Intent(MainActivity.this,Main2Activity.class));
    finish();
}





button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        progressDialog.show();
        b = true;
        email  = email_edittxt.getText().toString();
        password  = password_editxt.getText().toString();
        if (email_edittxt.getText().toString().isEmpty())
        {
            email_edittxt.setError("Required");
            b = false;
            progressDialog.dismiss();

        }

            if (password.isEmpty())
            {
                password_editxt.setError("Required");
               b =false;
               progressDialog.dismiss();
            }
            if (b)
            {
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            startActivity(new Intent(MainActivity.this,Main2Activity.class));
                            progressDialog.dismiss();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Check your account or internet connection..", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }

                });
            }



    }
});



textView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(MainActivity.this,SignupActivity.class));
    }
});

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SisoMOY").setMessage("هل تريد الخروج من التطبيق").setPositiveButton("اوافق", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).setNegativeButton("اِلغاء", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            return;
            }
        });
        builder.show();
    }
}
