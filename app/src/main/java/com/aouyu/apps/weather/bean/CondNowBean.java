package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * Created by fangxiaotian on 2016/12/27.
 */

public class CondNowBean implements Serializable {

    /**
     * code : 100
     * txt : 晴
     */

    private String code;
    private String txt;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return "CondNowBean{" +
                "code='" + code + '\'' +
                ", txt='" + txt + '\'' +
                '}';
    }
}
