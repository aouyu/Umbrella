package com.aouyu.apps.weather.base;

/**
 * Created by huhuan on 2016/11/7.
 * Presenter基类
 */

public interface BasePresenter<T extends BaseView> {
    public void attachView(T View);
    public void detachView();
}
