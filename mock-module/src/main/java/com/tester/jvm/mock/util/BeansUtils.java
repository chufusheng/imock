package com.tester.jvm.mock.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;

import java.lang.reflect.Type;


/**
 * @author: fusheng.chu
 * @create: 2020-11-19 16:30
 **/

public class BeansUtils {

    private static class SingletonHolder {
        private static BeansUtils INSTANCE = new BeansUtils();
    }

    public static BeansUtils getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Object parseByTypes(String json, String[] classNames) {
        Type[] types = new Type[classNames.length];
        try {
            for (int i = 0; i < classNames.length; i++) {
                types[i] = Class.forName(classNames[i]);
            }
        } catch (ClassNotFoundException e) {
            LogUtil.error("ClassNotFoundException", e.getMessage());
        }
        return JSONObject.parseObject(json, buildType(types));
    }

    public Object parseByTypes(String json, Type[] types) {
        return JSONObject.parseObject(json, buildType(types));
    }

    public static Type buildType(Type[] types) {
        ParameterizedTypeImpl beforeType = null;
        if (types != null && types.length > 0) {
            for (int i = types.length - 1; i > 0; i--) {
                beforeType = new ParameterizedTypeImpl(new Type[]{beforeType == null ? types[i] : beforeType}, null, types[i - 1]);
            }
        }
        return beforeType;
    }


}
