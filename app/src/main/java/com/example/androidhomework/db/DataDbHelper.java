package com.example.androidhomework.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataDbHelper extends SQLiteOpenHelper {
    static int dbVersion=1;
    static String name="user.db";
   public DataDbHelper(Context context){
       super(context,name,null,dbVersion);
   }
    @Override
    public void onCreate(SQLiteDatabase db) {
     String sql="create table user(id integer primary key autoincrement," +
             "name varchar(20),password varchar(20),birthday data,sex varchar(2))";
               db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {

    }
}
