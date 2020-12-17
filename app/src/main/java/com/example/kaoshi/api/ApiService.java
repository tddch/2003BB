package com.example.kaoshi.api;

import com.example.kaoshi.bean.HotBean;
import com.example.kaoshi.bean.NewsBean;
import com.example.kaoshi.bean.TabBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 *
 */
public interface ApiService {
    String BASE_URL="http://cdwan.cn:7000/tongpao/discover/";

    @GET("hot_activity.json")
    Observable<HotBean> getHot();

    @GET("navigation.json")
    Observable<TabBean> getTab();

    @GET("news_{type}.json")
    Observable<NewsBean> getNews(@Path("type") int type);
}
