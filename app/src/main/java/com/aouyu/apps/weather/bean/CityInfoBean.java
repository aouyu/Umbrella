package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * Created by fangxiaotian on 2016/12/29.
 */

public class CityInfoBean implements Serializable {

    private CityBasicBean basic;
    private String status;

    public CityBasicBean getBasic() {
        return basic;
    }

    public void setBasic(CityBasicBean basic) {
        this.basic = basic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CityInfoBean{" +
                "basic=" + basic +
                ", status='" + status + '\'' +
                '}';
    }
}
