package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * Created by fangxiaotian on 2016/12/23.
 */

public class HourlyForecastBean implements Serializable {

    /**
     * cond : {"code":"100","txt":"晴"}
     * date : 2016-08-30 12:00
     * hum : 47
     * pop : 0
     * pres : 1006
     * tmp : 29
     * wind : {"deg":"335","dir":"西北风","sc":"4-5","spd":"36"}
     */

    private CondNowBean cond;
    private String date;
    private String hum;
    private String pop;
    private String pres;
    private String tmp;
    private WindBean wind;

    public CondNowBean getCond() {
        return cond;
    }

    public void setCond(CondNowBean cond) {
        this.cond = cond;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }

    @Override
    public String toString() {
        return "HourlyForecastBean{" +
                "cond=" + cond +
                ", date='" + date + '\'' +
                ", hum='" + hum + '\'' +
                ", pop='" + pop + '\'' +
                ", pres='" + pres + '\'' +
                ", tmp='" + tmp + '\'' +
                ", wind=" + wind +
                '}';
    }
}
