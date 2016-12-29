package com.aouyu.apps.weather.bean;

import java.io.Serializable;

/**
 * 紫外线
 * Created by fangxiaotian on 2016/12/23.
 */

public class UvBean implements Serializable {

    /**
     * brf : 强
     * txt : 紫外线辐射强，建议涂擦SPF20左右、PA++的防晒护肤品。避免在10点至14点暴露于日光下。
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
        return "UvBean{" +
                "brf='" + brf + '\'' +
                ", txt='" + txt + '\'' +
                '}';
    }
}
