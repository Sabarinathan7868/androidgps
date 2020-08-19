package com.example.studentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    TextView signIn;
    EditText etUserName, etPhoneNo, etPassword, etConfirmPassword;
    Button btnSignUp;

    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = new DatabaseHelper(this);

        signIn = findViewById(R.id.sign_in);
        etUserName = findViewById(R.id.et_user_name);
        etPhoneNo = findViewById(R.id.et_phone_number);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnSignUp = findViewById(R.id.btn_sign_up);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = etUserName.getText().toString();
                String phoneNo = etPhoneNo.getText().toString();
                String password = etPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if (userName.equals("") || phoneNo.equals("") || password.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(SignUpActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(confirmPassword)) {
                    /*  Boolean chkUserName = db.chkUserName(userName);
                      if (chkUserName==true){*/
                        Boolean insert = db.insert(userName, password);
                        if (insert == true) {
                            etUserName.getText().clear();
                            etPhoneNo.getText().clear();
                            etPassword.getText().clear();
                            etConfirmPassword.getText().clear();
                            Intent intent = new Intent(SignUpActivity.this, Sign_in.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } /*else {
                            Toast.makeText(getApplicationContext(), "UserName Already Exists", Toast.LENGTH_SHORT).show();
                        }*/
                    } else {
                        Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn = new Intent(SignUpActivity.this, Sign_in.class);
                startActivity(signIn);
            }
        });
    }
}