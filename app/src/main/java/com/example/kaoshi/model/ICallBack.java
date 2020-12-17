package com.example.kaoshi.model;

import com.example.kaoshi.bean.HotBean;
import com.example.kaoshi.bean.TabBean;

public interface ICallBack {
    void getData(HotBean hotBean);
    void getError(String error);

    void getTab(TabBean tabBean);
    void getTabError(String error);
}
