package com.aouyu.apps.weather;

import android.util.Log;

import com.aouyu.apps.weather.base.RxBasePresenter;
import com.aouyu.apps.weather.bean.HeWeatherBean;
import com.aouyu.apps.weather.http.HttpSubscriber;
import com.aouyu.apps.weather.http.bean.Result;
import com.google.gson.reflect.TypeToken;

/**
 * Created by fangxiaotian on 2016/12/23.
 */

public class MainPresenter extends RxBasePresenter<MainConstract.IMainView> {
    MainModel mainModel = new MainModel();

    public void getWeather(String city, String key, String lang) {
        mRxManage.add(
                mainModel.getWeather(city, key, lang)
                        .subscribe(new HttpSubscriber<HeWeatherBean>(this, new TypeToken<Result<HeWeatherBean>>() {
                        }.getType()) {
                            @Override
                            public void success(HeWeatherBean heWeatherBean) {
                                Log.i("FXT", "***天气数据----------" + heWeatherBean.toString());
                                mView.getWeatherSuccess(heWeatherBean);
                            }

                            @Override
                            public void fail() {
                                super.fail();
                                Log.i("FXT", "获取天气失败");
                                mView.getWeatherFail();
                            }
                        }));
    }
}
