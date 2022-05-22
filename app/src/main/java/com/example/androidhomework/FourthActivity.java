package com.example.androidhomework;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {
    //定义对应
    TextView tx5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        //绑定控件
        tx5=findViewById(R.id.text_05);
        //接收数据并提取数据
        String str1=getIntent().getStringExtra("hh");
        tx5.setText(str1);
    }
}
