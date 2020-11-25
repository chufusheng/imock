package com.tester.jvm.mock.service.impl;

import com.tester.jvm.mock.common.domain.MockResult;
import com.tester.jvm.mock.common.domain.PageResult;
import com.tester.jvm.mock.common.domain.ResultHelper;
import com.tester.jvm.mock.common.params.LogInfoParams;
import com.tester.jvm.mock.service.LogService;
import com.tester.jvm.mock.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author: fusheng.chu
 * @create: 2020-11-25 14:08
 **/


@Service("LogService")
public class LogServiceImpl implements LogService {


    @Override
    public MockResult<String> getLog(LogInfoParams params) {
        String log = "";
        try {
            FileUtil fileUtil = new FileUtil();
            log = fileUtil.readLastRows(params.getFilepath(), null, params.getRows());
        } catch (IOException e) {
            return ResultHelper.fail("获取日志异常");
        }
        return ResultHelper.success("获取成功", log);
    }

}
