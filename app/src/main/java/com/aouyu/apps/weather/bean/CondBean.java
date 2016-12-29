package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * Created by fangxiaotian on 2016/12/23.
 */

public class CondBean implements Serializable {

    /**
     * code_d : 100
     * code_n : 100
     * txt_d : 晴
     * txt_n : 晴
     */

    private String code_d;
    private String code_n;
    private String txt_d;
    private String txt_n;

    public String getCode_d() {
        return code_d;
    }

    public void setCode_d(String code_d) {
        this.code_d = code_d;
    }

    public String getCode_n() {
        return code_n;
    }

    public void setCode_n(String code_n) {
        this.code_n = code_n;
    }

    public String getTxt_d() {
        return txt_d;
    }

    public void setTxt_d(String txt_d) {
        this.txt_d = txt_d;
    }

    public String getTxt_n() {
        return txt_n;
    }

    public void setTxt_n(String txt_n) {
        this.txt_n = txt_n;
    }

    @Override
    public String toString() {
        return "CondBean{" +
                "code_d='" + code_d + '\'' +
                ", code_n='" + code_n + '\'' +
                ", txt_d='" + txt_d + '\'' +
                ", txt_n='" + txt_n + '\'' +
                '}';
    }
}
