package com.example.androidhomework;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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


import com.example.androidhomework.db.MyDbHelper;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {
    //定义对象
    private MyDbHelper mMyDbHelper;
    private EditText et_name, ed_pwd;
    Button btn_3;
    CheckBox checkBox;
    private String username ;
    private SharedPreferences.Editor editor;
    @SuppressLint("CommitePrefEdits")
    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        mMyDbHelper = new MyDbHelper(this);

        initView();

        SharedPreferences sp = getSharedPreferences("user_mes", MODE_PRIVATE);
        editor = sp.edit();
        if(sp.getBoolean("flag",false)) {
            String user_read = sp.getString("user", "");
            String pwd_read = sp.getString("pwd", "");
            et_name.setText(user_read);
            ed_pwd.setText(pwd_read);
        }
        }

    private void initView() {
        et_name=findViewById(R.id.et_name);
        ed_pwd = findViewById(R.id.et_pwd);
        checkBox=findViewById(R.id.checkbox);
        btn_3=findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);
        SQLiteDatabase db = mMyDbHelper.getWritableDatabase();//具有读写权限

    }

    @Override
            public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_3:
                String name = et_name.getText().toString().trim();
                String pwd = ed_pwd.getText().toString().trim();
                Log.i("123", "onClick:name "+name);
                Log.i("123", "onClick:pwd) "+pwd);
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pwd)) {
                    ArrayList<User> data = mMyDbHelper.getAllData();
                    boolean match = false;
                    boolean match2 = false;
                    Log.i("123", "onClick:user "+data.toString());
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        Log.i("123", "onClick:user.getName() "+user.getName());
                        Log.i("123", "onClick:user.getPwd() "+user.getPwd());
                        if ((name.equals(user.getName()) && pwd.equals(user.getPwd()))) {
                            username = user.getName();
                            match = true;
                            if (checkBox.isChecked()) {
                                editor.putBoolean("flag", true);
                                editor.putString("user", user.getName());
                                editor.putString("pwd", user.getPwd());
                                editor.apply();
                                match2 = true;
                            } else {
                                editor.putString("user", user.getName());
                                editor.putString("pwd", "");
                                editor.clear();
                                editor.apply();
                                match2 = false;
                            }
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        if (match2) {
                            Toast.makeText(this, "成功记住密码", Toast.LENGTH_SHORT).show();
                            checkBox.setChecked(true);

                        }
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ThirdActivity.this,FourthActivity.class);
                        intent.putExtra("hh",et_name.getText().toString());//键值
                        startActivity(intent);
                        finish();
                        Runnable target;
                        //用线程启动
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                try {
                                    sleep(2000);//2秒 模拟登录时间
                                    String user_name = username;
                                    Intent intent1 = new Intent(ThirdActivity.this, FourthActivity.class);//设置自己跳转到成功的界面
                                    //创建一条居中带图片的toast并显示
                                    Toast toast = Toast.makeText(ThirdActivity.this, "点击头像去探索小世界吧", Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER, 2, 2);//让toast居中，左右偏移量为0
                                    ImageView image2 = new ImageView(ThirdActivity.this);//定义图片控件
                                    image2.setImageResource(R.drawable.avatar_6);//设置图片
                                    LinearLayout toastview = (LinearLayout) toast.getView();//定义toast的布局视图为线性布局
                                    toastview.addView(image2);//将图片加载到toast布局中
                                    toast.show();//显示toast
                                    startActivity(intent1);
                                    finish();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        };
                        thread.start();//打开线程
                    } else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();

                }
                break;





    }





//选中记住密码复选框，下一次启动，获取昵称和密码并显示出来
        String myname=getSharedPreferences("user_mes",0).getString("name","");
        String mypwd=getSharedPreferences("user_mes",0).getString("pwd","");
        Boolean myst=getSharedPreferences("user_mes",0).getBoolean("st",false);
       //判断过来的值
        if (myst==true) {
            et_name.setText(myname);
            ed_pwd.setText(mypwd);
            checkBox.setChecked(true);
        }else{
            ed_pwd.setText("");
            ed_pwd.setText("");
            checkBox.setChecked(false);
        }


        }
    }


