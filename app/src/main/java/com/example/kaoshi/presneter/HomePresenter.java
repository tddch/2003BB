package com.example.kaoshi.presneter;

import com.example.kaoshi.bean.HotBean;
import com.example.kaoshi.bean.TabBean;
import com.example.kaoshi.model.HomeModel;
import com.example.kaoshi.model.ICallBack;
import com.example.kaoshi.view.HomeView;

/**
 *
 */
public class HomePresenter implements ICallBack {
    private HomeView homeView;
    private final HomeModel model;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
        model = new HomeModel();
    }

    public void getData(){
        model.getDataHot(this);
    }

    @Override
    public void getData(HotBean hotBean) {
        homeView.getData(hotBean);
    }

    @Override
    public void getError(String error) {
        homeView.getError(error);
    }

    @Override
    public void getTab(TabBean tabBean) {
        homeView.getTab(tabBean);
    }

    @Override
    public void getTabError(String error) {
        homeView.getTabError(error);
    }
}

