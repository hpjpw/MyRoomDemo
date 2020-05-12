package com.hpjpw.myroomdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BabyCapRecyclerViewAdapter extends RecyclerView.Adapter<BabyCapRecyclerViewAdapter.ViewHolder> {
    private List<BabyCap> babyCapList;
    private BabyCapViewModel babyCapViewModel;
    private LayoutInflater mInflater;

    public BabyCapRecyclerViewAdapter(BabyCapViewModel babyCapViewModel, Context context) {
        this.babyCapViewModel = babyCapViewModel;
        this.mInflater = LayoutInflater.from(context);
    }

    public void setBabyCapList(List<BabyCap> babyCapList) {
        this.babyCapList = babyCapList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item,
                parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfo(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final BabyCap babyCap = babyCapList.get(position);
        RecyclerView.LayoutParams param = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if (babyCap.isVisibility()) {
            param.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            param.width = LinearLayout.LayoutParams.MATCH_PARENT;
            param.bottomMargin = 35;
            holder.itemView.setVisibility(View.VISIBLE);
        } else {
            holder.itemView.setVisibility(View.GONE);
            param.height = 0;
            param.bottomMargin = 0;
            param.width = 0;
        }
        holder.itemView.setLayoutParams(param);
        holder.news_title.setText(babyCap.getNews_title());
        holder.news_info.setText(babyCap.getNews_info());
        holder.news_thumb.setImageResource(babyCap.getNews_thumb_id());
        holder.news_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdd(holder.getAdapterPosition());
            }
        });
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                babyCapViewModel.deleteBabyCaps(babyCap);
            }
        });
        holder.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                babyCap.setNews_info(babyCap.getNews_info()+"update!");
                babyCapViewModel.updateBabyCaps(babyCap);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(babyCapList != null)
            return babyCapList.size();
        return 0;
    }

    public void showInfo(int position) {
        new AlertDialog.Builder(mInflater.getContext())
                .setTitle(babyCapList.get(position).getNews_title())
                .setMessage(babyCapList.get(position).getNews_info())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }

    public void showAdd(int position) {
        new AlertDialog.Builder(mInflater.getContext())
                .setTitle("提示")
                .setMessage("成功添加" + babyCapList.get(position).getNews_title())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView news_thumb;
        public TextView news_title;
        TextView news_info;
        ImageButton news_btn;
        Button buttonDelete;
        Button buttonUpdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news_title = itemView.findViewById(R.id.news_title);
            news_info = itemView.findViewById(R.id.news_info);
            news_thumb = itemView.findViewById(R.id.news_thumb);
            news_btn = itemView.findViewById(R.id.news_btn);
            buttonDelete=itemView.findViewById(R.id.buttonDelete);
            buttonUpdate=itemView.findViewById(R.id.buttonUpdate);
        }
    }
}
