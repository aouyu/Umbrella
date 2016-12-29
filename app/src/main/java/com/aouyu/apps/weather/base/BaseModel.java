package com.aouyu.apps.weather.base;

import com.aouyu.apps.weather.utils.ToastUtil;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by huhuan on 2016/11/9.
 */

public abstract class BaseModel {

    public Observable<String> io_main(Observable<String> observable){
        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public void show(String msgToast){
        ToastUtil.show(msgToast);
    }
}
