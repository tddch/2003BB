package com.example.kaoshi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.kaoshi.adapter.HotAdapter;
import com.example.kaoshi.bean.HotBean;
import com.example.kaoshi.bean.TabBean;
import com.example.kaoshi.fragment.NewsFragment;
import com.example.kaoshi.presneter.HomePresenter;
import com.example.kaoshi.view.HomeView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeView {

    private static final String TAG = MainActivity.class.getCanonicalName();
    @BindView(R.id.rv_hot)
    RecyclerView rvHot;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp_news)
    ViewPager vpNews;
    private HomePresenter presenter;
    private ArrayList<HotBean.DataBean> hotList;
    private HotAdapter adapter;
    private ArrayList<TabBean.DataBean> dataBeans;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new HomePresenter(this);
        initView();
        initData();
    }

    private void initData() {
        presenter.getData();
    }

    private void initView() {
        rvHot.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,true));
        hotList = new ArrayList<>();

        adapter = new HotAdapter(hotList, this);
        rvHot.setAdapter(adapter);

        adapter.setOnItemClickListener(new HotAdapter.OnItemClickListener() {
            @Override
            public void getOnItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                intent.putExtra("homeList",hotList.get(position).getLocation());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getData(HotBean hotBean) {
        Log.e(TAG, "getData: "+hotBean.toString() );
        if(hotBean.getData()!=null){
            hotList.addAll(hotBean.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getError(String error) {
        Log.e(TAG, "getError: "+error );
    }

    @Override
    public void getTab(TabBean tabBean) {
        //tablayout+fragment复用
        dataBeans = new ArrayList<>();
        fragments = new ArrayList<>();
        if(tabBean.getData()!=null){
            dataBeans.addAll(tabBean.getData());

            for (int i = 0; i < dataBeans.size()-1; i++) {
                fragments.add(new NewsFragment(dataBeans.get(i).getType()));
            }

            FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
            vpNews.setAdapter(fragmentAdapter);
            tab.setupWithViewPager(vpNews);


        }
        Log.e(TAG, "getTab: "+tabBean.toString() );
    }
    private class FragmentAdapter extends FragmentPagerAdapter{

        public FragmentAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return dataBeans.get(position).getName();
        }
    }



    @Override
    public void getTabError(String error) {
        Log.e(TAG, "getTabError: "+error );

    }
}