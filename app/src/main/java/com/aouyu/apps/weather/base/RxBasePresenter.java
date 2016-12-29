package com.aouyu.apps.weather.base;

import com.aouyu.apps.weather.utils.RxManage;

/**
 * 基于Rx的Presenter封装,控制订阅的生命周期
 */

public class RxBasePresenter<T extends BaseView> implements BasePresenter<T> {

    public T mView;
    protected RxManage mRxManage = new RxManage();

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        mRxManage.clear();
    }
}
