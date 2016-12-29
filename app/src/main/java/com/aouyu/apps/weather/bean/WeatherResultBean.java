package com.aouyu.apps.weather.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fangxiaotian on 2016/12/26.
 */

public class WeatherResultBean implements Serializable {

    private List<HeWeatherBean> HeWeather5;

    public List<HeWeatherBean> getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(List<HeWeatherBean> heWeather5) {
        HeWeather5 = heWeather5;
    }

    @Override
    public String toString() {
        return "WeatherResultBean{" +
                "HeWeather5=" + HeWeather5 +
                '}';
    }
}
