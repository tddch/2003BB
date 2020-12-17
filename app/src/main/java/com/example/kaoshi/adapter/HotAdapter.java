package com.example.kaoshi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kaoshi.R;
import com.example.kaoshi.bean.HotBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {
    private ArrayList<HotBean.DataBean> hotList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public HotAdapter(ArrayList<HotBean.DataBean> hotList, Context context) {
        this.hotList = hotList;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void getOnItemClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HotBean.DataBean dataBean = hotList.get(position);
        holder.tvCity.setText(dataBean.getLocation());
        holder.tvTitle.setText(dataBean.getTitle());
        Glide.with(context).load(dataBean.getCover()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.getOnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotList.size();
    }

    static
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_city)
        TextView tvCity;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
