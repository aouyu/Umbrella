package com.aouyu.apps.weather.utils;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 工具类
 *
 * @author fangxiaotian
 */
public class Tools {
    private static long lastClickTime;

    /**
     * 过滤EditText输入空格
     */
    public final static InputFilter filter = new InputFilter() {

        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned dest, int dstart, int dend) {
            // 返回null表示接收输入的字符,返回空字符串表示不接受输入的字符
            if (source.equals(" ")) {
                return "";
            } else {
                return null;
            }

        }
    };


    /**
     * 获取当前系统时间返回小时分钟
     *
     * @author ZhuZhouJun 2014-1-15 16:58:17
     */
    public static String getSimpleTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);

        return str;
    }

    /*
     * 判断字符串是否包含一些字符 contains
     */
    public static boolean containsString(String src, String dest) {
        boolean flag = false;
        if (src.contains(dest)) {
            flag = true;
        }
        return flag;
    }

    /**
     * 根据时间戳返回时分秒 --- 倒计时
     *
     * @param time1 时间
     * @return
     */
    public static String getMSTime(long time1) {
        long time = time1 / 1000;
        long a, b, c = 0;
        a = time % 60;
        b = time / 60;
        if (b > 60) {
            c = b / 60;
            b = b % 60;
        }
        return c + ":" + b + ":" + a;
    }

    /**
     * 判断当前是有网络
     *
     * @param context
     * @return
     */
    public static boolean checkIsOnLine(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            // 获取网络连接管理的对象
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null) {
                // 判断当前网络是否已经连接
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取字符串
     */
    public static String getString(Context context, int stringid) {
        String content;
        if (context == null || stringid == 0) {
            content = "error";
        } else {
            content = context.getString(stringid);
        }
        return content;
    }

    public static void setPricePoint(final Context context,
                                     final EditText editText, final double min, final double max) {
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        editText.setText(s);
                        editText.setSelection(s.length());
                    }

                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    editText.setText(s);
                    editText.setSelection(2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        editText.setText(s.subSequence(0, 1));
                        editText.setSelection(1);
                        return;
                    }
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

    /**
     * 把字符串转成数字
     */
    public static int stringToInt(String num) {
        if (isNull(num)) {
            num = "0";
        }
        return Integer.parseInt(num);
    }

    /**
     * 判断 多个字段的值否为空
     *
     * @return true为null或空; false不null或空
     */
    public static boolean isNull(String... ss) {
        for (int i = 0; i < ss.length; i++) {
            if (null == ss[i] || ss[i].equals("")
                    || ss[i].equalsIgnoreCase("null")) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断 一个字段的值否为空
     *
     * @param s
     * @return
     */
    public static boolean isNull(String s) {
        if (null == s || s.equals("") || s.equalsIgnoreCase("null")) {
            return true;
        }

        return false;
    }

    /**
     * 判断两个字段是否一样
     */
    public static boolean isEquals(String s0, String s1) {
        return s0 != null && null != s1 && s0.equals(s1);
    }

    /**
     * 将时间戳转为字符串 到分
     *
     * @param cc_time
     * @return
     */
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        if (cc_time == null) {
            cc_time = System.currentTimeMillis() + "";
        }

        if (cc_time.length() == 10) { // 单位 秒
            cc_time += "000";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time));

        return re_StrTime;

    }





    /**
     * 将时间戳转为字符串 到日
     *
     * @param cc_time
     * @return
     * @author Michael.Zhang 2013-08-05 14:09:17
     */
    public static String getStrDate(String cc_time) {
        String re_StrTime = "";
        if (cc_time == null) {
            cc_time = System.currentTimeMillis() + "";
        }

        if (cc_time.length() == 10) {
            cc_time += "000";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time));

        return re_StrTime;
    }

    /**
     * 将字符串到分 转换为时间戳
     *
     * @param user_time
     * @return
     * @author ZhuZhouJun 2014-4-3 下午5:06:49
     */
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 13);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    /**
     * 获取空格前面的字符串
     *
     * @param time
     * @return
     * @author ZhuZhouJun 2014-2-21 下午2:08:23
     */
    public static String getDate(String time) {
        String date = "";
        date = time.substring(0, time.indexOf(' '));
        return date;
    }

    /**
     * 获取当前系统时间
     *
     * @author ZhuZhouJun 2014-1-15 16:58:17
     */
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 将分转为元
     *
     * @return
     * @author Michael.Zhang
     */
    public static double getMoney(String money) {
        if (money != null && !money.equals("") && !money.equals("null")) {
            return Double.parseDouble(money) / 100.0;
        }

        return 0.00;
    }

    // 制造适配屏幕的imageView控件
    public static void handleImageViewForDestny(Context context, ImageView img) {
        int screenWidth = getScreenWidth(context);
        ViewGroup.LayoutParams lp = img.getLayoutParams();
        lp.width = screenWidth;
        lp.height = LayoutParams.WRAP_CONTENT;
        img.setLayoutParams(lp);
        img.setMaxWidth(screenWidth);
        img.setMaxHeight(screenWidth * 5); // 这里其实可以根据需求而定，我这里测试为最大宽度的5倍
    }

    private static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * 验证身份证号码
     *
     * @param idCard
     * @return
     * @author TangWei
     */
    public static boolean isIdCard(String idCard) {
        if (isNull(idCard))
            return false;
        String pattern_18 = "^[0-9][0-7][0-9]{4}[1-2][9|0]\\d{2}[0-1]\\d[0-3]\\d{4}[0-9|xX]$";// 简单的18位身份证号正则表达式
        String pattern_15 = "^[0-9][0-7][0-9]{4}\\d{2}[0-1]\\d[0-3]\\d{4}$";// 简单的15位身份证号正则表达式
        if (idCard.matches(pattern_18)) {
            return isLegalCard(idCard);
        }
        if (idCard.matches(pattern_15)) {
            return true;
        }
        return false;
    }

    /**
     * 是否合法的18位身份证号码
     *
     * @param card
     * @return
     */
    private static boolean isLegalCard(String card) {
        int sum = 0;
        int[] coefficient = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4,
                2};
        String[] pair = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
        for (int i = 0; i < card.length() - 1; i++) {
            Integer value = Integer.parseInt("" + card.charAt(i));
            sum += coefficient[i] * value;
        }
        int remainder = sum % 11;
        if (card.substring(card.length() - 1).equalsIgnoreCase(pair[remainder])) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证手机号码
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        if (isNull(phone))
            return false;
        String pattern = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";

        return phone.matches(pattern);
    }

    /**
     * 验证手机号码,并开放部分号码
     *
     * @param phone
     * @return
     */
    public static boolean isPhoneExceptInner(String phone, long min, long max) {
        if (isNull(phone))
            return false;
        if (Long.parseLong(phone) >= min && Long.parseLong(phone) <= max) {
            return true;
        }
        String pattern = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$";

        return phone.matches(pattern);
    }

    /**
     * 判断email格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 简单的验证一下银行卡号
     *
     * @param bankCard 信用卡是16位，其他的是13-19位
     * @return
     */
    public static boolean isBankCard(String bankCard) {
        if (isNull(bankCard))
            return false;
        String pattern = "^\\d{13,24}$";
        return bankCard.matches(pattern);
    }

    /**
     * 验证企业开户密码是否符合要求
     * 不少于8位字母和数字组成
     *
     * @param password
     * @return
     */
    public static boolean isPassword(String password) {
        String str = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,30}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(password);

        return m.matches();
    }

    /**
     * 将px类型的尺寸转换成dp类型的尺寸
     *
     * @param pxValue
     * @param context
     * @return
     */
    public static int PXtoDP(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将dp类型的尺寸转换成px类型的尺寸
     *
     * @param dipValue
     * @param context
     * @return
     */
    public static int DPtoPX(Context context, int dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * double 整理
     *
     * @return
     */
    public static Double roundDouble(double val, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = ((0 == val) ? new BigDecimal("0.0") : new BigDecimal(
                Double.toString(val)));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 判断 列表是否为空
     *
     * @return true为null或空; false不null或空
     */
    public static boolean isEmptyList(List list) {
        return list == null || list.size() == 0;
    }

    /**
     * 判断sd卡是否存在
     *
     * @return
     * @author Michael.Zhang 2013-07-04 11:30:54
     */
    public static boolean judgeSDCard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 判断 http 链接
     *
     * @param url
     * @return
     * @author Michael.Zhang
     */
    public static boolean isUrl(String url) {
        return null != url && url.startsWith("http://");
    }

    /**
     * 判断图品路径
     *
     * @return
     * @author Michael.Zhang 2013-11-8 下午7:50:30
     */
    public static boolean isImgUrl(String imgUrl) {
        return isUrl(imgUrl) && imgUrl.endsWith(".jpg");
    }

    /**
     * 获得hashmap中value的值,以List 返回
     *
     * @param hashMap
     * @return
     * @author Michael.Zhang 2013-08-21 13:56:07
     */
    public static List<String> getListByHashMap(HashMap<String, String> hashMap) {
        List<String> list = new ArrayList<String>();
        Iterator iter = hashMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            list.add((String) val);
        }

        return list;
    }

    /**
     * 获取版本号 给用户看的
     *
     * @return
     * @author Michael.Zhang 2013-08-29 22:14:37
     */
    public static double getVersionName(Context context) {
        String versionName = "0";
        if (getPackageInfo(context) != null) {
            versionName = getPackageInfo(context).versionName;
        }

        return Double.parseDouble(versionName);
    }

    /**
     * 获取版本号 返回String
     *
     * @return
     * @author Michael.Zhang 2013-08-29 22:14:37
     */
    public static String getVersionNameString(Context context) {
        String versionName = "0";
        if (getPackageInfo(context) != null) {
            versionName = getPackageInfo(context).versionName;
        }

        return versionName;
    }

    /**
     * 获取版本号 系统识别用的
     *
     * @return
     * @author Michael.Zhang 2013-08-31 18:47:09
     */
    public static int getVersionCode(Context context) {
        int versionCode = 0;
        if (getPackageInfo(context) != null) {
            versionCode = getPackageInfo(context).versionCode;
        }

        return versionCode;
    }

    /**
     * 比较版本名，用于比较本地和服务器版本名大小
     *
     * @param oldVersionName 本地版本号
     * @param newVersionName 服务器版本号
     * @return boolean true为更新，false为不更新
     */
    public static boolean compareVersionNames(String oldVersionName, String newVersionName) {
        Boolean res = false;

        String[] oldNumbers = oldVersionName.split("\\.");
        String[] newNumbers = newVersionName.split("\\.");

        // To avoid IndexOutOfBounds
        int maxIndex = Math.min(oldNumbers.length, newNumbers.length);

        for (int i = 0; i < maxIndex; i++) {
            int oldVersionPart = Integer.valueOf(oldNumbers[i]);
            int newVersionPart = Integer.valueOf(newNumbers[i]);

            if (oldVersionPart < newVersionPart) {
                res = true;
                break;
            } else if (oldVersionPart > newVersionPart) {
                res = false;
                break;
            }
        }

        // If versions are the same so far, but they have different length...
        if (res == false && oldNumbers.length != newNumbers.length) {
            res = (oldNumbers.length > newNumbers.length) ? false : true;
        }

        return res;
    }

    private static PackageInfo getPackageInfo(Context context) {
        String packageName = context.getPackageName();
        try {
            return context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取保存到View的Tag中的字符串
     *
     * @param v
     * @return
     */
    public static String getViewTagString(View v) {
        try {
            return v.getTag().toString();
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * 保留两位小数
     */
    public static double sishewurubaoliuliangwei(double maxAmount) {
        DecimalFormat fnum = new DecimalFormat("##0.00");
        double decimalmaxamount = Double.parseDouble(fnum.format(maxAmount));
        return decimalmaxamount;
    }

    ;

    /**
     * 判断str是否为空
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (null == s)
            return true;
        if (s.length() == 0)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }

    // 去文字半角化
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    public static void hideSoftInput(Activity activity) {
        InputMethodManager mm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        View view = activity.getCurrentFocus();

        if (null == mm || null == view) {
            return;
        }

        if (mm.isActive()) {
            mm.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } else {
            return;
        }

    }

    public static boolean isCarnumberNO(String carnumber) {
        /*
         * 车牌号格式：汉字 + A-Z + 5位A-Z或0-9 （只包括了普通车牌号，教练车和部分部队车等车牌号不包括在内）
		 */
        String carnumRegex = ".*[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]";
        if (TextUtils.isEmpty(carnumber))
            return false;
        else
            return carnumber.matches(carnumRegex);
    }

    /**
     * 验证车牌号格式
     *
     * @param strnum
     * @return
     */
    public static boolean IsVehiclenumber(String strnum) {
        String reg1 = "^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{4}[a-zA-Z_0-9_\u4e00-\u9fa5]$";
        return strnum.matches(reg1);
    }



    public static String cutString(String s, int n) throws Exception {
		/*
		 * 截取中文字符串工具类
		 */
        // TODO Auto-generated method stub
        if (s != null && "".equals(s)) {
            s = new String(s.getBytes(), "GBK");

        }
        StringBuffer buffer = new StringBuffer();
        if (n != 0 && n < s.getBytes("GBK").length) {

            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                buffer.append(c);
                // System.out.println("buffer=" + buffer.toString());
                if (Tools.isChinens(c)) {
                    --n;
                }

            }

        }

        return buffer.toString();
    }

    private static boolean isChinens(char c) throws Exception {
        // TODO Auto-generated method stub
        if (String.valueOf(c).getBytes("GBK").length > 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkString(String s) {
        return s.matches("[\u4e00-\u9fa5]");
    }

    // 获取屏幕宽度
    public static int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    // 获取屏幕高度
    public static int getWindowsheight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    // 计算listView的高度
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 去掉小数点方法
     *
     * @param MinQuota
     * @return
     */
    public static String doubleTrans1(double MinQuota) {
        if (MinQuota % 1.0 == 0) {
            return String.valueOf((long) MinQuota);
        }
        return String.valueOf(MinQuota);
    }

    public static String getCurrentTimeToMinute() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm ");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);

        return str;
    }

    /**
     * 过滤特殊字符
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String StringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字
        // String regEx = "[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }


    /**
     * 只允许字母、数字和汉字的正则
     *
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static String stringFilter(String str) throws PatternSyntaxException {

        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 富友webview参数转换
     */
    public static byte[] paramMap2bytes(Map<String, String> paramMap) throws Exception {
        Object[] keys = paramMap.keySet().toArray();
        Arrays.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            Object key = keys[i];
            sb.append(i == keys.length - 1 ? paramMap.get(key) : (paramMap.get(key) + "|"));
        }
        String str4Sign = sb.toString();
        Log.i("FXT", "STRING 4 SIGN:" + str4Sign);
        StringBuilder postStrSb = new StringBuilder();
        Object[] set = paramMap.entrySet().toArray();
        for (int i = 0; i < set.length; i++) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) set[i];
            postStrSb.append(String.format("%s=%s", entry.getKey(), (i == set.length - 1 ? entry.getValue() : (entry.getValue() + "&"))));
        }
        return postStrSb.toString().getBytes("UTF-8");
    }

    /**
     * 判断是否1秒内多次点击
     *
     * @return
     */

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
