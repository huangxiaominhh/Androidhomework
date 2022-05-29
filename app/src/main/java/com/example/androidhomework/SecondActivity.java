package com.example.androidhomework;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private EditText et_birthday, et_name, et_pwd;
    private RadioGroup rg_sex;
    private Button btn_submit, btn_login;
    private CheckBox cb_sing, cb_write, cb_play, other;
    private String name, pwd, sex, hobbys;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }

    private void init() {
        cb_sing = findViewById(R.id.cb_sing);
        cb_write = findViewById(R.id.cb_write);
        cb_play = findViewById(R.id.cb_play);

        other = findViewById(R.id.other);


        btn_login = findViewById(R.id.login);//登录按钮
        et_birthday = findViewById(R.id.birthday);//出生日期
        et_name = findViewById(R.id.et_name);
        et_pwd = findViewById(R.id.et_pwd);
        rg_sex = findViewById(R.id.rg);//性别单选框
        btn_submit = findViewById(R.id.submit);//提交按钮
        cb_write.setOnCheckedChangeListener(this);
        cb_sing.setOnCheckedChangeListener(this);
        other.setOnCheckedChangeListener(this);
        cb_play.setOnCheckedChangeListener(this);
        btn_submit.setOnClickListener(this);
        hobbys = new String();

        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.boy:
                        sex = "男";
                        break;
                    case R.id.girl:
                        sex = "女";
                        break;
                }

            }
        });


        //点击登录按钮跳转到登陆界面ThirdActivity
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);//显式Intent
                //创建一条居中带图片的toast并显示
                Toast toast = Toast.makeText(SecondActivity.this, "登录页面", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 2, 2);//让toast居中，左右偏移量为0
                ImageView image1 = new ImageView(SecondActivity.this);//定义图片控件
                image1.setImageResource(R.mipmap.a1);//设置图片
                LinearLayout toastview = (LinearLayout) toast.getView();//定义toast的布局视图为线性布局
                toastview.addView(image1);//将图片加载到toast布局中
                toast.show();//显示toast
            }

        });
        //点击出生日期编辑栏弹出日期表
        et_birthday.setOnTouchListener(new View.OnTouchListener()

        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch (View view, MotionEvent motionEvent){
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    showDatePickDlg();
                    return true;
                }
                return false;
            }
        });
        et_birthday.setOnFocusChangeListener(new View.OnFocusChangeListener()

        {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onFocusChange (View view,boolean hasFous){
                if (hasFous) {
                    showDatePickDlg();
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String motion = compoundButton.getText().toString();
        if (b) {
            if (!hobbys.contains(motion)) {
                hobbys = hobbys + motion;
            }
        } else {
            if (hobbys.contains(motion)) {
                hobbys = hobbys.replace(motion, "");
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit:
                getData();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, "请输入您的昵称", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, "请输入您的密码", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(sex)) {
                    Toast.makeText(this, "请输入您的性别", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                    Log.i("regsiter", "注册用户信息:" + "昵称: " + name + ",密码：" + pwd + ",性别：" + sex );
                    //点击注册按钮跳转到登录按钮
                            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                            startActivity(intent);//显式Intent
                            //创建一条居中带图片的toast并显示
                            Toast toast = Toast.makeText(SecondActivity.this, "登录页面", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 2, 2);//让toast居中，左右偏移量为0
                            ImageView image1 = new ImageView(SecondActivity.this);//定义图片控件
                            image1.setImageResource(R.mipmap.a1);//设置图片
                            LinearLayout toastview = (LinearLayout) toast.getView();//定义toast的布局视图为线性布局
                            toastview.addView(image1);//将图片加载到toast布局中
                            toast.show();//显示toast
                }
                break;
        }
    }

    private void getData() {
        name = et_name.getText().toString().trim();
        pwd = et_pwd.getText().toString().trim();
    }





    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void showDatePickDlg(){
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog =new DatePickerDialog(SecondActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int mothOfYear, int dayOfMonth) {
                    SecondActivity.this.et_birthday.setText(year + "年" + (mothOfYear+1) + "月" + dayOfMonth+"日");

                }
            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

             datePickerDialog.show();



    }


}
