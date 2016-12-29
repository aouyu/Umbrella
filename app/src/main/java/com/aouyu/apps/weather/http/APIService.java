package com.aouyu.apps.weather.http;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * API--接口  服务[这里处理的是同一的返回格式 resultCode  resultInfo Item<T> --->这里的Item才是返回的结果,T就是泛型 具体返回的been对象或集合]
 */
public interface APIService {


    /**
     * 7-10天预报
     */
    @GET(ApiUrl.FORECAST)
    Observable<String> getForecast(@Body String json);

    /**
     * 实况天气
     */
    @GET(ApiUrl.NOW)
    Observable<String> getNow(@Body String json);

    /**
     * 每小时预报（逐小时预报），最长10天
     */
    @GET(ApiUrl.HOURLY)
    Observable<String> getHourly(@Body String json);

    /**
     * 生活指数
     */
    @GET(ApiUrl.SUGGESTION)
    Observable<String> getSuggestion(@Body String json);

    /**
     * 灾害预警
     */
    @GET(ApiUrl.ALARM)
    Observable<String> getAlarm(@Body String json);

    /**
     * 天气预报
     */
    @GET(ApiUrl.WEATHER)
    Call<String> getWeather(@Query("city") String city, @Query("key") String key, @Query("lang") String lang);

    /**
     * 景点天气
     */
    @GET(ApiUrl.SCENIC)
    Observable<String> getScenic(@Body String json);

    /**
     * 历史天气
     */
    @GET(ApiUrl.HISTORICAL)
    Observable<String> getHistorical(@Body String json);


    /**
     * 城市查询
     */
    @POST(ApiUrl.SEARCH)
    Observable<String> getSearch(@Body String json);

}
