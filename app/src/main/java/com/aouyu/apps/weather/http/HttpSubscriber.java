package com.aouyu.apps.weather.http;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;

import com.aouyu.apps.weather.base.RxBasePresenter;
import com.aouyu.apps.weather.http.bean.Result;
import com.aouyu.apps.weather.utils.JsonUtil;
import com.aouyu.apps.weather.utils.NetUtils;
import com.aouyu.apps.weather.utils.ToastUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 封装的Subscriber供网络请求实现订阅
 */

public abstract class HttpSubscriber<T> extends Subscriber<String> {

    public Type clazz;
    private Context mContext;

    private int noShowToast = 0;     //不显示Toast

    /**
     * 需要加载的dialog时调用
     */
    @SuppressWarnings("unused")
    public HttpSubscriber(RxBasePresenter presenter, Type clazz) {
        this(presenter, true);
        this.clazz = clazz;
    }

    /**
     * 不显示Toast,用于一些不需要提示的接口，比如启动页获取手势密码
     *
     * @param presenter
     * @param clazz
     * @param noShowToast
     */
    public HttpSubscriber(RxBasePresenter presenter, Type clazz, int noShowToast) {
        this(presenter, true);
        this.clazz = clazz;
        this.noShowToast = noShowToast;
    }

    /**
     * 不要等待动画
     *
     * @param presenter
     * @param clazz
     * @param show
     */
    public HttpSubscriber(RxBasePresenter presenter, Type clazz, boolean show) {
        this(presenter, show);
        this.clazz = clazz;
    }


    public HttpSubscriber(Type clazz) {
        this.clazz = clazz;
    }

    /**
     * 需要加载的dialog时调用
     */
    @SuppressWarnings("unused")
    public HttpSubscriber(RxBasePresenter presenter, boolean show) {
        super();
        if (presenter.mView instanceof Fragment) {
            mContext = ((Fragment) (presenter.mView)).getActivity();
        } else if (presenter.mView instanceof Activity) {
            mContext = (Context) presenter.mView;
        }
        /*if (!NetUtils.isNetConnected()) {
            ToastUtil.show("网络连接失败，请检查您的网络！");
            return;
        }*/
    }


    /**
     * 网络访问后台成功，后台返回的数据封装httpResult
     */
    @Override
    public void onNext(String httpResult) {
        if (!TextUtils.isEmpty(httpResult)) {
            Log.d("FXT", httpResult);
            Result<T> mResult = JsonUtil.fromJson(httpResult, clazz);
            if (mResult != null) {
                success(mResult.HeWeather5.get(0));
            }
        } else {
            fail();
        }
    }

    /**
     * 网络访问后台异常
     */
    @Override
    public void onError(Throwable e) {
        try {
            Log.e("H2", e.getMessage());
            if (!NetUtils.isNetConnected()) {
                return;
            }
            handleError(e);
        } catch (Exception ex) {
            ToastUtil.show("连接错误");
        }

    }


    private void handleError(Throwable throwable) {
        try {
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                int statusCode = httpException.code();
                // handle different HTTP error codes here (4xx)
            } else if (throwable instanceof SocketTimeoutException) {
                // handle timeout from Retrofit
                ToastUtil.show("连接超时");
                fail();
            } else if (throwable instanceof IOException) {
                fail();
            } else {
                fail();
            }
        } catch (Exception ex) {
            ToastUtil.show("连接错误");
        }
    }

    /**
     * 网络访问结束
     */
    @Override
    public void onCompleted() {
        Log.i("网络访问结束", "onCompleted");
    }

    /**
     * 前台操作成功时的回调
     */
    public abstract void success(T t);

    /**
     * 前台操作失败时的回调
     */
    public void fail() {

    }

    public void tokenDisable() {

    }

    public void bfail(Result<T> mResult) {

    }

    /**
     * 前台操作成功时的回调
     */
    public void success(Result<T> mResult, T t) {

    }

    /**
     * 前台操作成功时的回调
     */
    public void success(Result<T> mResult) {

    }

    /**
     * resultCode等于1时，后台返回的提示（比如评估车辆结果价格为0，resultCode为1，sContent为“暂无报价
     */
    public void success(T t, String sContent) {

    }


    /**
     * 含分页的接口成功的回调
     */
    public void successWithPage(T t, int pageCount, int count) {

    }


}