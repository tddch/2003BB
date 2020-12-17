package com.example.kaoshi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.kaoshi.adapter.ViewPagerAdapter;
import com.example.kaoshi.bean.NewsBean;

import java.io.Serializable;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ArrayList<NewsBean.DataBean.ListBean.FilePathListBean> homeList = (ArrayList<NewsBean.DataBean.ListBean.FilePathListBean>) getIntent().getSerializableExtra("homeList");
        ViewPagerAdapter adapter = new ViewPagerAdapter(homeList, this);
        vp.setAdapter(adapter);

    }
}