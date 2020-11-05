package com.tester.jvm.mock.model;

import com.tester.jvm.mock.util.ExceptionAware;
import com.tester.jvm.mock.util.PathUtils;
import com.tester.jvm.mock.util.PropertyUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.tester.jvm.mock.util.PropertyUtil.getProperty;


/**
 * {@link ApplicationModel} 描述一个基础应用模型
 * <p>
 * 应用名    {@link ApplicationModel#appName}
 * 机器名    {@link ApplicationModel#host}
 * 环境信息  {@link ApplicationModel#environment}
 * 服务机器地址{@link ApplicationModel#mockServiceHost}
 * </p>
 */
public class ApplicationModel {

    private String appName;

    private String environment;

    private String host;

    private String mockServiceHost;


    private ExceptionAware ea = new ExceptionAware();

    private volatile boolean fusing = false;

    private static ApplicationModel instance = new ApplicationModel();

    private ApplicationModel() {
        // for example, you can define it your self
        this.appName = getProperty("app.name", "unknown");
        this.environment = getProperty("app.env", "unknown");
        this.mockServiceHost = getProperty("mock.host", "unknown");

        try {
            this.host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            // default value for disaster
            this.host = "127.0.0.1";
        }
    }

    public static ApplicationModel instance() {
        return instance;
    }

    /**
     * 是否正在工作（熔断机制）
     *
     * @return true/false
     */
    public boolean isWorkingOn() {
        return !fusing;
    }

    public String getMockServiceHost() {
        return mockServiceHost;
    }

    public void setMockServiceHost(String mockServiceHost) {
        this.mockServiceHost = mockServiceHost;
    }

    public String getAppName() {
        return appName;
    }


    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }


    public ExceptionAware getEa() {
        return ea;
    }

    public void setEa(ExceptionAware ea) {
        this.ea = ea;
    }

    public boolean isFusing() {
        return fusing;
    }

    public void setFusing(boolean fusing) {
        this.fusing = fusing;
    }
}
