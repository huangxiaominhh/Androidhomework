package com.example.androidhomework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhomework.Music;
import com.example.androidhomework.R;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter <MusicAdapter.ViewHolder> {
    Context mcontext;
   List<Music> mymusiclist;
    public MusicAdapter(List<Music> arrlist) {
        mymusiclist=arrlist;
    }
//方法1创建ViewHolder实例
    @NonNull
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item,parent,false);
       final ViewHolder holder=new ViewHolder(view);

        return holder;
    }
    //方法2对控件Recyclerview中子项的数据进行赋值的

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.ViewHolder holder, int position) {
      Music music=mymusiclist.get(position);//看点了哪一首歌
      holder.music_title.setText(music.getMusicTitle());
      holder.singer_name.setText(music.getSingerName());//显示

    }
    //方法3返回Recycleview中一共有多少行数据

    @Override
    public int getItemCount() {
        return mymusiclist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView music_title;
        TextView singer_name;
        public ViewHolder(@NonNull View view) {
            super(view);
            music_title=view.findViewById(R.id.music_title);
            singer_name=view.findViewById(R.id.singer_name);//绑定控件
        }
    }
}
