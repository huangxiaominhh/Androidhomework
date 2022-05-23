package com.example.androidhomework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FourthActivity extends AppCompatActivity {
    Button btn4,btn5,btn6,btn7;
    //定义对应
    TextView tx5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        btn4=findViewById(R.id.btn_4);
        btn5=findViewById(R.id.btn_5);
        btn6=findViewById(R.id.btn_6);
        btn7=findViewById(R.id.btn_7);
        //绑定控件
        tx5=findViewById(R.id.text_05);
        //接收数据并提取数据
        String str1=getIntent().getStringExtra("hh");
        tx5.setText(str1);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthActivity.this, SixthActivity.class);
                startActivity(intent);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthActivity.this, ListenActivity.class);
                startActivity(intent);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthActivity.this, LookActivity.class);
                startActivity(intent);
            }
        });



    }
}
