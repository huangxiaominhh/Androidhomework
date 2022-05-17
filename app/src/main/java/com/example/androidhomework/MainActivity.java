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
    Button btn_1,btn_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_1=findViewById(R.id.btn_1);
        btn_2=findViewById(R.id.btn_2);
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
                Toast toast=Toast.makeText(MainActivity.this,"进入登录/注册",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);//让toast居中
                ImageView image1=new ImageView(MainActivity.this);
                image1.setImageResource(R.drawable.a1);
                LinearLayout toastview=(LinearLayout) toast.getView();
                toastview.addView(image1);
                toast.show();
                Intent intent=new Intent("android.intent.action.SecondActivity11");
                startActivity(intent);

            }
        });
        Toast.makeText(MainActivity.this, "欢迎进入小世界", Toast.LENGTH_LONG).show();//进入app浮现的一句话
    }
}