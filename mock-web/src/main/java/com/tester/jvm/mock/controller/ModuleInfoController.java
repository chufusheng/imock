package com.tester.jvm.mock.controller;


import com.tester.jvm.mock.common.domain.MockResult;
import com.tester.jvm.mock.common.domain.ModuleInfoBO;
import com.tester.jvm.mock.common.domain.PageResult;
import com.tester.jvm.mock.common.domain.ResultHelper;
import com.tester.jvm.mock.common.params.ModuleInfoParams;
import com.tester.jvm.mock.service.ModuleInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link ModuleInfoController}
 * <p>
 * 在线模块页面
 *
 * @author fusheng.chu
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
    @RequestMapping("get/appName/list")
    public List<String> getAppNameList() {
        return moduleInfoService.getAppNameList();
    }

    @ResponseBody
    @RequestMapping("get/appEnv/list")
    public List<String> getAppEnvByAppNameList(@RequestParam String appName) {
        return moduleInfoService.getAppEnvByAppNameList(appName);
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
    @RequestMapping("reload")
    public MockResult<String> reload(@RequestBody ModuleInfoParams params) {
        return moduleInfoService.reload(params);
    }


    @ResponseBody
    @RequestMapping("log")
    public String log(@RequestBody ModuleInfoParams params) {
        return moduleInfoService.log(params).getData();
    }

    @ResponseBody
    @RequestMapping("frozenOrActive")
    public MockResult<String> frozenOrActive(@RequestBody ModuleInfoParams params) {
        try {
            if (params.getStatus().equals("ACTIVE")) {
                moduleInfoService.frozen(params);
            } else {
                moduleInfoService.active(params);
            }
        } catch (Exception e) {
            ResultHelper.fail();
        }

        return ResultHelper.success();
    }


    @GetMapping("test")
    @ResponseBody
    public Object test(@RequestParam String al, @RequestParam String b) {
        try {
            int a = Integer.valueOf(al);
            if (b.equals("1")) {
                return this.test1(a);
            } else if (b.equals("2")) {
                return this.test2(a);
            } else if (b.equals("3")) {
                return this.test3(a);
            } else if (b.equals("4")) {
                return this.test4(a);
            }
            return "null";

        } catch (Exception e) {
            return 404;
        }
    }


    public String test1(int a) throws IndexOutOfBoundsException {
        List b = new ArrayList();
        b.add("1");
        return (String) b.get(a);
    }


    public int test2(int a) throws IndexOutOfBoundsException {
        List b = new ArrayList();
        b.add(1);
        return (int) b.get(a);
    }


    public Long test3(int a) throws IndexOutOfBoundsException {
        List b = new ArrayList();
        b.add(1L);
        return (Long) b.get(a);
    }

    public Boolean test4(int a) {
        return false;
    }


}
