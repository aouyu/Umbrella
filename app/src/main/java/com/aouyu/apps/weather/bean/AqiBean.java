package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * 空气质量
 * Created by fangxiaotian on 2016/12/23.
 */

public class AqiBean implements Serializable {

    private CityBean city;

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "AqiBean{" +
                "city=" + city +
                '}';
    }
}
