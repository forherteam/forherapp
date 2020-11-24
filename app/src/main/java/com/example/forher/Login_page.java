package com.example.forher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class Login_page extends AppCompatActivity {


    EditText memail,mpassword;
    Button btnloginin;
    FirebaseAuth fauth;
    ProgressBar pgbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        memail = findViewById(R.id.Signupemilid);
        mpassword = findViewById(R.id.signupPassword);
        btnloginin = findViewById(R.id.Rigesterbtn);
        fauth = FirebaseAuth.getInstance();
        pgbar = findViewById(R.id.progressBar);


        btnloginin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    memail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Password is Required.");
                    return;
                }
                if(password.length()<6){
                    mpassword.setError("Password must be >= 6 Characters");
                    return;
                }
                pgbar.setVisibility(View.VISIBLE);

                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login_page.this,"User Created.",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),logged_in.class));

                        }
                        else{
                            Toast.makeText(Login_page.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            pgbar.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });


    }
}