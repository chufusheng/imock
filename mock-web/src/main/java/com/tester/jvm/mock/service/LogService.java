package com.tester.jvm.mock.service;

import com.tester.jvm.mock.common.domain.MockResult;
import com.tester.jvm.mock.common.domain.PageResult;
import com.tester.jvm.mock.common.params.LogInfoParams;

/**
 * @author: fusheng.chu
 * @create: 2020-11-25 13:59
 **/

public interface LogService {


    MockResult<String> getLog(LogInfoParams params);

}
