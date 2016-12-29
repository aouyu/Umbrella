package com.aouyu.apps.weather.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fangxiaotian on 2016/12/29.
 */

public class CityResultBean implements Serializable {

    private List<CityInfoBean> HeWeather5;

    public List<CityInfoBean> getHeWeather5() {
        return HeWeather5;
    }

    public void setHeWeather5(List<CityInfoBean> heWeather5) {
        HeWeather5 = heWeather5;
    }

    @Override
    public String toString() {
        return "CityResultBean{" +
                "HeWeather5=" + HeWeather5 +
                '}';
    }
}
