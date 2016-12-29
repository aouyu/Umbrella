package com.aouyu.apps.weather.base;

import android.content.Context;

/**
 * @author fengkehua.
 * @date 16/11/8.
 */
public interface BaseContract {

    /**
     * 代理(p)层基础接口
     */
//    interface BasePresenter<T> {
//
//        void attachView(T view);
//
//        void detachView();
//    }

    /**
     * UI基础交互处理接口
     */
    interface BaseView {

        Context getCurContext();//获取上下文对象,用于保存数据等

        void showProgress();//可以显示进度条

        void hideProgress();//可以隐藏进度条

        void showInfo(String info);//提示用户,提升友好交互

        void showErrorMsg(String msg);//发生错误就显示错误信息
    }


}
