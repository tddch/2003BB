package com.example.kaoshi.view;

import com.example.kaoshi.bean.HotBean;
import com.example.kaoshi.bean.TabBean;

public interface HomeView {
    void getData(HotBean hotBean);
    void getError(String error);

    void getTab(TabBean tabBean);
    void getTabError(String error);
}
