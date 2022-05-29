package com.example.androidhomework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.androidhomework.adapter.MusicAdapter;
import java.util.ArrayList;
import java.util.List;

public class ListenActivity extends AppCompatActivity {
  //定义对象
    RecyclerView recyclerView;
    private static final String TAG = "ListenActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
        initView();//控件初始化
        initData();//数据初始化
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
                Toast.makeText(this, "您选择了返回上个界面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,FourthActivity.class);
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


    private void initView() {
        recyclerView=findViewById(R.id.recyclerview2);
    }

    private void initData() {
        List<Music> musicList=new ArrayList<>();
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);//查询音乐
        Log.d(TAG,"initData:查询取到的歌曲共："+cursor.getCount()+"首");
        while (cursor.moveToNext()){
            @SuppressLint("Range") String mymusictitle=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            @SuppressLint("Range") String mysingername=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
           Music music=new Music(mymusictitle,mysingername);
           musicList.add(music);
        }
        cursor.close();//关闭游标指针

        //定制每一行子布局
        //创建适配器
        MusicAdapter adapter=new MusicAdapter(musicList);//实例化，传递数据源
        //让数据显示在recyclerview控件上
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);//列数一列方向垂直
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}