package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * 天文指数
 * Created by fangxiaotian on 2016/12/23.
 */

public class AstroBean implements Serializable {

    /**
     * mr : 03:09
     * ms : 17:06
     * sr : 05:28
     * ss : 18:29
     */

    private String mr;
    private String ms;
    private String sr;
    private String ss;

    public String getMr() {
        return mr;
    }

    public void setMr(String mr) {
        this.mr = mr;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getSr() {
        return sr;
    }

    public void setSr(String sr) {
        this.sr = sr;
    }

    public String getSs() {
        return ss;
    }

    public void setSs(String ss) {
        this.ss = ss;
    }

    @Override
    public String toString() {
        return "AstroBean{" +
                "mr='" + mr + '\'' +
                ", ms='" + ms + '\'' +
                ", sr='" + sr + '\'' +
                ", ss='" + ss + '\'' +
                '}';
    }
}
