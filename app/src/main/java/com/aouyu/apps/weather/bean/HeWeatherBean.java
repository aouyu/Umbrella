package com.aouyu.apps.weather.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 天气预报集合
 * Created by fangxiaotian on 2016/12/23.
 */

public class HeWeatherBean implements Serializable {

    private ArrayList<AlarmsBean> alarms;
    private AqiBean aqi;
    private BasicBean basic;
    private ArrayList<DailyForecastBean> daily_forecast;
    private ArrayList<HourlyForecastBean> hourly_forecast;
    private NowBean now;
    private String status;  // 数据返回状态
    private SuggestionBean suggestion;

    public ArrayList<AlarmsBean> getAlarms() {
        return alarms;
    }

    public void setAlarms(ArrayList<AlarmsBean> alarms) {
        this.alarms = alarms;
    }

    public AqiBean getAqi() {
        return aqi;
    }

    public void setAqi(AqiBean aqi) {
        this.aqi = aqi;
    }

    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
    }

    public ArrayList<DailyForecastBean> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(ArrayList<DailyForecastBean> daily_forecast) {
        this.daily_forecast = daily_forecast;
    }

    public ArrayList<HourlyForecastBean> getHourly_forecast() {
        return hourly_forecast;
    }

    public void setHourly_forecast(ArrayList<HourlyForecastBean> hourly_forecast) {
        this.hourly_forecast = hourly_forecast;
    }

    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public SuggestionBean getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(SuggestionBean suggestion) {
        this.suggestion = suggestion;
    }

    @Override
    public String toString() {
        return "HeWeatherBean{" +
                "alarms=" + alarms +
                ", aqi=" + aqi +
                ", basic=" + basic +
                ", daily_forecast=" + daily_forecast +
                ", hourly_forecast=" + hourly_forecast +
                ", now=" + now +
                ", status='" + status + '\'' +
                ", suggestion=" + suggestion +
                '}';
    }
}
