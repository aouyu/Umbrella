package com.aouyu.apps.weather.utils;

/**
 * evnetbus定义消息类
 * Created by fangxiaotian on 2016/12/29.
 */

public class MessageEvent {

    private String mMsg;

    public MessageEvent(String msg) {
        mMsg = msg;
    }

    public String getMsg() {
        return mMsg;
    }
}
