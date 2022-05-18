package com.example.androidhomework;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText et;
    private RadioGroup rg_sex;
    private Button btn_submit,btn_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn_login = findViewById(R.id.login);//登录按钮
        et = (EditText) findViewById(R.id.birthday);//出生日期
        //点击登录按钮跳转到登陆界面ThirdActivity
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);//隐式Intent
                //创建一条居中带图片的toast并显示
                Toast toast=Toast.makeText(SecondActivity.this,"登录页面",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,2,2);//让toast居中，左右偏移量为0
                ImageView image1=new ImageView(SecondActivity.this);//定义图片控件
                image1.setImageResource(R.mipmap.a1);//设置图片
                LinearLayout toastview=(LinearLayout) toast.getView();//定义toast的布局视图为线性布局
                toastview.addView(image1);//将图片加载到toast布局中
                toast.show();//显示toast
            }
        });
        //点击出生日期编辑栏弹出日期表
        et.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    showDatePickDlg();
                    return true;
                }
                return false;
            }
        });
        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFous) {
                if (hasFous){
                    showDatePickDlg();
                }
            }
        });
            }
        protected void showDatePickDlg(){
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog =new DatePickerDialog(SecondActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int mothOfYear, int dayOfMonth) {
                    SecondActivity.this.et.setText(year + "年" + (mothOfYear+1) + "月" + dayOfMonth+"日");

                }
            },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

             datePickerDialog.show();



    }

}
