package com.example.kaoshi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.kaoshi.R;
import com.example.kaoshi.bean.NewsBean;

import java.util.ArrayList;

/**
 *
 */
public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<NewsBean.DataBean.ListBean.FilePathListBean> list;
    private Context context;

    public ViewPagerAdapter(ArrayList<NewsBean.DataBean.ListBean.FilePathListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, null);
        ImageView iv_item = view.findViewById(R.id.iv_item);
        TextView tv_item = view.findViewById(R.id.tv_page);

        tv_item.setText(list.size()+"/"+(position+1));
        Glide.with(context).load(list.get(position).getFilePath()).into(iv_item);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);

    }

}
