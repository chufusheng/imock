package com.tester.jvm.mock;

import com.alibaba.jvm.sandbox.api.ModuleException;
import com.alibaba.jvm.sandbox.api.resource.ConfigInfo;
import com.alibaba.jvm.sandbox.api.resource.ModuleManager;
import com.tester.jvm.mock.model.ApplicationModel;
import com.tester.jvm.mock.util.HttpUtil;
import com.tester.jvm.mock.util.LogUtil;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * {@link HeartbeatHandler}
 * <p>
 *
 * @author fusheng.chu
 */
public class HeartbeatHandler {

    private static final long FREQUENCY = 10;

    private static ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("heartbeat-pool-%d").daemon(true).build());

    private final ConfigInfo configInfo;
    private final ModuleManager moduleManager;
    private AtomicBoolean initialize = new AtomicBoolean(false);

    public HeartbeatHandler(ConfigInfo configInfo, ModuleManager moduleManager) {
        this.configInfo = configInfo;
        this.moduleManager = moduleManager;
    }

    public synchronized void start() {
        if (initialize.compareAndSet(false, true)) {
            executorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        innerReport();
                    } catch (Exception e) {
                        LogUtil.error("error occurred when report heartbeat", e);
                    }
                }
            }, 0, FREQUENCY, TimeUnit.MINUTES);
        }
    }

    public void stop() {
        if (initialize.compareAndSet(true, false)) {
            executorService.shutdown();
        }
    }

    private void innerReport() {
        LogUtil.info("innerReport start");
        Map<String, String> params = new HashMap<String, String>(8);
        params.put("appName", ApplicationModel.instance().getAppName());
        params.put("ip", ApplicationModel.instance().getHost());
        params.put("environment", ApplicationModel.instance().getEnvironment());
        params.put("port", configInfo.getServerAddress().getPort() + "");
        params.put("version", Constants.VERSION);
        try {
            params.put("status", moduleManager.isActivated(Constants.MODULE_ID) ? "ACTIVE" : "FROZEN");
        } catch (ModuleException e) {
            LogUtil.error("error  when  innerReport", e);
        }
        HttpUtil.doPost(ApplicationModel.instance().getMockServiceHost() + "/module/report", params);
    }
}
