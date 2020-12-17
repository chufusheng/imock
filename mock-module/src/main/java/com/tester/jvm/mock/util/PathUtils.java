package com.tester.jvm.mock.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Field;

/**
 * {@link PathUtils} 插件路径
 * <p>
 * cfg 的位置
 * <p>
 *
 */
public class PathUtils {



    /**
     * 获取配置文件路径
     *
     * @return 配置绝对路径
     */
    public static String getConfigPath() {
        String modulePath = getModulePath();
        if (StringUtils.isEmpty(modulePath)) {
            return null;
        }
        return modulePath + "/cfg";
    }


    /**
     * 获取当前模块绝对路径
     *
     * @return 模块绝对路径
     */
    public static String getModulePath() {
        // moduleClassloader
        ClassLoader classLoader = PathUtils.class.getClassLoader();
        // moduleClassloader class
        Class<? extends ClassLoader> aClass = classLoader.getClass();
        try {
            Field field = aClass.getDeclaredField("moduleJarFile");
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            File file = (File) field.get(classLoader);
            field.setAccessible(accessible);
            return file.getParentFile().getAbsolutePath();
        } catch (Throwable e) {
            // ignore
        }
        return null;
    }


}
