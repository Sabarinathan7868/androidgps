package com.example.studentapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "student.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(userName text primary key,password text)");
        //TODO Create Table
        /*String name, String classNo, String section, String schoolName, String dob,
                String bloodGroup, String fatherName, String motherName, String parentsContactNo,
                String address1, String address2, String city, String state, String zip,
                String emergencyContactNo, String addLocation*/
        db.execSQL("Create table studentData(id INTEGER primary key AUTOINCREMENT NOT NULL,name text , genderRadioButton text,class text,section text, schoolname text,dob text,bloodGroup text,fatherName text,motherName text,parentsContactNo text,address1 text,address2 text,city text,state text,zip text,emergencyContactNo text,addLocation text, image BLOB, lat text,longs text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists user");
    }

    //inserting in database
    public boolean insert(String userName, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", userName);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);
        if (ins == 1) return false;
        else return true;
    }

    //check if email exists
    public boolean chkUserName(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where userName=?", new String[]{userName});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //checking the email and password
    public boolean userNamePassword(String userName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where userName=? and password=?", new String[]{userName, password});
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    //inserting user data into database
    public boolean addDataInsert(String name, String genderRadioButton, String classNo, String section, String schoolName, String dob,
                                 String bloodGroup, String fatherName, String motherName, String parentsContactNo,
                                 String address1, String address2, String city, String state, String zip,
                                 String emergencyContactNo, String addLocation, byte[] image, String lat, String longs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("genderRadioButton", genderRadioButton);
        contentValues.put("class", classNo);
        contentValues.put("section", section);
        contentValues.put("schoolName", schoolName);
        contentValues.put("dob", dob);
        contentValues.put("bloodGroup", bloodGroup);
        contentValues.put("fatherName", fatherName);
        contentValues.put("motherName", motherName);
        contentValues.put("parentsContactNo", parentsContactNo);
        contentValues.put("address1", address1);
        contentValues.put("address2", address2);
        contentValues.put("city", city);
        contentValues.put("state", state);
        contentValues.put("zip", zip);
        contentValues.put("emergencyContactNo", emergencyContactNo);
        contentValues.put("addLocation", addLocation);
        contentValues.put("image", image);
        contentValues.put("lat", lat);
        contentValues.put("longs", longs);
        long ins = db.insert("studentData", null, contentValues);
        if (ins == 1) return false;
        else return true;
    }

    public List<DataBean> getAllData() throws IOException {
        List<DataBean> dataBean = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from studentData ", new String[]{});
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                DataBean data = new DataBean();
                data.setId(cursor.getInt(cursor.getColumnIndex("id")));
                data.setName(cursor.getString(cursor.getColumnIndex("name")));
                data.setClassNo(cursor.getString(cursor.getColumnIndex("class")));
                data.setSection(cursor.getString(cursor.getColumnIndex("section")));
                data.setImage(getImage(cursor.getBlob(cursor.getColumnIndex("image"))));
                data.setLat(cursor.getString(cursor.getColumnIndex("lat")));
                data.setLongs(cursor.getString(cursor.getColumnIndex("longs")));
                //newly Added
                data.setSchoolName(cursor.getString(cursor.getColumnIndex("schoolname")));
                data.setGender(cursor.getString(cursor.getColumnIndex("genderRadioButton")));
                data.setDob(cursor.getString(cursor.getColumnIndex("dob")));
                data.setBloodGroup(cursor.getString(cursor.getColumnIndex("bloodGroup")));
                data.setFatherName(cursor.getString(cursor.getColumnIndex("fatherName")));
                data.setMotherName(cursor.getString(cursor.getColumnIndex("motherName")));
                data.setParentContactNo(cursor.getString(cursor.getColumnIndex("parentsContactNo")));
                data.setAdd1(cursor.getString(cursor.getColumnIndex("address1")));
                data.setAdd2(cursor.getString(cursor.getColumnIndex("address2")));
                data.setCity(cursor.getString(cursor.getColumnIndex("city")));
                data.setState(cursor.getString(cursor.getColumnIndex("state")));
                data.setZip(cursor.getString(cursor.getColumnIndex("zip")));
                data.setEmergencyContactNo(cursor.getString(cursor.getColumnIndex("emergencyContactNo")));
                data.setLocation(cursor.getString(cursor.getColumnIndex("addLocation")));
                dataBean.add(data);
            }
        }
        return dataBean;
    }


    public Bitmap getImage(byte[] image) throws IOException {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
