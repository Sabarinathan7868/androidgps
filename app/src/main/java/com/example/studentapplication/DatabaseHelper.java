package com.example.studentapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(userName text primary key,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists user");
    }

    //inserting in database
    public  boolean insert(String userName, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName",userName);
        contentValues.put("password",password);
        long ins = db.insert("user", null,contentValues);
        if (ins==1) return  false;
        else return true;
    }

    //check if email exists
    public boolean chkUserName(String userName){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where userName=?",new String[]{userName});
        if (cursor.getCount()>0) return false;
        else return true;
    }

    //checking the email and password
    public boolean userNamePassword(String userName,String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where userName=? and password=?",new String[]{userName,password});
        if (cursor.getCount()>0) return true;
        else return false;
    }

}
