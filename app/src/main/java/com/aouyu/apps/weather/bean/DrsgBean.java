package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * 穿衣建议
 * Created by fangxiaotian on 2016/12/23.
 */

public class DrsgBean implements Serializable {

    /**
     * brf : 热
     * txt : 天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。
     */

    private String brf;
    private String txt;

    public String getBrf() {
        return brf;
    }

    public void setBrf(String brf) {
        this.brf = brf;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return "DrsgBean{" +
                "brf='" + brf + '\'' +
                ", txt='" + txt + '\'' +
                '}';
    }
}
