package com.tester.jvm.mock.task;

import com.tester.jvm.mock.dal.dao.ModuleInfoDao;
import com.tester.jvm.mock.dal.model.ModuleInfo;
import com.tester.jvm.mock.service.ModuleInfoService;
import com.tester.jvm.mock.service.convert.ModuleInfoConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
public class AppTask {

    @Resource
    private ModuleInfoDao moduleInfoDao;

    @Resource
    private ModuleInfoService moduleInfoService;

    @Resource
    private ModuleInfoConverter moduleInfoConverter;

    @Scheduled(cron = "0/5 * * * * ?")
    private void heartbeatTask() {

        List<ModuleInfo> moduleInfos = moduleInfoService.getListByStatus("ACTIVE");
        for (ModuleInfo moduleInfo : moduleInfos) {
            Boolean heartRes = moduleInfoService.getAppHeart(moduleInfoConverter.convert(moduleInfo)).getData();
            if (!heartRes) {
                moduleInfoDao.updateStateById(moduleInfo.getId(), "OFFLINE");
            }
        }
    }
}