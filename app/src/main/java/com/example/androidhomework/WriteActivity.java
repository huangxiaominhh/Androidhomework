package com.example.androidhomework;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidhomework.Fragment.a1Fragment;
import com.example.androidhomework.Fragment.a2Fragment;
import com.example.androidhomework.Fragment.a3Fragment;
import com.example.androidhomework.Fragment.a4Fragment;

public class WriteActivity extends AppCompatActivity {
    ImageView image1,image2,image3,image4;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        image1=findViewById(R.id.a1);
        image2=findViewById(R.id.a2);
        image3=findViewById(R.id.a3);
        image4=findViewById(R.id.a4);
        image1.setOnClickListener(listener);
        image2.setOnClickListener(listener);
        image3.setOnClickListener(listener);
        image4.setOnClickListener(listener);

}
     View.OnClickListener listener=new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             FragmentManager fm = getSupportFragmentManager();//获取Fragment
             FragmentTransaction ft=fm.beginTransaction();//开启一个事务
             Fragment fragment=null;//为Fragment初始化
             switch (view.getId()){//获取点击的id判断点击了哪张图片
                 case R.id.a1:
                     fragment=new a1Fragment();
                     break;
                 case R.id.a2:
                     fragment=new a2Fragment();
                     break;
                     case R.id.a3:
                     fragment=new a3Fragment();
                     break;
                 case R.id.a4:
                     fragment=new a4Fragment();
                     break;
                 default:
                     break;
             }
             ft.replace(R.id.fragment1,fragment);
             ft.commit();

         }

     };

}

