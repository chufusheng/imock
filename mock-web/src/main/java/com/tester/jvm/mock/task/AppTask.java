package com.tester.jvm.mock.task;

import com.tester.jvm.mock.dal.dao.ModuleInfoDao;
import com.tester.jvm.mock.dal.model.ModuleInfo;
import com.tester.jvm.mock.service.ModuleInfoService;
import com.tester.jvm.mock.service.convert.ModuleInfoConverter;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

@Configuration
@EnableScheduling
public class AppTask {

    @Resource
    private ModuleInfoDao moduleInfoDao;

    @Resource
    private ModuleInfoService moduleInfoService;

    @Resource
    private ModuleInfoConverter moduleInfoConverter;

    Logger logger = Logger.getLogger(AppTask.class.getName());


    @Scheduled(cron = "0 0 0/1 * * ? ")
    private void heartbeatTask() {

        logger.info("====== heartbeatTask  任务开始 ======");
        List<ModuleInfo> moduleInfos = moduleInfoService.getListByStatus("ACTIVE");
        List<ModuleInfo> frozenModuleInfos = moduleInfoService.getListByStatus("FROZEN");
        moduleInfos.addAll(frozenModuleInfos);

        for (ModuleInfo moduleInfo : moduleInfos) {
            Boolean heartRes = moduleInfoService.getAppHeart(moduleInfoConverter.convert(moduleInfo)).getData();
            if (!heartRes) {
                moduleInfoDao.updateStateById(moduleInfo.getId(), "OFFLINE");
            }
        }
    }
}