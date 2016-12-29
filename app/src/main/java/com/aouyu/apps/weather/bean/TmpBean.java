package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * Created by fangxiaotian on 2016/12/23.
 */

public class TmpBean implements Serializable {

    /**
     * max : 29
     * min : 22
     */

    private String max;
    private String min;

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "TmpBean{" +
                "max='" + max + '\'' +
                ", min='" + min + '\'' +
                '}';
    }
}
