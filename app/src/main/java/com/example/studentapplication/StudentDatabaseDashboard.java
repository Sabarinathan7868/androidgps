package com.example.studentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StudentDatabaseDashboard extends AppCompatActivity {

    CardView addStudent, viewStudent, mapView;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_database_dashboard);

        addStudent = findViewById(R.id.cv_add_student);
        viewStudent = findViewById(R.id.cv_view_student);
        mapView = findViewById(R.id.cv_map_view);
        btnLogout = findViewById(R.id.btn_logout);


        //TODO Shared Preference LOGOUT
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        PreferenceUtiles.saveUserName("",getApplicationContext());
        PreferenceUtiles.savePassword("",getApplicationContext());
        Intent intent = new Intent(StudentDatabaseDashboard.this, Sign_in.class);
        startActivity(intent);
        finish();
            }
        });


        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addData = new Intent(StudentDatabaseDashboard.this, AddDataActivity.class);
                startActivity(addData);
            }
        });

        viewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewData = new Intent(StudentDatabaseDashboard.this, ViewDataActivity.class);
                startActivity(viewData);
            }
        });

        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapView = new Intent(StudentDatabaseDashboard.this, MapsActivity.class);
                startActivity(mapView);
            }
        });


    }
}