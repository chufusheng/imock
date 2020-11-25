package com.tester.jvm.mock.service.impl;


import com.alibaba.fastjson.JSON;
import com.tester.jvm.mock.common.domain.MockResult;
import com.tester.jvm.mock.common.domain.ModuleConfigBO;
import com.tester.jvm.mock.common.domain.PageResult;
import com.tester.jvm.mock.common.domain.ResultHelper;
import com.tester.jvm.mock.common.params.ModuleConfigParams;
import com.tester.jvm.mock.dal.dao.ModuleConfigDao;
import com.tester.jvm.mock.dal.model.ModuleConfig;
import com.tester.jvm.mock.service.ModuleConfigService;
import com.tester.jvm.mock.service.convert.ModuleConfigConverter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.stream.Collectors;

/**
 * {@link }
 * <p>
 *
 * @author fusheng.chu
 */
@Service("ModuleConfigService")
public class ModuleConfigServiceImpl implements ModuleConfigService {

    @Resource
    private ModuleConfigDao moduleConfigDao;

    @Resource
    private ModuleConfigConverter moduleConfigConverter;

    @Override
    public PageResult<ModuleConfigBO> list(ModuleConfigParams params) {
        PageResult<ModuleConfigBO> result = new PageResult<>();
        Page<ModuleConfig> page = moduleConfigDao.selectByParams(params);
        if (page.hasContent()) {
            result.setSuccess(true);
            result.setPageIndex(params.getPage());
            result.setCount(page.getTotalElements());
            result.setTotalPage(page.getTotalPages());
            result.setPageSize(params.getSize());
            result.setData(page.getContent().stream().map(moduleConfigConverter::convert).collect(Collectors.toList()));
        }
        return result;

    }

    @Override
    public MockResult<ModuleConfigBO> query(ModuleConfigParams params) {
        ModuleConfig moduleConfig = moduleConfigDao.query(params);
        if (moduleConfig == null) {
            return ResultHelper.fail("data not exist");
        }
        return ResultHelper.success(moduleConfigConverter.convert(moduleConfig));
    }

    @Override
    public MockResult<ModuleConfigBO> saveOrUpdate(ModuleConfigParams params) {

        ModuleConfig  moduleConfig = new ModuleConfig();
        moduleConfig.setId(params.getId());
        moduleConfig.setAppName(params.getAppName());
        moduleConfig.setEnvironment(params.getEnvironment());
        moduleConfig.setMockClass(params.getMockClass());
        moduleConfig.setMockMethod(params.getMockMethod());
        moduleConfig.setReturnObj(JSON.toJSONString(params.getReturnObj()));
        moduleConfig.setRuleConfig(params.getRuleConfig());
        moduleConfig.setIsUsable(params.getIsUsable());

        ModuleConfig callback = moduleConfigDao.saveOrUpdate(moduleConfig);
        return ResultHelper.success(moduleConfigConverter.convert(callback));
    }

    @Override
    public MockResult<ModuleConfig> push(ModuleConfigParams params) {
        return ResultHelper.success(moduleConfigDao.query());
    }
}
