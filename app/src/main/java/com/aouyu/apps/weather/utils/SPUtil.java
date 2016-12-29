package com.aouyu.apps.weather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.aouyu.apps.weather.bean.CityItemBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * 文件缓存
 *
 * @author huangyue
 */
public class SPUtil {

    long startTime = 0l;
    long endTime = 0l;

    private static final String SMART_CAR_SP = "smartCar_sp";// 缓存文件名

    private static SharedPreferences sp;

    private static final String FIRST_IN = "firstIn";// 首次进入应用
    private static final String AD_POPUP_WINDOW = "ad_popup_window";// 首次进入应用

    private static final String fragment_state = "_fragmentState";//

    private static final String GESTUREVERIFY_STATE = "_gestureverifystate";


    private static final String USER_ID = "userId";// 用户id
    private static final String ID_NUM = "idNum";// 用户身份证
    private static final String TOKEN = "token";// token
    private static final String DEVICE_TOKEN = "deviceToken";// 登录设备的服务端唯一标识
    private static final String DEVICE_ID = "deviceId";// 设备id
    private static final String REAL_NAME = "realName";// 用户名
    private static final String ALIAS = "alias";// 推送别名
    private static final String NICK_NAME = "nickName";// 用户昵称
    private static final String PHONE = "phone";// 手机号
    private static final String AVATAR = "avatar";// 头像的url地址
    private static final String CERTIFIED_STATUS = "CertifiedStatus";// 用户认证状态
    private static final String BROWSE_COUNT = "browseCount";// 浏览量
    private static final String USER_NO = "sUserNo";// 用户编号
    private static final String LOCAL_CITY = "localCity";// 用户编号
    private static final String LOCAL_CITY_ID = "localCityId";// 用户编号
    private static final String USER_HEAD = "userHead";// 用户头像地址
    private static final String I_VERIFY_STATUS = "iVerifyStatus";    // 车商认证状态
    private static final String B_IS_OPEN = "bIsOpen";    // 是否富友已开户
    private static final String B_EMAIL_BIND = "bEmailBind";    // 是否富友已开户

    /* 缓存二手车首页和买车页面的最新时间戳 */
    private static final String USED_CAR_TIME = "usedCarTime";
    private static final String BUY_CAR_TIME = "buyCarTime";
    private static final String COUPON_NEW_TIME = "newCouponTime";

    private static final String UPDATE_APK_VERSION = "updateApkVersion";// 缓存最新apk的版本

    private static final String My_REFUEL_CARD_DEFAULT = "myRefuelCardDefault";

    private static SharedPreferences getSP(Context context) {
        if (sp == null)
            sp = context.getSharedPreferences(SMART_CAR_SP,
                    Context.MODE_PRIVATE);
        return sp;
    }

    /**
     * 序列化对象
     *
     * @return
     * @throws IOException
     */
    private String serializeCityList(List<CityItemBean> cityItemBeanList) throws IOException {
        startTime = System.currentTimeMillis();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        objectOutputStream.writeObject(cityItemBeanList);
        String serStr = byteArrayOutputStream.toString("ISO-8859-1");
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        Log.d("serial", "serialize str =" + serStr);
        endTime = System.currentTimeMillis();
        Log.d("serial", "序列化耗时为:" + (endTime - startTime));
        return serStr;
    }

    /**
     * 反序列化对象
     *
     * @param str
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private List<CityItemBean> deSerializationCityList(String str) throws IOException,
            ClassNotFoundException {
        startTime = System.currentTimeMillis();
        String redStr = java.net.URLDecoder.decode(str, "UTF-8");
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                redStr.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(
                byteArrayInputStream);
        List<CityItemBean> cityItemBeanList = (List<CityItemBean>) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        endTime = System.currentTimeMillis();
        Log.d("serial", "反序列化耗时为:" + (endTime - startTime));
        return cityItemBeanList;
    }

    public void saveCityList(String strObject) {
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("cityList", strObject);
        edit.commit();
    }

    public String getCityList() {
        return sp.getString("cityList", null);
    }

    /**
     * 保存最新apk版本
     *
     * @param context
     * @param newApkVersion
     */
    public static void saveUpdateApkVersion(Context context,
                                            String newApkVersion) {
        saveString(context, UPDATE_APK_VERSION, newApkVersion);
    }

    /**
     * 获取最新apk版本
     *
     * @param context
     * @return
     */
    public static String getUpdateApkVersion(Context context) {
        return getString(context, UPDATE_APK_VERSION, "");
    }

    /**
     * 是否首次登录
     *
     * @param context
     * @param firstIn
     */
    public static void saveFirstIn(Context context, Boolean firstIn) {
        saveBoolean(context, FIRST_IN, firstIn);
    }

    /**
     * 是否首次登录
     *
     * @param context
     * @return
     */
    public static Boolean getFirstIn(Context context) {
        return getBoolean(context, FIRST_IN, true);
    }

    /**
     * 保存手势密码状态
     */
    public static void saveState(Context context, Boolean state) {
        saveBoolean(context, GESTUREVERIFY_STATE, state);
    }

    /**
     * 保存手势密码状态
     */
    public static boolean getState(Context context) {
        return getBoolean(context, FIRST_IN, true);
    }


    /**
     * 首次弹出广告框
     *
     * @param context
     * @param firstIn
     */
    public static void saveAddPopupWindow(Context context, Boolean firstIn) {
        saveBoolean(context, AD_POPUP_WINDOW, firstIn);
    }


    /**
     * 是否首次登录
     *
     * @param context
     * @return
     */
    public static Boolean getAddPopupWindow(Context context) {
        return getBoolean(context, AD_POPUP_WINDOW, true);
    }


    public static void saveFragmentState(Context context, Boolean firstIn) {
        saveBoolean(context, fragment_state, firstIn);
    }

    public static Boolean getFragmentState(Context context) {
        return getBoolean(context, fragment_state, true);
    }

    /**
     * 保存车商认证状态
     *
     * @param context
     * @param iVerifyStatus
     */
    public static void saveVerifyStatus(Context context, int iVerifyStatus) {
        saveInt(context, I_VERIFY_STATUS, iVerifyStatus);
    }

    /**
     * 获取用户id
     *
     * @param context
     * @return
     */
    public static int getVerifyStatus(Context context) {
        return getInt(context, I_VERIFY_STATUS, 1);
    }

    /**
     * 保存用户id
     *
     * @param context
     * @param userName
     */
    public static void saveUserName(Context context, String userName) {
        saveString(context, USER_ID, userName);
    }

    /**
     * 保存用户头像地址
     *
     * @param context
     * @param userHead
     */
    public static void saveUserHead(Context context, String userHead) {
        saveString(context, USER_HEAD, userHead);
    }

    /**
     * 获取用户id
     *
     * @param context
     * @return
     */
    public static String getUserName(Context context) {
        return getString(context, USER_ID, "");
    }

    /**
     * 获取用户头像
     *
     * @param context
     * @return
     */
    public static String getUserHead(Context context) {
        return getString(context, USER_HEAD, "");
    }

    /**
     * 保存token
     *
     * @param context
     * @param token
     */
    public static void saveToken(Context context, String token) {
        saveString(context, TOKEN, token);
    }

    /**
     * 获取token
     *
     * @param context
     * @return
     */
    public static String getToken(Context context) {
        return getString(context, TOKEN, "");
    }

    /**
     * 保存token
     *
     * @param context
     * @param localCity
     */
    public static void saveLocalCity(Context context, String localCity) {
        saveString(context, LOCAL_CITY, localCity);
    }

    /**
     * 获取token
     *
     * @param context
     * @return
     */
    public static String getLocalCity(Context context) {
        return getString(context, LOCAL_CITY, "");
    }

    /**
     * 保存deviceToken
     *
     * @param context
     * @param token
     */
    public static void saveDeviceToken(Context context, String token) {
        saveString(context, DEVICE_TOKEN, token);
    }

    /**
     * 是否富友已开户
     *
     * @param context
     * @param isOpen
     */
    public static void saveIsOpen(Context context, Boolean isOpen) {
        saveBoolean(context, B_IS_OPEN, isOpen);
    }

    /**
     * 是否富有开户
     *
     * @param context
     * @return
     */
    public static Boolean getIsOpen(Context context) {
        return getBoolean(context, B_IS_OPEN, false);
    }

    /**
     * 邮箱是否已绑定
     *
     * @param context
     * @param isBindEmail
     */
    public static void saveIsBindEmail(Context context, Boolean isBindEmail) {
        saveBoolean(context, B_IS_OPEN, isBindEmail);
    }

    /**
     * 获取邮箱是否绑定
     *
     * @param context
     * @return
     */
    public static Boolean getIsBindEmail(Context context) {

        return getBoolean(context, B_EMAIL_BIND, false);
    }

    /**
     * 获取deviceToken
     *
     * @param context
     * @return
     */
    public static String getDeviceToken(Context context) {
        return getString(context, DEVICE_TOKEN, "");
    }

    /**
     * 保存设备id
     *
     * @param context
     * @param deviceId
     */
    public static void saveDeviceId(Context context, String deviceId) {
        saveString(context, DEVICE_ID, deviceId);
    }

    /**
     * 获取设备id
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        return getString(context, DEVICE_ID, "");
    }


    /**
     * 保存用户名
     *
     * @param context
     * @param realName
     */
    public static void saveRealName(Context context, String realName) {
        saveString(context, REAL_NAME, realName);
    }

    /**
     * 获取用户名
     *
     * @param context
     * @return
     */
    public static String getRealName(Context context) {
        return getString(context, REAL_NAME, "");
    }

    /**
     * 保存用户编号
     *
     * @param context
     * @param sUserNo
     */
    public static void saveUseNo(Context context, String sUserNo) {
        saveString(context, USER_NO, sUserNo);
    }

    /**
     * 获取用户编号
     *
     * @param context
     * @return
     */
    public static String getUseNo(Context context) {
        return getString(context, USER_NO, "");
    }


    /**
     * 保存用户id
     *
     * @param context
     * @param sUserId
     */
    public static void saveUserId(Context context, String sUserId) {
        saveString(context, USER_ID, sUserId);
    }

    /**
     * 获取用户id
     *
     * @param context
     * @return
     */
    public static String getUserId(Context context) {
        return getString(context, USER_ID, "");
    }

    /**
     * 保存用户身份证id
     *
     * @param context
     * @param sIdNum
     */
    public static void saveIdNum(Context context, String sIdNum) {
        saveString(context, ID_NUM, sIdNum);
    }

    /**
     * 获取用户身份证id
     *
     * @param context
     * @return
     */
    public static String getIdNum(Context context) {
        return getString(context, ID_NUM, "");
    }


    /**
     * 保存用户昵称
     *
     * @param context
     * @param nickName
     */
    public static void saveNickName(Context context, String nickName) {
        saveString(context, NICK_NAME, nickName);
    }

    /**
     * 获取用户昵称
     *
     * @param context
     * @return
     */
    public static String getNickName(Context context) {
        return getString(context, NICK_NAME, "");
    }

    /**
     * 保存推送别名
     *
     * @param context
     * @param alias
     */
    public static void saveAlias(Context context, String alias) {
        saveString(context, ALIAS, alias);
    }

    /**
     * 获取推送别名
     *
     * @param context
     * @return
     */
    public static String getAlias(Context context) {
        return getString(context, ALIAS, "");
    }

    /**
     * 保存用户手机号
     *
     * @param context
     * @param phoneNumber
     */
    public static void savePhone(Context context, String phoneNumber) {
        saveString(context, PHONE, phoneNumber);
    }

    /**
     * 获取用户手机号
     *
     * @param context
     * @return
     */
    public static String getPhone(Context context) {
        return getString(context, PHONE, "");
    }

    /**
     * 保存用户头像url地址
     *
     * @param context
     * @param avatar
     */
    public static void saveAvatar(Context context, String avatar) {
        saveString(context, AVATAR, avatar);
    }

    /**
     * 获取用户头像url地址
     *
     * @param context
     * @return
     */
    public static String getAvatar(Context context) {
        return getString(context, AVATAR, "");
    }


    /**
     * 获取用户认证状态
     *
     * @param context
     * @return
     */
    public static int getUserCertifiedStatus(Context context) {
        return getInt(context, CERTIFIED_STATUS, 0);
    }


    /**
     * 保存用户认证状态
     *
     * @param context
     * @param status
     */
    public static void saveUserCertifiedStatus(Context context, int status) {
        saveInt(context, CERTIFIED_STATUS, status);
    }


    /**
     * 保存浏览量
     *
     * @param context
     * @param browseCount
     */
    public static void saveBrowseCount(Context context, int browseCount) {
        saveInt(context, BROWSE_COUNT, browseCount);
    }

    /**
     * 获取浏览量
     *
     * @param context
     * @return
     */
    public static int getBrowseCount(Context context) {
        return getInt(context, BROWSE_COUNT, 0);
    }

    /**
     * 保存加油卡默认项
     *
     * @param context
     * @param position
     */
    public static void saveMyDefaultRefuelCard(Context context, int position) {
        saveInt(context, My_REFUEL_CARD_DEFAULT, position);
    }

    /**
     * 获取加油卡默认项
     *
     * @param context
     * @return
     */
    public static int getMyDefaultRefuelCard(Context context) {
        return getInt(context, My_REFUEL_CARD_DEFAULT, 0);
    }

    /**
     * 保存定位城市ID
     *
     * @param context
     * @param cityId
     */
    public static void saveLocalCityId(Context context, long cityId) {
        saveLong(context, LOCAL_CITY_ID, cityId);
    }

    /**
     * 获取定位城市ID
     *
     * @param context
     * @return
     */
    public static long getLocalCityId(Context context) {
        return getLong(context, LOCAL_CITY_ID, 0L);
    }

    /**
     * 保存二手车首页的时间戳
     *
     * @param context
     * @param time
     */
    public static void saveUsedCarTime(Context context, long time) {
        if (getUsedCarTime(context) < time) {
            saveLong(context, USED_CAR_TIME, time);
        }
    }

    /**
     * 获取二手车首页的时间戳
     *
     * @param context
     * @return
     */
    public static long getUsedCarTime(Context context) {
        return getLong(context, USED_CAR_TIME, (long) 0);
    }

    /**
     * 保存买车页面的时间戳
     *
     * @param context
     * @param time
     */
    public static void saveBuyCarTime(Context context, long time) {
        if (getBuyCarTime(context) < time) {
            saveLong(context, BUY_CAR_TIME, time);
        }
    }

    /**
     * 获取买车页面的时间戳
     *
     * @param context
     * @return
     */
    public static long getBuyCarTime(Context context) {
        return getLong(context, BUY_CAR_TIME, (long) 0);
    }

    /**
     * 保存违章页面新的优惠券的时间戳
     *
     * @param context
     * @param time
     */
    public static void saveNewCouponTime(Context context, long time) {
        if (getNewCouponTime(context) < time) {
            saveLong(context, COUPON_NEW_TIME, time);
        }
    }

    /**
     * 获取违章页面新的优惠券的时间戳
     *
     * @param context
     * @return
     */
    public static long getNewCouponTime(Context context) {
        return getLong(context, COUPON_NEW_TIME, (long) 0);
    }

    /*****************************************************************************************************************************************
     * 缓存数据类型操作
     *****************************************************************************************************************************************/
    /**
     * 缓存字符串数据
     *
     * @param context
     * @param key
     * @param value
     */
    private static void saveString(Context context, String key, String value) {
        sp = getSP(context);
        sp.edit().putString(key, value).commit();
    }

    /**
     * 获取字符串的缓存数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    private static String getString(Context context, String key, String defValue) {
        sp = getSP(context);
        return sp.getString(key, defValue);
    }

    /**
     * 缓存整型数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveInt(Context context, String key, int value) {
        sp = getSP(context);
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 获取整型的缓存数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context context, String key, int defValue) {
        sp = getSP(context);
        return sp.getInt(key, defValue);
    }

    /**
     * 缓存长整型数据
     *
     * @param context
     * @param key
     * @param value
     */
    private static void saveLong(Context context, String key, Long value) {
        sp = getSP(context);
        sp.edit().putLong(key, value).commit();
    }

    /**
     * 获取长整型的缓存数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    private static Long getLong(Context context, String key, Long defValue) {
        sp = getSP(context);
        return sp.getLong(key, defValue);
    }

    /**
     * 缓存布尔型数据
     *
     * @param context
     * @param key
     * @param value
     */
    private static void saveBoolean(Context context, String key, boolean value) {
        sp = getSP(context);
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 获取布尔型的缓存数据
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    private static boolean getBoolean(Context context, String key,
                                      boolean defValue) {
        sp = getSP(context);
        return sp.getBoolean(key, defValue);
    }

}