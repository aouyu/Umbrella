package com.aouyu.apps.weather;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aouyu.apps.weather.adapter.BaseRecyclerAdapter;
import com.aouyu.apps.weather.adapter.RecyclerViewHolder;
import com.aouyu.apps.weather.base.BaseSimpleActivity;
import com.aouyu.apps.weather.bean.CityItemBean;
import com.aouyu.apps.weather.utils.MessageEvent;
import com.yolanda.nohttp.Logger;

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

    @OnClick(R.id.tv_add)
    void add() {
        startActivity(new Intent(this, AddActivity.class));
    }

    @OnClick(R.id.img_info)
    void info() {

    }

    private BaseRecyclerAdapter<CityItemBean> mBaseAdapter;
    private List<CityItemBean> cityItemBeanList = new ArrayList<>();

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_citys);
    }

    @Override
    protected void initData() {
        cityItemBeanList = MyApplication.cityItemBeanList;
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
}
