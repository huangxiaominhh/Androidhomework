package com.example.androidhomework.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidhomework.R;
import com.example.androidhomework.bean.MemoBean;
import com.example.androidhomework.db.MyDbHelper;

import java.util.List;
import java.util.Random;

public class MemoAdapter extends RecyclerView.Adapter <MemoAdapter.ViewHolder> {
    private Context mcontext;
    private List<MemoBean> arr1;
    private MyDbHelper mhelper1;
    private SQLiteDatabase db;
    private ViewHolder mholder;
    private int i;

    //构造方法
    public MemoAdapter(Context mcontext, List<MemoBean> arr1) {
        this.mcontext = mcontext;
        this.arr1 = arr1;
    }

    //负责加载item布局
    @NonNull
    @Override
    public MemoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.recy_item, parent, false);//布局渲染器
        ViewHolder mholder = new ViewHolder(view);//把布局放到view
        return mholder;
    }

    //负责加载item数据
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MemoAdapter.ViewHolder mholder,int i) {
        MemoBean memoBean = arr1.get(i);
        mholder.item_title.setText(memoBean.getTitle());
        mholder.item_content.setText(memoBean.getContent());
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);//形状
        gradientDrawable.setCornerRadius(10f);//设置圆角Radius

        mholder.item_layout.setBackground(gradientDrawable);//设置为backgrount
       //点击其中一个子项（记录），弹出删除功能
        mholder.item_layout.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick (View view){
            //弹出对话框，删除
            AlertDialog.Builder dialog=new AlertDialog.Builder(mcontext);
            dialog.setMessage("确定它删除吗？");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int ab) {

                    //从数据库当中删除掉
                    mhelper1=new MyDbHelper(mcontext);//数据库实例化
                    db=mhelper1.getWritableDatabase();//获取数据库读写权限
                    db.delete("memory","title=?",new String[]{arr1.get(i).getTitle()});
                    arr1.remove(i);//把数组移除掉
                    notifyItemRemoved(i);
                    dialogInterface.dismiss();//刷新消失



                }
            });
            dialog.setNegativeButton("取消",null);
            dialog.setCancelable(false);
            dialog.create();
            dialog.show();

        }

    });
    }




  //recyView一共有多少个子项
    @Override
    public int getItemCount() {

        return arr1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_title,item_content;
        LinearLayout item_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_title=itemView.findViewById(R.id.item_title);
            item_content=itemView.findViewById(R.id.item_content);
            item_layout=itemView.findViewById(R.id.item_layout);


        }
    }
}
