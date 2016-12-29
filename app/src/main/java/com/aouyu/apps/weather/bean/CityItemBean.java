package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * Created by fangxiaotian on 2016/12/29.
 */

public class CityItemBean implements Serializable {

    private String time;    // 数据刷新时间
    private String city;    // 保存的城市
    private String tmp;     // 城市温度

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    @Override
    public String toString() {
        return "CityItemBean{" +
                "time='" + time + '\'' +
                ", city='" + city + '\'' +
                ", tmp='" + tmp + '\'' +
                '}';
    }
}
