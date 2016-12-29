package com.aouyu.apps.weather.utils;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * 用于管理RxBus的事件和RxJava相关代码的生命周期处理
 * Created by huhuan on 2016/11/7
 */
public class RxManage {

    public RxBus mRxBus = RxBus.$();//实现EventBus功能
    private Map<String, Observable<?>> mObservables = new HashMap<>();// 管理观察者
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();// 管理订阅者者

    /**
     * 添加到订阅管理者中
     */
    public void add(Subscription m) {
        mCompositeSubscription.add(m);
    }

    /**
     * 取消订阅和取消注册的RxBus事件
     */
    public void clear() {
        mCompositeSubscription.unsubscribe();// 取消订阅
        for (Map.Entry<String, Observable<?>> entry : mObservables.entrySet()) {
            mRxBus.unregister(entry.getKey(), entry.getValue());// 移除观察
        }
    }

    /**
     * 注册RxBus的事件
     */
    public void on(String eventName, Action1<Object> action1) {
        Observable<?> observable = mRxBus.register(eventName);
        mObservables.put(eventName, observable);
        mCompositeSubscription.add(observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }));
    }

    /**
     * 执行RxBus指定的事件
     */
    public void post(Object tag, Object content) {
        mRxBus.post(tag, content);
    }
}
