package com.example.androidhomework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LookActivity extends AppCompatActivity {
    private ListView mListView;
    private String[] titles={"毛泽东","高尔基","列宁","莎士比亚","贝多芬","爱因斯坦","居里夫人","伽利略","张衡"};
    private String[] contents={"世上无难事，只要肯攀登。","如果不想在世界上虚度一生，那就要学习一辈子。","只要愿意学习，就一定能够学会。","生活里没有书籍，就好像没有阳光;智慧里没有书籍，就好像鸟儿没有翅膀","卓越人的一大优点是在不利与艰难的遭遇里百折不挠。","我们没有什么才能，不过喜欢寻根究底地追求问题罢了。","我们应该有恒心，尤其要有自信力","追求科学，需要特殊的勇敢。","人生在勤，不索何获。"};
    private int[] icons={R.drawable.mao1,R.drawable.gao2,R.drawable.lie3,R.drawable.sa4,R.drawable.bei5,R.drawable.ai6,R.drawable.jv7,R.drawable.jia8,R.drawable.zhang9};
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look);
        mListView=findViewById(R.id.lv);
        MyBaseAdapter myadapter=new MyBaseAdapter();
        mListView.setAdapter(myadapter);
    }
    private class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int i) {
            return titles[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder = null;
            if (view == null) {
                view = View.inflate(LookActivity.this, R.layout.list_item, null);
                holder = new ViewHolder();
                holder.title = view.findViewById(R.id.title);
                holder.content = view.findViewById(R.id.content);
                holder.im = view.findViewById(R.id.im);
                view.setTag(holder);

            } else {
                holder = (ViewHolder) view.getTag();
            }
            holder.title.setText(titles[i]);
            holder.content.setText(contents[i]);
            holder.im.setBackgroundResource(icons[i]);
            return view;
        }

        class ViewHolder {
            TextView title, content;
            ImageView im;
        }
    }


}
