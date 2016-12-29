package com.aouyu.apps.weather.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;

/**
 * 简单类型BaseActivity
 * (1.自带公共返回事件处理的简洁基类，不适用于有回传值数据的界面)
 * Created by fangxiaotian on 2016/11/7.
 */

public abstract class BaseSimpleActivity extends FragmentActivity {

    public Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView();

        ButterKnife.bind(this);
        initData();
        loadData();
        showContent();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected  void showContent(){

    }

    protected abstract void setContentView();

    protected abstract void initData();

    protected  void loadData(){

    }
}
