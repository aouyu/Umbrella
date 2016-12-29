package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * 洗车建议
 * Created by fangxiaotian on 2016/12/23.
 */

public class CwBean implements Serializable {

    /**
     * brf : 较不宜
     * txt : 较不宜洗车，未来一天无雨，风力较大，如果执意擦洗汽车，要做好蒙上污垢的心理准备。
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
        return "CwBean{" +
                "brf='" + brf + '\'' +
                ", txt='" + txt + '\'' +
                '}';
    }
}
