package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * 运动建议
 * Created by fangxiaotian on 2016/12/23.
 */

public class SportBean implements Serializable {

    /**
     * brf : 较适宜
     * txt : 天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。
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
        return "SportBean{" +
                "brf='" + brf + '\'' +
                ", txt='" + txt + '\'' +
                '}';
    }
}
