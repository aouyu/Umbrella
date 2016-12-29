package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * 感冒指数
 * Created by fangxiaotian on 2016/12/23.
 */

public class FluBean implements Serializable {

    /**
     * brf : 较易发
     * txt : 虽然温度适宜但风力较大，仍较易发生感冒，体质较弱的朋友请注意适当防护。
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
        return "FluBean{" +
                "brf='" + brf + '\'' +
                ", txt='" + txt + '\'' +
                '}';
    }
}
