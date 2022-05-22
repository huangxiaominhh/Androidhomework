package com.example.androidhomework;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    //定义对象
    Button btn_3,btn_login;
    EditText ed,ed_pwd;
    CheckBox checkBox;
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ed=findViewById(R.id.et_name);
        ed_pwd = findViewById(R.id.et_pwd);
        checkBox=findViewById(R.id.checkbox);
        btn_3=findViewById(R.id.btn_3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //单击按钮将输入的用户名、密码、复选框状态保存起来
                SharedPreferences.Editor editor=getSharedPreferences("myfile",0).edit();
                editor.putString("name",ed.getText().toString());//键：值，键值对方式存储
                editor.putString("pwd",ed_pwd.getText().toString());//把输入发密码存放在pwd键中
                editor.putBoolean("st",checkBox.isChecked());//把复选框的状态存放在status键里
                editor.commit();//提交，即为存储成功
                //页面跳转逻辑
                Intent intent = new Intent(ThirdActivity.this,FourthActivity.class);
                intent.putExtra("hh",ed.getText().toString());//键值
                startActivity(intent);
                finish();
                //创建一条居中带图片的toast并显示
                Toast toast=Toast.makeText(ThirdActivity.this,"点击头像去你想去的地方",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,2,2);//让toast居中，左右偏移量为0
                ImageView image2=new ImageView(ThirdActivity.this);//定义图片控件
                image2.setImageResource(R.drawable.img);//设置图片
                LinearLayout toastview=(LinearLayout) toast.getView();//定义toast的布局视图为线性布局
                toastview.addView(image2);//将图片加载到toast布局中
                toast.show();//显示toast
            }

        });
//选中记住密码复选框，下一次启动，获取昵称和密码并显示出来
        String myname=getSharedPreferences("myfile",0).getString("name","");
        String mypwd=getSharedPreferences("myfile",0).getString("pwd","");
        Boolean myst=getSharedPreferences("myfile",0).getBoolean("st",false);
       //判断过来的值
        if (myst==true) {
            ed.setText(myname);
            ed_pwd.setText(mypwd);
            checkBox.setChecked(true);
        }else{
            ed.setText("");
            ed_pwd.setText("");
            checkBox.setChecked(false);
        }
    }
}
