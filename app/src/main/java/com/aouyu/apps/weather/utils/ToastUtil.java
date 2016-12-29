package com.aouyu.apps.weather.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.aouyu.apps.weather.MyApplication;


/**
 * Toast单例
 *
 * Created by fangxiaotian on 2016/11/3.
 */

public class ToastUtil {
    private volatile static Toast mToast;


    public static void show(Context context,String message){
        if (!TextUtils.isEmpty(message)) {
            if (mToast == null) {
                mToast = Toast.makeText(context.getApplicationContext(),
                        message, Toast.LENGTH_SHORT);
                mToast.show();
            } else {
                mToast.setText(message);
                mToast.show();
            }
        }
    }
    public static void show( String message) {
        if (!TextUtils.isEmpty(message)) {
            if (mToast == null) {
                mToast = Toast.makeText(MyApplication.mContext.getApplicationContext(),
                        message, Toast.LENGTH_SHORT);
                mToast.show();
            } else {
                mToast.setText(message);
                mToast.show();
            }
        }
    }

    public static void cancel() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
