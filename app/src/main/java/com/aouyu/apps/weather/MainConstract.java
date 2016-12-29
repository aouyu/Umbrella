package com.aouyu.apps.weather;

import com.aouyu.apps.weather.base.BaseModel;
import com.aouyu.apps.weather.base.BaseView;
import com.aouyu.apps.weather.bean.HeWeatherBean;

import rx.Observable;

/**
 * Created by fangxiaotian on 2016/12/23.
 */

public interface MainConstract {

    /**
     * V视图层  (公共的用户交互逻辑可以扩展在BaseView中)
     */
    interface IMainView extends BaseView {

        // 请求成功
        void getWeatherSuccess(HeWeatherBean heWeatherBean);

        // 请求失败
        void getWeatherFail();
    }

    /**
     * M逻辑处理层
     */
    abstract class IMainModel extends BaseModel {

        // 请求天气
        abstract Observable<String> getWeather(String city, String key, String lang);

    }
}
