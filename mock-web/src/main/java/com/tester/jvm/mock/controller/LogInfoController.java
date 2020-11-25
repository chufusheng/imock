package com.tester.jvm.mock.controller;

import com.tester.jvm.mock.common.domain.MockResult;
import com.tester.jvm.mock.common.domain.ModuleConfigBO;
import com.tester.jvm.mock.common.domain.PageResult;
import com.tester.jvm.mock.common.params.LogInfoParams;
import com.tester.jvm.mock.common.params.ModuleConfigParams;
import com.tester.jvm.mock.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author: fusheng.chu
 * @create: 2020-11-25 13:58
 **/


@RequestMapping("/log")
@Controller
public class LogInfoController {


    @Resource
    LogService logService;

    @GetMapping("get")
    @ResponseBody
    public MockResult<String> getLog(@RequestParam String filePath, @RequestParam int rows) {

        LogInfoParams params = new LogInfoParams();
        params.setFilepath(filePath);
        params.setRows(rows);
        return logService.getLog(params);
    }

}
