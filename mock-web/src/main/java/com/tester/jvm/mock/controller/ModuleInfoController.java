package com.tester.jvm.mock.controller;


import com.tester.jvm.mock.common.domain.MockResult;
import com.tester.jvm.mock.common.domain.ModuleInfoBO;
import com.tester.jvm.mock.common.domain.PageResult;
import com.tester.jvm.mock.common.domain.PagerAdapter;
import com.tester.jvm.mock.common.params.ModuleInfoParams;
import com.tester.jvm.mock.service.ModuleInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link ModuleInfoController}
 * <p>
 * 在线模块页面
 *
 * @author zhaoyb1990
 */
@RequestMapping("/module")
@Controller
public class ModuleInfoController {

    @Resource
    private ModuleInfoService moduleInfoService;

    @ResponseBody
    @RequestMapping("get/list")
    public PageResult<ModuleInfoBO> list(@RequestBody ModuleInfoParams params) {
        return moduleInfoService.query(params);
    }

    @ResponseBody
    @RequestMapping("get/byName")
    public MockResult<ModuleInfoBO> list(@RequestBody String appName) {
        return moduleInfoService.query(appName);
    }

    @ResponseBody
    @PostMapping("report")
    public MockResult<ModuleInfoBO> list(@RequestBody ModuleInfoBO params) {
        return moduleInfoService.report(params);
    }

    @ResponseBody
    @RequestMapping("active")
    public MockResult<ModuleInfoBO> active(@RequestBody ModuleInfoParams params) {
        return moduleInfoService.active(params);
    }

    @ResponseBody
    @RequestMapping("frozen")
    public MockResult<ModuleInfoBO> frozen(@RequestBody ModuleInfoParams params) {
        return moduleInfoService.frozen(params);
    }

    @ResponseBody
    @RequestMapping("install")
    public MockResult<String> install(@RequestBody ModuleInfoParams params) {
        return moduleInfoService.install(params);
    }

    @ResponseBody
    @RequestMapping("/reload")
    public MockResult<String> reload(@RequestParam String appName) {
        return moduleInfoService.reload(appName);
    }


    @GetMapping("test")
    @ResponseBody
    public String test(@RequestParam int a) {
        try {
            return this.test1(a);
        } catch (Exception e) {
            return e.toString();
        }
    }


    public String test1(int a) throws IndexOutOfBoundsException {
        List b = new ArrayList();
        b.add("1");
        return (String) b.get(a);
    }


}
