package com.example.androidhomework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    //定义对象
    Button btn_3;
    EditText ed;
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ed=findViewById(R.id.et_name);
        btn_3=findViewById(R.id.btn_3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //页面跳转逻辑
                Intent intent = new Intent(ThirdActivity.this,FourthActivity.class);
                intent.putExtra("hh",ed.getText().toString());//键值
                startActivity(intent);
            }

        });
    }
}
