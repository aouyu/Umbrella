package com.aouyu.apps.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aouyu.apps.weather.R;
import com.aouyu.apps.weather.bean.HourlyForecastBean;
import com.aouyu.apps.weather.utils.DateUtil;

import java.util.List;

/**
 * 小时预报Adapter
 * Created by fang on 2016/12/27.
 */

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<HourlyForecastBean> hourlyForecastBeanList;

    public HourlyAdapter(Context context, List<HourlyForecastBean> list) {
        mInflater = LayoutInflater.from(context);
        hourlyForecastBeanList = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        TextView tvTime;
        TextView tvPop;
        ImageView imgCond;
        TextView tvTmp;
    }

    @Override
    public int getItemCount() {
        return hourlyForecastBeanList.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_hourly_forecast,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.tvTime = (TextView) view
                .findViewById(R.id.tv_time);
        viewHolder.tvPop = (TextView) view
                .findViewById(R.id.tv_pop);
        viewHolder.imgCond = (ImageView) view
                .findViewById(R.id.img_cond);
        viewHolder.tvTmp = (TextView) view
                .findViewById(R.id.tv_tmp);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        switch (hourlyForecastBeanList.get(i).getCond().getCode()) {
            case "100":
                viewHolder.imgCond.setImageResource(R.drawable.icon_sunny);
                break;
            case "101":
            case "102":
            case "103":
                viewHolder.imgCond.setImageResource(R.drawable.icon_cloudy);
                break;
            case "104":
                viewHolder.imgCond.setImageResource(R.drawable.icon_overcast);
                break;
            case "302":
            case "303":
            case "304":
                viewHolder.imgCond.setImageResource(R.drawable.icon_thundershower);
                break;
            case "305":
            case "309":
                viewHolder.imgCond.setImageResource(R.drawable.icon_light_rain);
                break;
            case "306":
            case "307":
            case "308":
            case "310":
            case "311":
            case "312":
            case "313":
                viewHolder.imgCond.setImageResource(R.drawable.icon_heavy_rain);
                break;
            case "400":
            case "401":
            case "402":
            case "403":
            case "404":
            case "405":
            case "406":
            case "407":
                viewHolder.imgCond.setImageResource(R.drawable.icon_snow);
                break;
            case "500":
            case "501":
            case "502":
            case "503":
            case "504":
            case "505":
            case "506":
            case "507":
            case "508":
                viewHolder.imgCond.setImageResource(R.drawable.icon_overcast);
                break;
            default:
                viewHolder.imgCond.setImageResource(R.drawable.icon_sunny);
                break;
        }
        if (i == 0) {
            viewHolder.tvTime.setText("现在");
        } else {
            viewHolder.tvTime.setText(DateUtil.extractHour(hourlyForecastBeanList.get(i).getDate()) + "时");
        }
        if (!hourlyForecastBeanList.get(i).getPop().equals("0")) {
            viewHolder.tvPop.setText(hourlyForecastBeanList.get(i).getPop());
        } else {
            viewHolder.tvPop.setVisibility(View.INVISIBLE);
        }
        viewHolder.tvTmp.setText(hourlyForecastBeanList.get(i).getTmp() + "°");
    }

}
