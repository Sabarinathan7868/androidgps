package com.example.studentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("WrapperTypeMayBePrimitive")
public class Sign_in extends AppCompatActivity {

    EditText etUserName, etPassword;
    DatabaseHelper db;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        db = new DatabaseHelper(this);
        etUserName = findViewById(R.id.et_user_name);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                Boolean checkEmailPass = db.userNamePassword(userName,password);
                //noinspection PointlessBooleanExpression
                if (checkEmailPass==true){
                    Intent studentDash = new Intent(Sign_in.this, StudentDatabaseDashboard.class);
                    startActivity(studentDash);
                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Wrong User or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}