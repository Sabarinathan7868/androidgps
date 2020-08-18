package com.example.studentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class ViewDataActivity extends AppCompatActivity {

    RecyclerView rvViewData;

    DatabaseHelper addDataDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         String TAG=ViewDataActivity.class.getSimpleName();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_data);
        rvViewData = findViewById(R.id.rv_view_data);
      addDataDb = new DatabaseHelper(this);

/*
        rvViewData.setHasFixedSize(true);
*/
        rvViewData.setLayoutManager(new LinearLayoutManager(this));
//        rvViewData.setItemAnimator(new DefaultItemAnimator());
        try {
            rvViewData.setAdapter(new DataBeanAdapter(this.addDataDb.getAllData()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}