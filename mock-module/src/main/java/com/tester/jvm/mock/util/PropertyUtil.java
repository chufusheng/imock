package com.tester.jvm.mock.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * {@link PropertyUtil} 属性操作
 * <p>
 *
 * @author fusheng.chu
 */
public class PropertyUtil {

    private static Properties properties = new Properties();

    static {
        try {
            LogUtil.info2("PathUtils.getConfigPath()", com.tester.jvm.mock.util.PathUtils.getConfigPath());
            InputStream is = new FileInputStream(new File(com.tester.jvm.mock.util.PathUtils.getConfigPath() + "/mock.properties"));
            properties.load(is);
        } catch (Exception e) {
            LogUtil.error("com.tester.jvm.mock.util.PathUtils.getConfigPath() error", e);
            if (PropertyUtil.class.getClassLoader().getClass().getCanonicalName().contains("sandbox")) {
                throw new RuntimeException("load mock.properties failed", e);
            }
        }
    }

    /**
     * 获取配置，先获取jvm 环境变量里的，如果没有获取配置文件里的（./sandbox-module/cfg/mock.properties）
     *
     * @param key          属性key
     * @param defaultValue 默认值
     */

    public static String getProperty(String key, String defaultValue) {
        LogUtil.info2("System.getProperties", key + ":" + System.getProperty(key));

        String property = System.getProperty(key);
        if (StringUtils.isBlank(property)) {
            property = properties.getProperty(key);
        }
        return StringUtils.isEmpty(property) ? defaultValue : property;
    }

    /**
     * 获取系统属性带默认值
     *
     * @param key          属性key
     * @param defaultValue 默认值
     * @return 属性值 or 默认值
     */
    public static String getSystemPropertyOrDefault(String key, String defaultValue) {
        String property = System.getProperty(key);
        return StringUtils.isEmpty(property) ? defaultValue : property;
    }

    /**
     * 获取mock.properties的配置信息
     *
     * @param key          属性key
     * @param defaultValue 默认值
     * @return 属性值 or 默认值
     */
    public static String getPropertyOrDefault(String key, String defaultValue) {
        String property = properties.getProperty(key);
        return StringUtils.isEmpty(property) ? defaultValue : property;
    }

}
