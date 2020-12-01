package com.tester.jvm.mock.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * {@link LogUtil} 日志类工具；插件没有引入日志框架可以使用{@link LogUtil} 打日志
 * <p>
 *
 * @author fusheng.chu
 */
public class LogUtil {


    private final static Logger LOGGER = LoggerFactory.getLogger(LogUtil.class);

    public static void info2(String title, String log, Object... params) {
        info(getTitle(title) + log, params);
    }

    public static void error2(String title, String log, Object... params) {
        error(getTitle(title) + log, params);
    }

    public static void info(String placeholder, Object... params) {
        LOGGER.info(placeholder, params);
    }

    public static void error(String placeholder, Object... params) {
        LOGGER.error(placeholder, params);
    }

    public static void warn(String placeholder, Object... params) {
        LOGGER.warn(placeholder, params);
    }

    public static void debug(String placeholder, Object... params) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(placeholder, params);
        }
    }

    public static String getTitle(String title) {
        String init = "                                ====== ";
        StringBuilder sb = new StringBuilder(init);
        if (title.length() <= 33) {
            title = title + sb.delete(0, title.length());
        }
        return title;

    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.home"));

    }
}
