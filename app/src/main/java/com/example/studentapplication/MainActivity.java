package com.example.studentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView signUp;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp = findViewById(R.id.sign_up);
        btnLogin = findViewById(R.id.btn_login);

      /*  if (PreferenceUtiles.getUserName(this) != null || !PreferenceUtiles.getUserName(this).equals("")){
            Intent intent = new Intent(MainActivity.this, StudentDatabaseDashboard.class);
            startActivity(intent);
        }else{

        }*/

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Intent signIn = new Intent(MainActivity.this, Sign_in.class);
                        startActivity(signIn);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(signUp);
            }
        });
    }
}