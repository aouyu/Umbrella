package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * 灾害预警
 * Created by fangxiaotian on 2016/12/23.
 */

public class AlarmsBean implements Serializable {

    private String level;    //
    private String stat;
    private String title;
    private String txt;
    private String type;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AlarmsBean{" +
                "level='" + level + '\'' +
                ", stat='" + stat + '\'' +
                ", title='" + title + '\'' +
                ", txt='" + txt + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
