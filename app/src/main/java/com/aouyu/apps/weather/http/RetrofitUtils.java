package com.aouyu.apps.weather.http;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Retofit网络请求工具类
 */
public class RetrofitUtils {
    private static final int CONN_TIMEOUT = 10;//连接超时时间,单位  秒
    private static final int READ_TIMEOUT = 10;//读取超时时间,单位  秒
    private static Retrofit mRetrofit;

    private RetrofitUtils() {

    }


    public static Retrofit newInstence() {
        mRetrofit = null;
        OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false);// 失败重发
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())//添加一个client,不然retrofit会自己默认添加一个
                .baseUrl(ApiUrl.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return mRetrofit;
    }

    public static APIService getService(){

        return newInstence().create(APIService.class);
    }

    public static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    /**
     * 添加完整请求参数，需要登录操作的接口，添加token
     *
     * @param map
     * @return
     */
    public static String getParams(Map<String, Object> map) {
        String jsonParam = "";
        try {
            jsonParam = new JSONObject(new Gson().toJson(map)).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonParam;
    }
}
