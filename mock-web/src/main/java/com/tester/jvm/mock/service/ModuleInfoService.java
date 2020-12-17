package com.tester.jvm.mock.service;

import com.tester.jvm.mock.common.domain.MockResult;
import com.tester.jvm.mock.common.domain.ModuleInfoBO;
import com.tester.jvm.mock.common.domain.PageResult;
import com.tester.jvm.mock.common.params.ModuleInfoParams;

import java.util.List;

public interface ModuleInfoService {

    PageResult<ModuleInfoBO> query(ModuleInfoParams params);

    MockResult<ModuleInfoBO> query(String appName, String ip);

    MockResult<ModuleInfoBO> report(ModuleInfoBO params);

    MockResult<ModuleInfoBO> active(ModuleInfoParams params);

    MockResult<ModuleInfoBO> frozen(ModuleInfoParams params);

    MockResult<String> install(ModuleInfoParams params);

    MockResult<String> reload(ModuleInfoParams params);

    MockResult<String> log(ModuleInfoParams params);

    List<String> getAppNameList();

    List<String> getAppEnvByAppNameList(String appName);


}
