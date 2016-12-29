package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * Created by fangxiaotian on 2016/12/23.
 */

public class WindBean implements Serializable {

    /**
     * deg : 339
     * dir : 北风
     * sc : 4-5
     * spd : 24
     */

    private String deg;
    private String dir;
    private String sc;
    private String spd;

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getSpd() {
        return spd;
    }

    public void setSpd(String spd) {
        this.spd = spd;
    }

    @Override
    public String toString() {
        return "WindBean{" +
                "deg='" + deg + '\'' +
                ", dir='" + dir + '\'' +
                ", sc='" + sc + '\'' +
                ", spd='" + spd + '\'' +
                '}';
    }
}
