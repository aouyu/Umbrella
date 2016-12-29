package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * Created by fangxiaotian on 2016/12/23.
 */

public class UpdateBean implements Serializable {

    /**
     * loc : 2016-08-30 11:52
     * utc : 2016-08-30 03:52
     */

    private String loc;
    private String utc;

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }

    @Override
    public String toString() {
        return "UpdateBean{" +
                "loc='" + loc + '\'' +
                ", utc='" + utc + '\'' +
                '}';
    }
}
