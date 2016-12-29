package com.aouyu.apps.weather.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by huhuan on 2016/11/10.
 * 反射获取泛型的类型
 */

public class GenericUtil {
    /**
     * @param object 对象
     * @param index  对象类型的泛型下标
     * @param <T>    泛型指定
     */
    public static <T> T getObj(Object object, int index) {
        try {
            ParameterizedType parameterizedType = (ParameterizedType) (object.getClass()
                    .getGenericSuperclass());
            Type type = parameterizedType.getActualTypeArguments()[index];
            return ((Class<T>) type).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
