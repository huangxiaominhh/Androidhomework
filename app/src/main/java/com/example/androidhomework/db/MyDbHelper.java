package com.example.androidhomework.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.androidhomework.User;

import java.util.ArrayList;

public class MyDbHelper extends SQLiteOpenHelper {
    private static String DBNAME="homework";
    private static int VERSION=1;
    private SQLiteDatabase db;

    public MyDbHelper( Context context) {
        super(context, DBNAME, null, VERSION);
        db = getReadableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table memory(id Integer primary key,title TEXT,content TEXT)");
        db.execSQL("CREATE TABLE  IF NOT EXISTS user(_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,pwd TEXT  )"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }


    public void add(String name, String pwd){
        db.execSQL("INSERT INTO user (name,pwd) VALUES(?,?)",new Object[]{name,pwd});
    }
    public void delete(String name,String pwd){
        db.execSQL("DELETE FROM user WHERE name = AND pwd ="+name+pwd);
    }
    public void updata(String pwd){
        db.execSQL("UPDATE user SET pwd = ?",new Object[]{pwd});
    }

    public ArrayList<User> getAllData(){
        ArrayList<User> list = new ArrayList<User>();
        @SuppressLint("Recycle") Cursor cursor = db.query("user",null,null,null,null,null,"name DESC");
        while(cursor.moveToNext()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String pwd = cursor.getString(cursor.getColumnIndex("pwd"));

            list.add(new User(name,pwd));
        }
        return list;
    }
}
