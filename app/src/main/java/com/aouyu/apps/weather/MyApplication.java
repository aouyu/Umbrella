package com.aouyu.apps.weather;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.aouyu.apps.weather.bean.CityItemBean;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * @author fengkehua.
 * @date 16/11/3.
 */
public class MyApplication extends Application {
    /** 保存所有已创建的Activity */
    private static Stack<Activity> activityStack;
    // singleton
    private static MyApplication context = null;

    //App的上下文
    public static Context mContext = null;

    // 城市列表信息
    public static List<CityItemBean> cityItemBeanList = new ArrayList<>();
    // 点击城市列表位置
    public static int posCity = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        mContext = this;
        init();
    }

    public static MyApplication getInstance() {
        return context;
    }


    private void init() {
        NoHttp.initialize(this);
        Logger.setDebug(true);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("FXT");// 设置NoHttp打印Log的tag。
    }



    /** Activity被销毁时，从Activities中移除 */
    public void remove(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }



}
