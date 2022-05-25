package com.example.androidhomework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.example.androidhomework.adapter.MusicAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListenActivity extends AppCompatActivity {
  //定义对象
    RecyclerView recyclerView;
    private static final String TAG = "Cannot invoke method length() on null object";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
        initView();//控件初始化
        initData();//数据初始化
    }


    private void initView() {
        recyclerView=findViewById(R.id.recyclerview2);
    }

    private void initData() {
        List<Music> arrlist=new ArrayList();
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);//查询音乐
        Log.d(TAG,"initData:查询取到的歌曲共："+cursor.getCount()+"首");
        while (cursor.moveToNext()){
           @SuppressLint("Range") String mymusictitle=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
           @SuppressLint("Range") String mysingername=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
           Music music=new Music(mymusictitle,mysingername);
           arrlist.add(music);
        }
        cursor.close();//关闭游标指针

        //定制每一行子布局
        //创建适配器
        MusicAdapter adapter=new MusicAdapter(arrlist);//实例化，传递数据源
        //让数据显示在recyclerview控件上
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);//列数一列方向垂直
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}