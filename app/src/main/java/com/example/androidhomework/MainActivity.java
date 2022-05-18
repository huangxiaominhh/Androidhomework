package com.example.androidhomework;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_1,btn_2;//定义对象


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_1=findViewById(R.id.btn_1);//绑定控件
        btn_2=findViewById(R.id.btn_2);
        //按钮单击事件
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
             builder.setIcon(R.drawable.a1);
             builder.setTitle("温馨提示");
             builder.setMessage("确定要退出吗？");
             builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {
                     finish();
                 }
             });
             builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialogInterface, int i) {

                 }
             });
             builder.create().show();

            }

        });


        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建一条居中带图片的toast并显示
                Toast toast=Toast.makeText(MainActivity.this,"注册页面",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);//让toast居中，左右偏移量为0
                ImageView image1=new ImageView(MainActivity.this);//定义图片控件
                image1.setImageResource(R.mipmap.a1);//设置图片
                LinearLayout toastview=(LinearLayout) toast.getView();//定义toast的布局视图为线性布局
                toastview.addView(image1);//将图片加载到toast布局中
                toast.show();//显示toast
                Intent intent=new Intent("android.intent.action.SecondActivity11");
                startActivity(intent);

            }
        });
        //创建一条显示时间短的toast
        Toast.makeText(MainActivity.this, "欢迎进入小世界", Toast.LENGTH_SHORT).show();//进入app浮现的一句话
    }
}