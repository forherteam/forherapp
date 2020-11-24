package com.example.forher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class firstpage extends AppCompatActivity {

    Button btnfirstpage;
    FirebaseAuth fauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);


        fauth = FirebaseAuth.getInstance();
        if(fauth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),logged_in.class));
            finish();

        }

        btnfirstpage = findViewById(R.id.signupbtn);


        btnfirstpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensignup();
            }
        });
        

    }


    private void opensignup() {
        Intent intentsignup = new Intent(this,Login_page.class);
        startActivity(intentsignup);

    }
}


