package com.tester.jvm.mock.service.convert;


import com.alibaba.fastjson.JSON;
import com.tester.jvm.mock.common.domain.ModuleConfigBO;
import com.tester.jvm.mock.dal.model.ModuleConfig;
import com.tester.jvm.mock.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataUnit;

/**
 * {@link ModuleConfigConverter}
 * <p>
 *
 * @author fusheng.chu
 */
@Component("moduleConfigConverter")
@Slf4j
public class ModuleConfigConverter implements ModelConverter<ModuleConfig, ModuleConfigBO> {

    @Override
    public ModuleConfigBO convert(ModuleConfig source) {
        ModuleConfigBO bo = new ModuleConfigBO();
        BeanUtils.copyProperties(source, bo);
        bo.setIsUsable(source.getIsUsable() == 1 ? true : false);
        return bo;
    }

    @Override
    public ModuleConfig reconvert(ModuleConfigBO target) {
        return null;
    }
}
