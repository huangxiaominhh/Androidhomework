package com.example.androidhomework;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
                //创建一条居中带图片的toast并显示
                Toast toast=Toast.makeText(FourthActivity.this,"写一写",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,2,2);//让toast居中，左右偏移量为0
                ImageView image2=new ImageView(FourthActivity.this);//定义图片控件
                image2.setImageResource(R.drawable.avatar_1);//设置图片
                LinearLayout toastview=(LinearLayout) toast.getView();//定义toast的布局视图为线性布局
                toastview.addView(image2);//将图片加载到toast布局中
                toast.show();//显示toast
                startActivity(intent);
                finish();

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthActivity.this, SixthActivity.class);
                Toast toast=Toast.makeText(FourthActivity.this,"探羊",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,2,2);//让toast居中，左右偏移量为0
                ImageView image2=new ImageView(FourthActivity.this);//定义图片控件
                image2.setImageResource(R.drawable.sheep2);//设置图片
                LinearLayout toastview=(LinearLayout) toast.getView();//定义toast的布局视图为线性布局
                toastview.addView(image2);//将图片加载到toast布局中
                toast.show();//显示toast
                startActivity(intent);
                finish();
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthActivity.this, ListenActivity.class);
                //创建一条居中带图片的toast并显示
                Toast toast=Toast.makeText(FourthActivity.this,"按住鼠标滑动点击歌曲一起听音乐吧",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,2,2);//让toast居中，左右偏移量为0
                ImageView image2=new ImageView(FourthActivity.this);//定义图片控件
                image2.setImageResource(R.drawable.mu);//设置图片
                LinearLayout toastview=(LinearLayout) toast.getView();//定义toast的布局视图为线性布局
                toastview.addView(image2);//将图片加载到toast布局中
                toast.show();//显示toast
                startActivity(intent);
                finish();
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FourthActivity.this, LookActivity.class);
                Toast toast=Toast.makeText(FourthActivity.this,"看一看",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,2,2);//让toast居中，左右偏移量为0
                ImageView image2=new ImageView(FourthActivity.this);//定义图片控件
                image2.setImageResource(R.drawable.avatar_1);//设置图片
                LinearLayout toastview=(LinearLayout) toast.getView();//定义toast的布局视图为线性布局
                toastview.addView(image2);//将图片加载到toast布局中
                toast.show();//显示toast
                startActivity(intent);
                finish();
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
