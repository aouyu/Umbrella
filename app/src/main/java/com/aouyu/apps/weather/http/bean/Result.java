package com.aouyu.apps.weather.http.bean;

import java.util.List;

/**
 * Result
 */
public class Result<T> {
    /** 数据源 */
    public List<T> HeWeather5;

    @Override
    public String toString() {
        return "Result{" +
                "HeWeather5=" + HeWeather5 +
                '}';
    }
}
