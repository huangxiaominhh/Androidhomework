package com.example.androidhomework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SixthActivity extends AppCompatActivity {
    private MyBroadcastReceiverOne one;
    private MyBroadcastReceiverTwo two;
    private MyBroadcastReceiverThree three;
    private ImageView iv_sheep;
    private TextView content, tv_one, tv_two, tv_three;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
        registerReceiver();
        init();
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



    private void init() {
        iv_sheep = findViewById(R.id.iv_sheep);
        content = findViewById(R.id.content);
        tv_one = findViewById(R.id.tv_one);
        tv_two = findViewById(R.id.tv_two);
        tv_three = findViewById(R.id.tv_three);
        iv_sheep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setVisibility(View.VISIBLE);
                iv_sheep.setClickable(false);
                Intent intent = new Intent();
                intent.setAction("SHEEPS");
                sendOrderedBroadcast(intent, null);
            }
        });

        tv_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(SixthActivity.this,sheep1Activity.class);
                startActivity(intent);
                finish();
            }
        });
        tv_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(SixthActivity.this,sheep2Activity.class);
                startActivity(intent);
                finish();
            }
        });
        tv_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(SixthActivity.this,sheep3Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void registerReceiver() {
        one = new MyBroadcastReceiverOne();
        IntentFilter filter1 = new IntentFilter();
        filter1.setPriority(800);
        filter1.addAction("SHEEPS");
        registerReceiver(one, filter1);

        two = new MyBroadcastReceiverTwo();
        IntentFilter filter2 = new IntentFilter();

        filter2.setPriority(1000);
        filter2.addAction("SHEEPS");
        registerReceiver(two, filter2);

        three = new MyBroadcastReceiverThree();
        IntentFilter filter3 = new IntentFilter();
        filter3.setPriority(6000);
        filter3.addAction("SHEEPS");
        registerReceiver(three, filter3);
    }

    private int num = 0;

    private class MyBroadcastReceiverOne extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv_one.setVisibility(View.VISIBLE);
            num=num+1;
            tv_one.setText(num + "");
            Log.i("BroadcastReceiverOne", "广播接收者One，接收到了广播消息");
            delay();
        }
    }

    private class MyBroadcastReceiverThree extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv_three.setVisibility(View.VISIBLE);
            num=num+1;
            tv_three.setText(num + "");
            Log.i("BroadcastReceiverThree", "广播接收者Two，接收到了广播消息");
            delay();
        }
    }

    private class MyBroadcastReceiverTwo extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            tv_two.setVisibility(View.VISIBLE);
            num=num+1;
            tv_two.setText(num + "");
            Log.i("BroadcastReceiverThree", "广播接收者Three，接收到了广播消息");
            delay();
        }
    }
    private void delay(){
        try {
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(one);
        unregisterReceiver(two);
        unregisterReceiver(three);
    }

}

