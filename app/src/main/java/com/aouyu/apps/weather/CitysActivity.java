package com.aouyu.apps.weather;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.aouyu.apps.weather.adapter.BaseRecyclerAdapter;
import com.aouyu.apps.weather.adapter.RecyclerViewHolder;
import com.aouyu.apps.weather.base.BaseSimpleActivity;
import com.aouyu.apps.weather.bean.CityItemBean;
import com.aouyu.apps.weather.bean.HeWeatherBean;
import com.aouyu.apps.weather.bean.WeatherResultBean;
import com.aouyu.apps.weather.http.ApiUrl;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 城市列表Activity
 * Created by fangxiaotian on 2016/12/28.
 */

public class CitysActivity extends BaseSimpleActivity {

    @BindView(R.id.recycler_citys)
    RecyclerView recyclerCitys;
    @BindView(R.id.animation_view)
    LottieAnimationView animationView;

    private WeatherResultBean resultBean;
    private HeWeatherBean heWeather;
    private String url;
    private RequestQueue queue;

    @OnClick(R.id.tv_add)
    void add() {
        startActivity(new Intent(this, AddActivity.class));
    }

    @OnClick(R.id.img_info)
    void info() {
        if (animationView.isAnimating()) {
            animationView.cancelAnimation();
        } else {
            animationView.playAnimation();
        }
    }

    private BaseRecyclerAdapter<CityItemBean> mBaseAdapter;
    private List<CityItemBean> cityItemBeanList = new ArrayList<>();

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_citys);
    }

    @Override
    protected void initData() {
        url = ApiUrl.API_BASE_URL + "weather";
        queue = NoHttp.newRequestQueue();
        cityItemBeanList = cityItemBeanList;
        Logger.d("城市列表" + cityItemBeanList);
        initAdapter();

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerCitys.setLayoutManager(linearLayoutManager);
        //设置Item增加、移除动画
        recyclerCitys.setItemAnimator(new DefaultItemAnimator());
        recyclerCitys.setAdapter(mBaseAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initAdapter() {
        mBaseAdapter = new BaseRecyclerAdapter<CityItemBean>(CitysActivity.this, cityItemBeanList) {

            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.item_city;
            }

            @Override
            protected void bindData(RecyclerViewHolder holder, int position, final CityItemBean item) {
                if (position == 0) {
                    holder.getImageView(R.id.img_loc).setVisibility(View.VISIBLE);
                }
                holder.setText(R.id.tv_time, item.getTime());
                holder.setText(R.id.tv_city, item.getCity());
                holder.setText(R.id.tv_tmp, item.getTmp() + "°");
            }

        };
        mBaseAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int pos) {
                // 首页需要刷新选中城市天气
                MyApplication.posCity = pos;
                EventBus.getDefault().post(new MessageEvent(cityItemBeanList.get(pos).getCity()));
                finish();
            }
        });
    }

    /**
     * 获取城市天气
     *
     * @param cityname
     */
    private void getWeather(String cityname) {
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
                    Logger.d("获取天气结果" + heWeather);
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

    }
}
