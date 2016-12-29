package com.aouyu.apps.weather.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aouyu.apps.weather.R;
import com.aouyu.apps.weather.bean.DailyForecastBean;
import com.yolanda.nohttp.Logger;

import java.util.List;

/**
 * 天预报Adapter
 * Created by fang on 2016/12/27.
 */

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<DailyForecastBean> dailyForecastBeanList;
    private String todayWeek;
    private int position = 0;
    // 周几数组
    static String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    public DailyAdapter(Context context, List<DailyForecastBean> list, String strWeek) {
        mInflater = LayoutInflater.from(context);
        dailyForecastBeanList = list;
        todayWeek = strWeek;

        // 得到今天在数组中的位置
        for (int i = 0; i < weeks.length; i++) {
            if (weeks[i].equals(strWeek)) {
                position = i;
            }
        }
        Logger.d("位置" + position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        TextView tvDay;
        ImageView imgCond;
        TextView tvMax;
        TextView tvMin;
    }

    @Override
    public int getItemCount() {
        return dailyForecastBeanList.size();
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_daily_forecast,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.tvDay = (TextView) view
                .findViewById(R.id.tv_day);
        viewHolder.imgCond = (ImageView) view
                .findViewById(R.id.img_cond);
        viewHolder.tvMax = (TextView) view
                .findViewById(R.id.tv_tmp_max);
        viewHolder.tvMin = (TextView) view
                .findViewById(R.id.tv_tmp_min);
        return viewHolder;
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        if ((i + position + 1) < weeks.length) {
            viewHolder.tvDay.setText(weeks[i + position + 1]);
        } else {
            viewHolder.tvDay.setText(weeks[i - (weeks.length - position - 1)]);
        }
//        viewHolder.mImg.setImageResource(dailyForecastBeanList.get(i));
        viewHolder.tvMax.setText(dailyForecastBeanList.get(i).getTmp().getMax());
        viewHolder.tvMin.setText(dailyForecastBeanList.get(i).getTmp().getMin());
    }

}