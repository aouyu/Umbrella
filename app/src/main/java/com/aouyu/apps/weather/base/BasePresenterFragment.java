package com.aouyu.apps.weather.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aouyu.apps.weather.utils.GenericUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by huhuan on 2016/11/16.
 */

public abstract class BasePresenterFragment<T extends BasePresenter> extends Fragment implements BaseView{
    public abstract int getContentViewId();

    protected Context context;
    protected View mRootView;

    // 应用是否销毁标志
    protected boolean isDestroy;
    private Unbinder unbinder;
    public T mPresenter;
    public Resources mResources;

    @Nullable
    @Override
    /**
     * 绑定butterknife找到的视图并创建
     */
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        unbinder = ButterKnife.bind(this, mRootView);

        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = GenericUtil.getObj(this, 0);
        if (mPresenter != null) {
            mPresenter.attachView(this);;
        }
        mResources = context.getApplicationContext().getResources();
        this.context = context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAllMembersView(savedInstanceState);
        initData();
        loadData();
    }

    /**
     * 处理找到找到视图并创建后的操作
     *
     * @param savedInstanceState
     */
    protected abstract void initAllMembersView(Bundle savedInstanceState);

    protected abstract void initData();

    protected  void loadData(){

    }

    /**
     * 提示信息
     *
     * @param message
     */
    protected void show(String message) {
        show(message);
    }

    /**
     * 销毁时解除绑定
     */
    @Override
    public void onDestroyView() {
        mPresenter.detachView();//取消订阅
        isDestroy = true;
        unbinder.unbind();// 解绑
        super.onDestroyView();
    }

}
