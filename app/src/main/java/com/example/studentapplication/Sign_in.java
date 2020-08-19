package com.example.studentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("WrapperTypeMayBePrimitive")
public class Sign_in extends AppCompatActivity {

    EditText etUserName, etPassword;
    DatabaseHelper db;
    Button btnLogin;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        db = new DatabaseHelper(this);
        etUserName = findViewById(R.id.et_user_name);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_sign_in_login);
        signUp = findViewById(R.id.sign_up);

        //TODO for login shared preference
/*        if (PreferenceUtiles.getUserName(this) != null || !PreferenceUtiles.getUserName(this).equals("")){
            Intent intent = new Intent(Sign_in.this, StudentDatabaseDashboard.class);
            startActivity(intent);
        }else{

        }*/

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_in.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                Boolean checkEmailPass = db.userNamePassword(userName, password);
                //noinspection PointlessBooleanExpression
                if (checkEmailPass == true) {
//                    PreferenceUtiles.saveUserName(userName, getApplicationContext());
//                    PreferenceUtiles.savePassword(password, getApplicationContext());
                    Intent studentDash = new Intent(Sign_in.this, StudentDatabaseDashboard.class);
                    startActivity(studentDash);
                    Toast.makeText(getApplicationContext(), "Successfully Login", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong User or Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}