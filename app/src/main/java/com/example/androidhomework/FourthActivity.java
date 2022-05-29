package com.example.androidhomework;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
                finish();
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
    //添加选项菜单，显示菜单项
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);//获取当前菜单对象，加载菜单布局文件
        return true;//显示菜单
    }
    //菜单响应事件


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.return_item:
                Toast.makeText(this, "您选择了返回登录界面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,ThirdActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.out_item:
                Toast.makeText(this,"您选择了退出登录",Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this,ThirdActivity.class);
                startActivity(intent2);
                finish();
                break;
            default:
        }
        return super.onOptionsItemSelected(item);
    }


}
