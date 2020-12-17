package com.example.kaoshi.model;

import com.example.kaoshi.api.ApiService;
import com.example.kaoshi.base.HttpMessenger;
import com.example.kaoshi.bean.HotBean;
import com.example.kaoshi.bean.TabBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 *
 */
public class HomeModel {
    public void getDataHot(final ICallBack callBack ){
        ApiService api = HttpMessenger.getInstance().getApi();
        api. getHot()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                        callBack.getData(hotBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        api.getTab()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<TabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TabBean tabBean) {
                        callBack.getTab(tabBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getTabError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
