package com.aouyu.apps.weather;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aouyu.apps.weather.base.BaseSimpleActivity;
import com.aouyu.apps.weather.bean.CityInfoBean;
import com.aouyu.apps.weather.bean.CityItemBean;
import com.aouyu.apps.weather.bean.CityResultBean;
import com.aouyu.apps.weather.http.ApiUrl;
import com.google.gson.Gson;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.StringRequest;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 添加城市Activity
 * Created by fangxiaotian on 2016/12/28.
 */

public class AddActivity extends BaseSimpleActivity{

    @BindView(R.id.edt_city_name)
    EditText edtCityName;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.recycler_search)
    RecyclerView recyclerSearch;

    private CityResultBean resultBean;
    private CityInfoBean cityInfoBean;
    private CityItemBean cityItemBean = new CityItemBean();

    @OnClick(R.id.tv_cancel)
    void cancel() {
        finish();
    }

    @OnClick(R.id.tv_result)
    void cityOk() {
        // 添加城市到列表
        cityItemBean.setCity(edtCityName.getText().toString().trim());
        cityItemBean.setTmp("");
        cityItemBean.setTime("");
        MyApplication.cityItemBeanList.add(cityItemBean);
        finish();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_add);
    }

    @Override
    protected void initData() {
        edtCityName.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(final CharSequence s, int start, int before,
                                      int count) {
                if (!TextUtils.isEmpty(s)) {
                    String url = ApiUrl.API_BASE_URL + "search";
                    RequestQueue queue = NoHttp.newRequestQueue();
                    Request<String> request = new StringRequest(url);
                    // 添加url?key=value形式的参数
                    request.add("city", s.toString().trim());
                    request.add("key", ApiUrl.API_KEY);
                    queue.add(0, request, new OnResponseListener<String>() {

                        @Override
                        public void onSucceed(int what, Response<String> response) {
                            if (response.responseCode() == 200) {// 请求成功。
                                Gson gson = new Gson();
                                resultBean = gson.fromJson(response.get(), CityResultBean.class);
                                cityInfoBean = resultBean.getHeWeather5().get(0);
                                Logger.d("城市搜索" + cityInfoBean);
                                if (cityInfoBean.getStatus().equals("ok")) {
                                    tvResult.setClickable(true);
                                    tvResult.setVisibility(View.VISIBLE);
                                    tvResult.setText(cityInfoBean.getBasic().getCity());
                                } else {
                                    tvResult.setClickable(false);
                                    tvResult.setText("");
                                }
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
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
