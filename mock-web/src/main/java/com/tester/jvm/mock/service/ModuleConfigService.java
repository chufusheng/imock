package com.tester.jvm.mock.service;


import com.tester.jvm.mock.common.domain.MockResult;
import com.tester.jvm.mock.common.domain.ModuleConfigBO;
import com.tester.jvm.mock.common.domain.PageResult;
import com.tester.jvm.mock.common.params.ModuleConfigParams;
import com.tester.jvm.mock.dal.model.ModuleConfig;

/**
 * {@link ModuleConfigService}
 * <p>
 *
 * @author fusheng.chu
 */
public interface ModuleConfigService {

    PageResult<ModuleConfigBO> list(ModuleConfigParams params);

    MockResult<ModuleConfigBO> query(ModuleConfigParams params);

    MockResult<ModuleConfigBO> saveOrUpdate(ModuleConfigParams params);

    MockResult<ModuleConfig> push(ModuleConfigParams params);
}
