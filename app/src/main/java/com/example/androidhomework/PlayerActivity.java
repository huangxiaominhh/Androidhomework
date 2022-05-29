package com.example.androidhomework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerActivity extends AppCompatActivity {
//定义对象
private static final String TAG = "PlayerActivity";
    private TextView title;
    private TextView name;
    private Cursor cursor;//定义游标指针
    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private TextView start_time;
    private TextView total_time;
    private Handler mhandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initView();//初始化控件
        initData();//初始化数据
        initPlay();//播放歌曲
        initSeek();//初始化进度条
        moveSeek();//推动滑动条
        initUpdate();//实时更新滑动条的当前时间

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
                Intent intent = new Intent(this,ListenActivity.class);
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
        title=findViewById(R.id.title);
        name=findViewById(R.id.name);
        seekBar=findViewById(R.id.seek_bar);
        start_time=findViewById(R.id.start_time);
        total_time=findViewById(R.id.total_time);

    }

    private void initData() {
       int position = getIntent().getIntExtra("myposition",0);//获取歌曲位置
        Log.d(TAG,"initData:" +position);
        cursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null, MediaStore.Video.Media.DEFAULT_SORT_ORDER);//升序排序
         cursor.moveToPosition(position);//将游标指针移动到获取歌曲的位置
        @SuppressLint("Range") String mytitle=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));//获取歌曲名字
        @SuppressLint("Range") String myname=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));//获取歌手名字
          title.setText(mytitle);
          name.setText(myname);
    }
    private void initPlay(){
        mediaPlayer=new MediaPlayer();
        @SuppressLint("Range") String path=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
        mediaPlayer.reset();//清空里面的其他歌曲
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();//准备就绪
            mediaPlayer.start();//开始唱歌
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void initSeek(){
        seekBar.setMax(mediaPlayer.getDuration());//获取音频文件总时长
        seekBar.setProgress(mediaPlayer.getCurrentPosition());//获取当前播放的进度条值
        start_time.setText(toTime(mediaPlayer.getCurrentPosition()));
        total_time.setText(toTime(mediaPlayer.getDuration()));
    }

    private String toTime(int getDutation) {
        int time=getDutation/1000; //毫秒转化为秒
        int minute=time/60; //取整：求出分钟
        int second=time%60; //取余：求出秒 90秒/60=1（分钟）。。。。30（秒）
        String mm=String.format("%01d:%02d",minute,second);//指定显示的格式
        return mm;
    }
    private void moveSeek(){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b){
                    mediaPlayer.seekTo(i);//音频从你拖到的位置处开始播放
                    initSeek();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private void initUpdate(){
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                Message msg=new Message();
                msg.what=11;
                mhandler.sendMessage(msg);
            }
        };
        timer.schedule(timerTask,0,1000);
        mhandler=new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg){
                super.handleMessage(msg);
                switch (msg.what){
                    case 11:
                        initSeek();//重新执行进度条的初始化代代码
                        break;
                    default:
                        break;
                }
            }
        };
    }
    //按返回键停止播放
    @Override
    protected  void onDestroy(){
        super.onDestroy();
        mediaPlayer.stop();
    }
}