package com.aouyu.apps.weather;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aouyu.apps.weather.adapter.DailyAdapter;
import com.aouyu.apps.weather.adapter.HourlyAdapter;
import com.aouyu.apps.weather.base.BasePresenterActivity;
import com.aouyu.apps.weather.bean.CityItemBean;
import com.aouyu.apps.weather.bean.DailyForecastBean;
import com.aouyu.apps.weather.bean.HeWeatherBean;
import com.aouyu.apps.weather.bean.HourlyForecastBean;
import com.aouyu.apps.weather.bean.WeatherResultBean;
import com.aouyu.apps.weather.http.ApiUrl;
import com.aouyu.apps.weather.utils.DateUtil;
import com.aouyu.apps.weather.utils.MessageEvent;
import com.google.gson.Gson;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.StringRequest;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BasePresenterActivity<MainPresenter> implements MainConstract.IMainView {

    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_weather_now)
    TextView tvWeatherNow;
    @BindView(R.id.tv_tmp_now)
    TextView tvTmpNow;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.tv_tmp_max)
    TextView tvTmpMax;
    @BindView(R.id.tv_tmp_min)
    TextView tvTmpMin;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_sunrise_time)
    TextView tvSunrise;
    @BindView(R.id.tv_sunset_time)
    TextView tvSunset;
    @BindView(R.id.tv_pop_num)
    TextView tvPop;
    @BindView(R.id.tv_hum_num)
    TextView tvHum;
    @BindView(R.id.tv_speed_num)
    TextView tvSpeed;
    @BindView(R.id.tv_felt_num)
    TextView tvFelt;
    @BindView(R.id.tv_pcpn_num)
    TextView tvPcpn;
    @BindView(R.id.tv_pres_num)
    TextView tvPres;
    @BindView(R.id.tv_vis_num)
    TextView tvVis;
    @BindView(R.id.tv_uv_num)
    TextView tvUv;
    @BindView(R.id.tv_aqi_num)
    TextView tvAqi;
    @BindView(R.id.tv_qlty_num)
    TextView tvQlty;
    @BindView(R.id.recycler_hourly)
    RecyclerView recyclerHourly;
    @BindView(R.id.recycler_daily)
    RecyclerView recyclerDaily;
    @BindView(R.id.scrollView)
    ScrollView scrollview;

    private WeatherResultBean resultBean;
    private HeWeatherBean heWeather;
    private HourlyAdapter hourlyAdapter;
    private DailyAdapter dailyAdapter;
    private List<HourlyForecastBean> hourlyForecastBeanList = new ArrayList<>();
    private List<DailyForecastBean> dailyForecastBeanList = new ArrayList<>();
    private HourlyForecastBean hourlyBean = new HourlyForecastBean();
    private CityItemBean cityItemBean = new CityItemBean();
    private RequestQueue queue;
    private String url;

    @OnClick(R.id.img_city_list)
    void cityList() {
        Intent intent = new Intent(this, CitysActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.img_info)
    void info() {
        show("开发中");
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {
        //注册EventBus
        EventBus.getDefault().register(this);
        url = ApiUrl.API_BASE_URL + "weather";
        queue = NoHttp.newRequestQueue();
        getWeather("上海");
    }

    /**
     * 获取城市天气
     * @param cityname
     */
    private void getWeather(String cityname) {
        hourlyForecastBeanList.clear();
        dailyForecastBeanList.clear();
        Request<String> request = new StringRequest(url);
        // 添加url?key=value形式的参数
        request.add("city", cityname);
        request.add("key", ApiUrl.API_KEY);
        queue.add(0, request, new OnResponseListener<String>() {

            @Override
            public void onSucceed(int what, Response<String> response) {
                if (response.responseCode() == 200) {// 请求成功。
                    Gson gson = new Gson();
                    resultBean = gson.fromJson(response.get(), WeatherResultBean.class);
                    heWeather = resultBean.getHeWeather5().get(0);
                    Logger.d(heWeather);
                    bindData();
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onStart(int what) {
                // 这里可以show()一个wait dialog。
            }

            @Override
            public void onFinish(int what) {
                // 这里可以dismiss()上面show()的wait dialog。
            }
        });
    }

    private void bindData() {
        tvDay.setText(DateUtil.getWeek());
        Logger.d(DateUtil.getWeek());
        tvCity.setText(heWeather.getBasic().getCity() + "市");
        tvWeatherNow.setText(heWeather.getNow().getCond().getTxt());
        tvTmpNow.setText(heWeather.getNow().getTmp());
        tvTmpMax.setText(heWeather.getDaily_forecast().get(0).getTmp().getMax());
        tvTmpMin.setText(heWeather.getDaily_forecast().get(0).getTmp().getMin());
        tvDesc.setText("今天：现在" + heWeather.getNow().getCond().getTxt() + "。最高气温" +
                heWeather.getDaily_forecast().get(0).getTmp().getMax() + "°，今晚最低气温" +
                heWeather.getDaily_forecast().get(0).getTmp().getMin() + "°。");
        tvSunrise.setText(heWeather.getDaily_forecast().get(0).getAstro().getSr());
        tvSunset.setText(heWeather.getDaily_forecast().get(0).getAstro().getSs());
        tvPop.setText(heWeather.getDaily_forecast().get(0).getPop() + "%");
        tvHum.setText(heWeather.getNow().getHum() + "%");
        tvSpeed.setText(heWeather.getNow().getWind().getDir() + " 每秒" + heWeather.getNow().getWind().getSpd() + "米");
        tvFelt.setText(heWeather.getNow().getFl() + "°");
        tvPcpn.setText(heWeather.getNow().getPcpn() + "毫米");
        tvPres.setText(heWeather.getNow().getPres() + "百帕");
        tvVis.setText(heWeather.getNow().getVis() + "公里");
        tvUv.setText(heWeather.getSuggestion().getUv().getBrf());
        tvAqi.setText(heWeather.getAqi().getCity().getAqi());
        tvQlty.setText(heWeather.getAqi().getCity().getQlty());

        hourlyBean.setTmp(heWeather.getNow().getTmp());
        hourlyBean.setPop("0");
        hourlyForecastBeanList.add(hourlyBean);
        for (int i = 0; i < heWeather.getHourly_forecast().size(); i++) {
            hourlyForecastBeanList.add(heWeather.getHourly_forecast().get(i));
        }
        Logger.d(heWeather.getHourly_forecast().size());
        Logger.d(hourlyForecastBeanList);
        for (int i = 0; i < heWeather.getDaily_forecast().size(); i++) {
            if (i != 0) {
                dailyForecastBeanList.add(heWeather.getDaily_forecast().get(i));
            }
        }
        // 添加城市列表信息
        cityItemBean.setCity(heWeather.getBasic().getCity());
        cityItemBean.setTmp(heWeather.getNow().getTmp());
        cityItemBean.setTime(DateUtil.extractTime(heWeather.getBasic().getUpdate().getLoc()));
        if (MyApplication.cityItemBeanList.size() == 0) {
            MyApplication.cityItemBeanList.add(cityItemBean);
            Logger.d("增加");
        } else {
            Logger.d("替换" + MyApplication.posCity);
            MyApplication.cityItemBeanList.remove(MyApplication.posCity);
            MyApplication.cityItemBeanList.add(MyApplication.posCity, cityItemBean);
        }

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerHourly.setLayoutManager(linearLayoutManager);
        //设置适配器
        hourlyAdapter = new HourlyAdapter(this, hourlyForecastBeanList);
        recyclerHourly.setAdapter(hourlyAdapter);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        recyclerDaily.setLayoutManager(linearLayoutManager2);
        //设置适配器
        dailyAdapter = new DailyAdapter(this, dailyForecastBeanList, DateUtil.getWeek());
        //设置Item增加、移除动画
        recyclerDaily.setItemAnimator(new DefaultItemAnimator());
        recyclerDaily.setAdapter(dailyAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Logger.d("接收到消息：" + event.getMsg());
        getWeather(event.getMsg());
    }

    @Override
    public void onResume() {
        super.onResume();
        scrollview.scrollTo(10, 10);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getWeatherSuccess(HeWeatherBean heWeatherBean) {
        Log.d("FXT", "天气数据" + heWeatherBean.toString());
    }

    @Override
    public void getWeatherFail() {

    }
}

