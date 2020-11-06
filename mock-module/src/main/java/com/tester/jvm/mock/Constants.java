package com.tester.jvm.mock;

/**
 * <p>
 *
 * @author fusheng.chu
 */
public class Constants {

    static final String MODULE_ID = "mock";

    static final String VERSION = "1.0.0";


    /**
     * servlet-api路由（目前sandbox还不支持启动module路由，所以在插件层面进行路由，保证插件使用容器的servlet-api）
     */
    public static final String SERVLET_API_NAME = "javax.servlet.http.HttpServlet";


}
