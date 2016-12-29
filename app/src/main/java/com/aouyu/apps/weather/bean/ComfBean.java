package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * 舒适度
 * Created by fangxiaotian on 2016/12/23.
 */

public class ComfBean implements Serializable {

    /**
     * brf : 较舒适
     * txt : 白天天气晴好，您在这种天气条件下，会感觉早晚凉爽、舒适，午后偏热。
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
        return "ComfBean{" +
                "brf='" + brf + '\'' +
                ", txt='" + txt + '\'' +
                '}';
    }
}
