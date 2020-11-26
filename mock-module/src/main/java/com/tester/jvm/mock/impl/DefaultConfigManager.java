package com.tester.jvm.mock.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tester.jvm.mock.MockModule;
import com.tester.jvm.mock.model.ApplicationModel;
import com.tester.jvm.mock.model.MockConfig;
import com.tester.jvm.mock.model.MockResult;
import com.tester.jvm.mock.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * {@link DefaultConfigManager} http数据拉取
 * <p>
 *
 * @author fusheng.chu
 */
public class DefaultConfigManager {

    private final static String DEFAULT_CONFIG_URL = ApplicationModel.instance().getMockServiceHost() + "/config/get/list?appName=%s&env=%s";
    private final static Logger log = LoggerFactory.getLogger(DefaultConfigManager.class);

    public MockResult<List<MockConfig>> pullConfig() {

        log.info("pullConfig       Start");

        int retryTime = 100;
        HttpUtil.Resp resp = null;
        while (--retryTime > 0) {
            resp = HttpUtil.doGet(String.format(DEFAULT_CONFIG_URL, ApplicationModel.instance().getAppName(),
                    ApplicationModel.instance().getEnvironment()));
            if (resp.isSuccess()) {
                log.info("pullConfig     success");
                break;
            }else {
                log.error("pullConfig     failed");
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                break;
            }
        }
        if (resp == null) {
            throw new RuntimeException("pull mock config failed, remain retry time is " + retryTime);
        }
        try {
            MockResult<List<MockConfig>> listMockResult = JSON.parseObject(resp.getBody(), new TypeReference<MockResult<List<MockConfig>>>() {
            });
            return listMockResult;
        } catch (Exception e) {
            log.error("pullConfig Exception    =====", e);
            return MockResult.builder().success(false).message(e.getMessage()).build();
        }
    }

}
