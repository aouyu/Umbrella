package com.aouyu.apps.weather;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by fangxiaotian on 2016/12/23.
 */

public class MainModel extends MainConstract.IMainModel {
    @Override
    Observable<String> getWeather(String city, String key, String lang) {
        Map<String, String> map = new HashMap<>();
        map.put("city", city);
        map.put("key", key);
        map.put("lang", lang);
        return null;
    }
}
