package com.tester.jvm.mock.controller;


import com.tester.jvm.mock.common.domain.MockResult;
import com.tester.jvm.mock.common.domain.ModuleConfigBO;
import com.tester.jvm.mock.common.domain.PageResult;
import com.tester.jvm.mock.common.params.ModuleConfigParams;
import com.tester.jvm.mock.dal.model.ModuleConfig;
import com.tester.jvm.mock.service.ModuleConfigService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * {@link ModuleConfigController}
 * <p>
 * 配置管理页面
 *
 * @author fusheng.chu
 */
@RequestMapping("/config")
@Controller
public class ModuleConfigController {

    @Resource
    private ModuleConfigService moduleConfigService;


    @PostMapping("get/list")
    @ResponseBody
    public PageResult<ModuleConfigBO> getList(@RequestBody ModuleConfigParams params) {
        return moduleConfigService.list(params);
    }


    @GetMapping("get/list")
    @ResponseBody
    public PageResult<ModuleConfigBO> getList(@RequestParam String appName, @RequestParam String env) {

        ModuleConfigParams params = new ModuleConfigParams();
        params.setAppName(appName);
        params.setEnvironment(env);
        return moduleConfigService.list(params);
    }


    @PostMapping("save")
    @ResponseBody
    public MockResult<ModuleConfigBO> saveOrUpdate(@RequestBody ModuleConfigParams params) {
        return moduleConfigService.saveOrUpdate(params);
    }

}
