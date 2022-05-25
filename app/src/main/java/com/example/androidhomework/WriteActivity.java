package com.example.androidhomework;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.androidhomework.Fragment.a1Fragment;
import com.example.androidhomework.Fragment.a2Fragment;
import com.example.androidhomework.Fragment.a3Fragment;
import com.example.androidhomework.Fragment.a4Fragment;
import com.example.androidhomework.adapter.MemoAdapter;
import com.example.androidhomework.bean.MemoBean;
import com.example.androidhomework.db.MyDbHelper;

import java.util.ArrayList;
import java.util.List;

public class WriteActivity extends AppCompatActivity {
    //定义对象
    ImageView image1,image2,image3,image4;
    Button btn_add;
    RecyclerView recyclerView1;
    MyDbHelper mhelper;
    SQLiteDatabase db;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        //1绑定控件
        initView();
        //2 对单击添加单击事件
        btnOnclicknext();
        //3完善从数据库获取数据，显示到RecyclerView,WriteActivity控件里
        recyDisplay();
        image1=findViewById(R.id.a1);
        image2=findViewById(R.id.a2);
        image3=findViewById(R.id.a3);
        image4=findViewById(R.id.a4);
        image1.setOnClickListener(listener);
        image2.setOnClickListener(listener);
        image3.setOnClickListener(listener);
        image4.setOnClickListener(listener);

}



    //1 绑定控件-----代码
    private void initView() {
        btn_add=findViewById(R.id.btn_add);
        recyclerView1=findViewById(R.id.recyclerview1);
        mhelper=new MyDbHelper(WriteActivity.this);//实例化
        db=mhelper.getWritableDatabase();

    }
    //2对单击添加单击事件---代码
    private void btnOnclicknext() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //单击后跳转到下一页
                Intent intent=new Intent(WriteActivity.this,AddwriteActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
    //3完善从数据库获取数据，显示到RecyclerView,WriteActivity控件里
    @SuppressLint("Range")
    private void recyDisplay() {
        //1准备数据--把标题内容写在类里面
        List<MemoBean> arr=new ArrayList();//动态数组
        //从数据库取数据
        Cursor cursor =db.rawQuery("select * from memory",null);
         //循环语句，多次循环
        while (cursor.moveToNext()){
            String mytitle = cursor.getString(cursor.getColumnIndex("title"));
           String mycontent = cursor.getString(cursor.getColumnIndex("content"));
            MemoBean memoBean=new MemoBean(mytitle,mycontent);//打包成一个类
            arr.add(memoBean);//放到数组中
        }
        cursor.close();//结果集关闭

        //2子布局 recy_item
        //3数据---桥（适配器MemoAapter）--子布局
        MemoAdapter adapter=new MemoAdapter(WriteActivity.this,arr);
        //4确定显示的方式
        StaggeredGridLayoutManager st=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);//一列，垂直
        recyclerView1.setLayoutManager(st);
        //5让数据显示出来
        recyclerView1.setAdapter(adapter);

    }

}

