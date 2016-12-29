package com.aouyu.apps.weather.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.aouyu.apps.weather.utils.GenericUtil;
import com.aouyu.apps.weather.utils.ToastUtil;

import butterknife.ButterKnife;

/**
 * Created by huhuan on 2016/11/10.
 * 将Presenter封装进baseActivity
 */

public abstract class BasePresenterActivity<T extends BasePresenter> extends FragmentActivity implements BaseView {

    public Context mContext = null;


    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView();
        mPresenter = GenericUtil.getObj(this, 0);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        ButterKnife.bind(this);
        initData();
        loadData();

    }

    protected abstract void setContentView();

    protected abstract void initData();

    protected void loadData() {

    }

    public void show(String msgToast){
        ToastUtil.show(msgToast);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }
}
