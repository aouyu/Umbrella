package com.aouyu.apps.weather.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aouyu.apps.weather.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 基于Butterknife注解的Fragment
 *
 * Created by fangxiaotian on 2016/11/3.
 */

public abstract class BaseFragment extends Fragment {
    public abstract int getContentViewId();

    protected Context context;
    protected View mRootView;
    protected int mScreenWidth;
    protected int mScreenHeight;

    // 应用是否销毁标志
    protected boolean isDestroy;
    private Unbinder unbinder;

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
        ToastUtil.show(context, message);
    }

    /**
     * 销毁时解除绑定
     */
    @Override
    public void onDestroyView() {
        isDestroy = true;
        unbinder.unbind();// 解绑
        super.onDestroyView();
    }
}
