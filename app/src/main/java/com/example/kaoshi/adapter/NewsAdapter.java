package com.example.kaoshi.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kaoshi.R;
import com.example.kaoshi.ViewActivity;
import com.example.kaoshi.bean.NewsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<NewsBean.DataBean.ListBean> list;
    private Context context;
    private final LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    public NewsAdapter(ArrayList<NewsBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public interface OnItemClickListener{
        void getOnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = inflater.inflate(R.layout.item_news_one, parent, false);
            return new ViewholderOne(view);
        } else if (viewType == 2) {
            View view = inflater.inflate(R.layout.item_news_two, parent, false);
            return new ViewholderTwo(view);
        } else {
            View view = inflater.inflate(R.layout.item_news_three, parent, false);
            return new ViewholderThree(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsBean.DataBean.ListBean listBean = list.get(position);
        if(holder instanceof ViewholderOne){
            Glide.with(context).load(listBean.getFilePathList().get(0).getFilePath()).into(((ViewholderOne) holder).ivItemOne);
            Glide.with(context).load(listBean.getFilePathList().get(1).getFilePath()).into(((ViewholderOne) holder).ivItemTwo);
            Glide.with(context).load(listBean.getFilePathList().get(2).getFilePath()).into(((ViewholderOne) holder).ivItemThree);
            ArrayList<NewsBean.DataBean.ListBean.FilePathListBean> filePathList = (ArrayList<NewsBean.DataBean.ListBean.FilePathListBean>) listBean.getFilePathList();
            ((ViewholderOne) holder).ivItemOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ViewActivity.class);
                    intent.putExtra("homeList",filePathList);
                    context.startActivity(intent);
                }
            });
            ((ViewholderOne) holder).ivItemTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ViewActivity.class);
                    intent.putExtra("homeList",filePathList);
                    context.startActivity(intent);
                }
            });
            ((ViewholderOne) holder).ivItemThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ViewActivity.class);
                    intent.putExtra("homeList",filePathList);
                    context.startActivity(intent);
                }
            });
        }else if(holder instanceof ViewholderTwo){
            Glide.with(context).load(listBean.getFilePathList().get(0).getFilePath()).into(((ViewholderTwo) holder).ivItemOne);
            ((ViewholderTwo) holder).tvTitle.setText(listBean.getTitle());
            ArrayList<NewsBean.DataBean.ListBean.FilePathListBean> filePathList = (ArrayList<NewsBean.DataBean.ListBean.FilePathListBean>) listBean.getFilePathList();
            ((ViewholderTwo) holder).ivItemOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ViewActivity.class);
                    intent.putExtra("homeList",filePathList);
                    context.startActivity(intent);
                }
            });
        }else if(holder instanceof ViewholderThree){
            ((ViewholderThree) holder).tvItem.setText(listBean.getTitle());
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return 1;
        } else if (position % 3 == 1) {
            return 2;
        } else {
            return 3;
        }
    }

    static
    class ViewholderOne extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_one)
        ImageView ivItemOne;
        @BindView(R.id.iv_item_two)
        ImageView ivItemTwo;
        @BindView(R.id.iv_item_three)
        ImageView ivItemThree;

        ViewholderOne(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static
    class ViewholderTwo extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.iv_item_one)
        ImageView ivItemOne;

        ViewholderTwo(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static
    class ViewholderThree extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;

        ViewholderThree(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
