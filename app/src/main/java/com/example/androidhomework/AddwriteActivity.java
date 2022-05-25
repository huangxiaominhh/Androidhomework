package com.example.androidhomework;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidhomework.db.MyDbHelper;

import java.sql.Time;

public class AddwriteActivity extends AppCompatActivity {
    //定义对象
    EditText title, content;
    Button  btn_save;
    MyDbHelper mhelper;
    SQLiteDatabase db;//定义数据库对象


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addwrite);
        //1绑定控件
        initView();

        //把信息保存到数据库
        btnSave();
    }


    private void initView() {
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        btn_save = findViewById(R.id.save);
        mhelper=new MyDbHelper(AddwriteActivity.this);//实例化
        db = mhelper.getWritableDatabase();//具有读写权限

    }

    //把信息保存到数据库中
    private void btnSave() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存信息到数据
                ContentValues contentValues=new ContentValues();//一行
                contentValues.put("title",title.getText().toString());//一行1列
                contentValues.put("content",content.getText().toString());//一行3列
                db.insert("memory",null,contentValues);
                Toast.makeText(AddwriteActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            //跳转到writeActivity页面
                Intent intent=new Intent(AddwriteActivity.this,WriteActivity.class);
                startActivity(intent);
                finish();
            }

        });
    }

}


