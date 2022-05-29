package com.example.androidhomework.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhomework.Music;
import com.example.androidhomework.PlayerActivity;
import com.example.androidhomework.R;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter <MusicAdapter.ViewHolder> {
    Context mcontext;
   List<Music> myMusicList;
    public MusicAdapter(List<Music> musicList) {
        myMusicList=musicList;
    }
//方法1创建ViewHolder实例
    @NonNull
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item,parent,false);
       final ViewHolder holder=new ViewHolder(view);
        //点击跳转到播放界面事件
        holder.musicview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position= holder.getAbsoluteAdapterPosition();//获取点击了哪首歌
                Intent intent=new Intent(view.getContext(), PlayerActivity.class);
                intent.putExtra("myposition",position);//获取点击的位置位置
                view.getContext().startActivity(intent);//开启



            }
        });
        return holder;
    }
    //方法2对控件Recyclerview中子项的数据进行赋值的

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.ViewHolder holder, int position) {
      Music musicinfo=myMusicList.get(position);//看点了哪一首歌
      holder.music_title.setText(musicinfo.getMusicTitle());
      holder.singer_name.setText(musicinfo.getSingerName());//显示

    }
    //方法3返回Recycleview中一共有多少行数据

    @Override
    public int getItemCount() {
        return myMusicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        View musicview;
        TextView music_title;
        TextView singer_name;
        public ViewHolder(@NonNull View view) {
            super(view);
            musicview=view;//保存最外层的实例
            music_title=view.findViewById(R.id.music_title);
            singer_name=view.findViewById(R.id.singer_name);//绑定控件
        }
    }
}
