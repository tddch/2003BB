package com.example.kaoshi.base;

import com.example.kaoshi.api.ApiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class HttpMessenger {
    private static HttpMessenger instance;

    public static HttpMessenger getInstance() {
        if (instance == null) {
            synchronized (HttpMessenger.class) {
                if (instance == null) {
                    instance = new HttpMessenger();
                }
            }
        }
        return instance;
    }

    public ApiService apiService;

    public ApiService getApi() {
        if (apiService == null) {
            apiService = getRetrofit().create(ApiService.class);
        }
        return apiService;
    }

    private Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .client(getOkHttpClink())
                .baseUrl(ApiService.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public OkHttpClient getOkHttpClink() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }


}
