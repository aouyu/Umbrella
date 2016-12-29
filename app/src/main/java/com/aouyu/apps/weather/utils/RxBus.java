package com.aouyu.apps.weather.utils;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

/**
 * 用RxJava实现的EventBus
 * Created by huhuan on 2016/11/7
 */
public class RxBus {
    private static RxBus instance;
    @SuppressWarnings("rawtypes")
    private ConcurrentHashMap<Object, List<Subject>> subjectMapper = new ConcurrentHashMap<>();

    private RxBus() {
    }

    public static synchronized RxBus $() {
        if (null == instance) {
            instance = new RxBus();
        }
        return instance;
    }

    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Collection<Subject> collection) {
        return null == collection || collection.isEmpty();
    }

    /**
     * 订阅事件源
     */
    public RxBus OnEvent(Observable<?> observable, Action1<Object> action1) {
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(action1, new
                Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        return $();
    }

    /**
     * 注册事件源
     */
    @SuppressWarnings({"rawtypes"})
    public <T> Observable<T> register(@NonNull Object tag) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (null == subjectList) {
            subjectList = new ArrayList<>();
            subjectMapper.put(tag, subjectList);
        }
        Subject<T, T> subject;
        subjectList.add(subject = PublishSubject.create());
        return subject;
    }

    @SuppressWarnings("rawtypes")
    public void unregister(@NonNull Object tag) {
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjectMapper.remove(tag);
        }
    }

    /**
     * 取消监听
     */
    @SuppressWarnings("rawtypes")
    public RxBus unregister(@NonNull Object tag,
                            @NonNull Observable<?> observable) {
        if (null == observable)
            return $();
        List<Subject> subjects = subjectMapper.get(tag);
        if (null != subjects) {
            subjects.remove(observable);
            if (isEmpty(subjects)) {
                subjectMapper.remove(tag);
            }
        }
        return $();
    }

    public void post(@NonNull Object content) {
        post(content.getClass().getName(), content);
    }

    /**
     * 触发事件
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public void post(@NonNull Object tag, @NonNull Object content) {
        List<Subject> subjectList = subjectMapper.get(tag);
        if (!isEmpty(subjectList)) {
            for (Subject subject : subjectList) {
                subject.onNext(content);
            }
        }
    }
}
